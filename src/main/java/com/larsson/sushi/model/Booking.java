package com.larsson.sushi.model;


import jakarta.persistence.*;
import java.text.SimpleDateFormat;


@Entity
@Table(name = "booking")
public class Booking {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_date")
    private String bookingDate;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Customer customer;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Order order;

    @Column(name = "lunch")
    private boolean lunch;

    @Column(name = "dinner")
    private boolean dinner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String newBookingDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        this.bookingDate = newBookingDate;
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
    }

    public boolean isDinner() {
        return dinner;
    }

    public void setDinner(boolean dinner) {
        this.dinner = dinner;
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

    public Booking() {
    }

    public Booking(Long id, String bookingDate, Room room, Customer customer,Order order, boolean lunch, boolean dinner) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.room = room;
        this.customer = customer;
        this.lunch = lunch;
        this.dinner = dinner;
    }
}
