package com.larsson.sushi.controller;


import com.larsson.sushi.model.*;
import com.larsson.sushi.service.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {


    private static final Logger customerLogger = Logger.getLogger(CustomerController.class);
    @Autowired
    DishService dishService;

    @Autowired
    ItemServiceImpl itemService;

    @Autowired
    OrderService orderService;



    @Autowired
    BookingServiceImpl bookingService;

    @GetMapping("/sushis")
    public List<Dish> getSushis(){
        customerLogger.info("Customer checking menu");
        return dishService.getAllDishes();
    }

    @GetMapping("/item/{id}")
    public Item getItem(@PathVariable Long id){
        return itemService.getItem(id);
    }

    @GetMapping("/order/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getOrder(id);
    }

    @GetMapping("/mybookings/{id}")
    public List<Booking> getBookings(@PathVariable Long id){
        return bookingService.allBookingsByCustomerId(id);
    }

    @PostMapping("/bookroom")
    public boolean newBooking(@RequestBody Booking booking){
        return bookingService.newBooking(booking);
    }

    @PutMapping("/updatebooking")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking){
        return new ResponseEntity<>(bookingService.upDateBooking(booking),HttpStatus.OK);
    }


}
