package com.larsson.sushi.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.LocalDate;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "booking")
public class Booking {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private LocalDate bookingDate;


    @Column(name = "number_of_guest")
    private Short numberOfGuest;

    @ManyToOne( fetch = FetchType.EAGER)
    private Room room;


    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @ManyToOne( fetch = FetchType.EAGER )
    private Order order;

    @Column(name = "lunch")
    private boolean lunch;

    @Column(name = "dinner")
    private boolean dinner;


    public Booking() {
    }

    public Booking(Long id, LocalDate bookingDate, Room room, Short numberOfGuest, Customer customer, Order order, boolean lunch, boolean dinner) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.room = room;
        this.numberOfGuest = numberOfGuest;
        this.customer = customer;
        this.order = order;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public Short getNumberOfGuest() {
        return numberOfGuest;
    }

    public void setNumberOfGuest(Short numberOfGuest) {
        this.numberOfGuest = numberOfGuest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {

        return bookingDate;
    }

    public void setBookingDate(LocalDate newBookingDate) {
            if(newBookingDate.isAfter(LocalDate.of(2020,9,14)) && !newBookingDate.isAfter(LocalDate.now().plusMonths(3))){
                this.bookingDate = newBookingDate;
            }else{
                throw new DataIntegrityViolationException("Incorrect date ");
            }


    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isLunch() {
        return lunch;
    }

    public void setLunch(boolean lunch) {
        this.lunch = lunch;
        if(lunch){
            setDinner(false);
        }
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
        if(dinner){
            setLunch(false);
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void updateValues(Booking updatedBooking){
        this.bookingDate = updatedBooking.getBookingDate();
        this.dinner = updatedBooking.isDinner();
        this.lunch = updatedBooking.isLunch();
        this.room = updatedBooking.getRoom();
        this.customer = updatedBooking.getCustomer();
        this.order = updatedBooking.getOrder();

    }



    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", room=" + room +
                ", customer=" + customer +
                ", order=" + order +
                ", lunch=" + lunch +
                ", dinner=" + dinner +
                '}';
    }
}
