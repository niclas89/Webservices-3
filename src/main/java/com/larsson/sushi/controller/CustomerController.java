package com.larsson.sushi.controller;


import com.larsson.sushi.model.*;
import com.larsson.sushi.service.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {


    private static final Logger customerLogger = Logger.getLogger(CustomerController.class);
    @Autowired
    DishService dishService;


    @Autowired
    OrderService orderService;

    @Value("${redirectUrl}")
    private String redirectUrl;



    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping("/sushis")
    public ResponseEntity<List<Dish>> getSushis(){
        customerLogger.info("Customer checking menu");
        return ResponseEntity.ok(dishService.getAllDishes());
    }




    @GetMapping("/mybookings/{id}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.allBookingsByCustomerId(id));
    }

    @PostMapping("/bookroom")
    public void newBooking(@RequestBody Booking booking, HttpServletResponse response) throws IOException {
        Long id = bookingService.newBooking(booking).getId();
         response.sendRedirect("http://localhost:8585/api/v3/booking/"+id);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.getBooking(id));
    }

    @PutMapping("/updatebooking")
    public void updateBooking(@RequestBody Booking booking,HttpServletResponse response) throws IOException {
        Long id = bookingService.upDateBooking(booking).getId();
        response.sendRedirect("http://localhost:8585/api/v3/booking/"+id);
    }


}
