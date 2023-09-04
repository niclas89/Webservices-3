package com.larsson.sushi.service;

import com.larsson.sushi.model.Booking;

import java.util.List;

public interface BookingService {

    Boolean newBooking(Booking newBooking);

    List<Booking>  allBookingsByCustomerId(Long id);

    Booking upDateBooking(Booking booking);


}
