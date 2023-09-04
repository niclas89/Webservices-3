package com.larsson.sushi.service;

import com.larsson.sushi.model.Dish;
import com.larsson.sushi.repository.DishRepossitory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DishServiceImpl implements DishService{

    private static final Logger DishLogger = Logger.getLogger(Dish.class);

    @Autowired
    private DishRepossitory repository;

    @Override
    public List<Dish> getAllDishes() {
        return repository.findAll();
    }

    @Override
    public Dish addDish(Dish dish) {
        DishLogger.info("Admin added new dish:" + dish.getName());
        return repository.save(dish);
    }

    @Override
    public Boolean deleteDish(Long id) {

        if(repository.existsById(id)){
            repository.deleteById(id);
            DishLogger.info("Admin deleted dish, id:" + id);
            return true;
        }else
            throw  new NoSuchElementException("No dish found with id:" + id);


    }
}
