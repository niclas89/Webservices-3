package com.larsson.sushi.controller;



import com.larsson.sushi.model.Dish;
import com.larsson.sushi.model.Room;
import com.larsson.sushi.service.CustomerService;
import com.larsson.sushi.service.DishService;
import com.larsson.sushi.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class AdminController {

    @Autowired
    CustomerService customerService;

    @Autowired
    DishService dishService;

    @Autowired
    RoomService roomService;

    @GetMapping("/customers")
    public ResponseEntity<Object> getAllCustomer(){
        return ResponseEntity.ok(customerService.getAllCustomers());
    }
    @PostMapping("/add-dish")
    public ResponseEntity<Object>addDish(@RequestBody Dish newDish){
        return ResponseEntity.ok(dishService.addDish(newDish));
    }

    @DeleteMapping("/deletedish")
    public ResponseEntity<Object>deleteDish(@RequestBody Dish dish){
        return ResponseEntity.ok(dishService.deleteDish(dish.getId()));
    }

    @PutMapping("/updateroom")
    public ResponseEntity<Object> updateRoom(@RequestBody Room updatedRoom){
        return ResponseEntity.ok(roomService.updateRoom(updatedRoom));
    }



}
