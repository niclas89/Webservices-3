package com.larsson.sushi.repository;

import com.larsson.sushi.model.Booking;
import com.larsson.sushi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {




    Optional<Booking> findByBookingDateAndRoomAndLunchIsTrue(String bookingDate, Room room);

    Optional<Booking> findByBookingDateAndRoomAndDinnerIsTrue(String bookingDate, Room room);

   List<Booking>  findAllByCustomer_Id(Long id);
}
