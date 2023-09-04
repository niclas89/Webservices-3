package com.larsson.sushi.service;


import com.larsson.sushi.exceptionHandling.BusinessException;
import com.larsson.sushi.model.Booking;
import com.larsson.sushi.repository.BookingRepository;

import com.larsson.sushi.repository.ItemRepossitory;
import com.larsson.sushi.repository.OrderRepository;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class BookingServiceImpl implements BookingService{

    private static final Logger bookingLogger = Logger.getLogger(BookingServiceImpl.class);

    @Autowired
    private BookingRepository repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ItemRepossitory itemRepossitory;


    @Override
    public Boolean newBooking(Booking newBooking) {
        if(checkAvailability(newBooking)){
            /*if(newBooking.getOrder().getId() == null){
                Order order = orderRepository.save(newBooking.getOrder());
                for(Item item:order.getItems()){
                    item.setOrder(order);
                }
                itemRepossitory.saveAllAndFlush(order.getItems());

            }

             */
            repository.save(newBooking);
            bookingLogger.info("Customer made a new booking for date:" + newBooking.getBookingDate());
            return true;
        }
        return false;
    }

    @Override
    public List<Booking> allBookingsByCustomerId(Long id) {
        List<Booking> dbBookings = repository.findAllByCustomer_Id(id);
        for(Booking booking:dbBookings){
            booking.getOrder().updateTotalPrice();
        }
        return  dbBookings;
    }

    @Override
    public Booking upDateBooking(Booking updatedBooking) {
        boolean availabilityCheck;
        Optional<Booking> dbBooking = repository.findById(updatedBooking.getId());
        if(dbBooking.isPresent()){
            availabilityCheck = checkDateChange(dbBooking.get(),updatedBooking);
            dbBooking.get().updateValues(updatedBooking);
            if(availabilityCheck){
                if(!checkAvailability(dbBooking.get())){
                    bookingLogger.info("Customer tried to updated booking id:" + dbBooking.get().getId() + "  to an unavailable date:" + dbBooking.get().getBookingDate());
                    throw new BusinessException("Booking at chosen date and time not available ", HttpStatus.NOT_MODIFIED.value());
                    //throw new DataIntegrityViolationException("Booking at chosen date and time not available");
                }
            }
            bookingLogger.info("Customer updated booking with id:"+ dbBooking.get().getId());
            return repository.save(dbBooking.get());
        }else{
            throw new NoSuchElementException("No booking with that booking id");
        }
    }

    private boolean checkDateChange(Booking booking1 , Booking booking2){
        if(booking1.getBookingDate().equalsIgnoreCase(booking2.getBookingDate()) && booking1.isLunch() && booking2.isLunch() || booking1.getBookingDate().equalsIgnoreCase(booking2.getBookingDate()) && booking1.isDinner() && booking2.isDinner()   ){
        }else return true;

        if(booking1.getRoom().getId().equals(booking2.getRoom().getId())){
        }else return true;

        return false;
    }

    public boolean checkAvailability(Booking booking){
        Optional<Booking> occupied;
        if(booking.isDinner()) {
             occupied = repository.findByBookingDateAndRoomAndDinnerIsTrue(booking.getBookingDate(), booking.getRoom());
        } else if (booking.isLunch()) {
            occupied = repository.findByBookingDateAndRoomAndLunchIsTrue(booking.getBookingDate(),booking.getRoom());

        }else{
            throw  new DataIntegrityViolationException("Incomplete  Booking, Dinner och Lunch must be selected  ");
        }

        return occupied.isEmpty();



    }

}
