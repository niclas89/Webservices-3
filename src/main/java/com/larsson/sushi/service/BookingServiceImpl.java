package com.larsson.sushi.service;


import com.larsson.sushi.exceptionHandling.BusinessException;
import com.larsson.sushi.model.Booking;
import com.larsson.sushi.model.Order;
import com.larsson.sushi.repository.*;
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
    private BookingRepository bookingRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RoomRepository roomRepository;


    @Override
    public Booking newBooking(Booking newBooking) {

        if(checkAvailability(newBooking)){
            if(newBooking.getCustomer().getId() == null){
                newBooking.setCustomer(customerRepository.save(newBooking.getCustomer()));
                newBooking.getOrder().setCustomer(newBooking.getCustomer());
            }
            System.out.println(newBooking.getRoom().getId());
            if(newBooking.getOrder().getId() == null){
                newBooking.setOrder(orderRepository.save(newBooking.getOrder()));

                System.out.println(newBooking.getNumberOfGuest() );
                System.out.println(newBooking.getRoom().getMaxGuest());

            }if(newBooking.getNumberOfGuest() <= roomRepository.getReferenceById(newBooking.getRoom().getId()).getMaxGuest()) {
                bookingLogger.info("Customer made a new booking for date:" + newBooking.getBookingDate());
                updateTotalPrice(newBooking);
                return bookingRepository.saveAndFlush(newBooking);

            }else{
                throw new BusinessException("Room is to small for the ammount of guests",200);
            }



        }

       throw new BusinessException("Date not available",HttpStatus.BAD_REQUEST.value());
    }

    @Override
    public Booking getBooking(Long id) {
        Booking booking = bookingRepository.getReferenceById(id);
        updateTotalPrice(booking);
        return  booking;
    }

    @Override
    public List<Booking> allBookingsByCustomerId(Long id) {
        List<Booking> dbBookings = bookingRepository.findAllByCustomer_Id(id);
        for(Booking booking:dbBookings){
            updateTotalPrice(booking);
        }
        return  dbBookings;
    }

    @Override
    public Booking upDateBooking(Booking updatedBooking) {
        if(updatedBooking.getId() == null){
            throw new DataIntegrityViolationException("");
        }
        Optional<Booking> dbBooking = bookingRepository.findById(updatedBooking.getId());
        if(dbBooking.isPresent()){

            dbBooking.get().updateValues(updatedBooking);

                if(!checkAvailability(dbBooking.get())){
                    throw new BusinessException("Booking at chosen date and time not available ", HttpStatus.NOT_MODIFIED.value());
                }

            if(dbBooking.get().getNumberOfGuest() <= roomRepository.getReferenceById(dbBooking.get().getRoom().getId()).getMaxGuest()) {
                Order dbOrder = orderRepository.getReferenceById(dbBooking.get().getOrder().getId());
                dbOrder.setItems(dbBooking.get().getOrder().getItems());
                orderRepository.save(dbOrder);
                bookingLogger.info("Customer updated booking with id:" + dbBooking.get().getId());
                return bookingRepository.save(dbBooking.get());
            }else{
                throw new BusinessException("Room is to small for the ammount of guests",200);
            }
        }else{
            throw new NoSuchElementException("No booking with that booking id");
        }
    }


    public boolean checkAvailability(Booking booking){
        Optional<Booking> occupied;

        if(booking.isDinner()) {
             occupied = bookingRepository.findByBookingDateAndRoomAndDinnerIsTrue(booking.getBookingDate(), booking.getRoom());
        } else if (booking.isLunch()) {
            occupied = bookingRepository.findByBookingDateAndRoomAndLunchIsTrue(booking.getBookingDate(),booking.getRoom());

        }else{
            throw  new DataIntegrityViolationException("Incomplete  Booking, Dinner or Lunch must be selected  ");
        }

        if(occupied.isPresent()){
            if (occupied.get().getId().equals(booking.getId())){
                return true;
            }else
                return false;

        }


        return true;



    }
    public void updateTotalPrice(Booking booking){
        booking.getOrder().updateTotalPrice();
    }

}
