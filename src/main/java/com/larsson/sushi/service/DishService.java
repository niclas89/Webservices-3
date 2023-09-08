package com.larsson.sushi.service;

import com.larsson.sushi.model.Dish;

import java.util.List;

public interface DishService {

    List<Dish> getAllDishes();

   Dish addDish(Dish dish);

    Boolean deleteDish(Long id);
}
