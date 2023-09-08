package com.larsson.sushi.service;

import com.larsson.sushi.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingService {

    Booking newBooking(Booking newBooking);

    Booking getBooking(Long id);

    List<Booking>  allBookingsByCustomerId(Long id);

    Booking upDateBooking(Booking booking);


}
