package com.larsson.sushi.controller;


import com.larsson.sushi.model.Customer;
import com.larsson.sushi.model.Dish;
import com.larsson.sushi.model.Room;
import com.larsson.sushi.service.CustomerService;
import com.larsson.sushi.service.DishService;
import com.larsson.sushi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    @Autowired
    CustomerService customerService;

    @Autowired
    DishService dishService;

    @Autowired
    RoomService roomService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }
    @PostMapping("/add-dish")
    public Dish addDish(@RequestBody Dish newDish){
        return dishService.addDish(newDish);
    }

    @DeleteMapping("/deletedish")
    public boolean deleteDish(@RequestBody Dish dish){
        return dishService.deleteDish(dish.getId());
    }

    @PutMapping("/updateroom")
    public Room updateRoom(@RequestBody Room updatedRoom){
        return roomService.updateRoom(updatedRoom);
    }



}
