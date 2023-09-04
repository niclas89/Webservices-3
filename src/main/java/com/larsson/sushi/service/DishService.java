package com.larsson.sushi.service;

import com.larsson.sushi.model.Dish;

import java.util.List;

public interface DishService {

    public List<Dish> getAllDishes();

    public Dish addDish(Dish dish);

    public Boolean deleteDish(Long id);
}
