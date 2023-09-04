package com.larsson.sushi.repository;

import com.larsson.sushi.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepossitory extends JpaRepository<Dish,Long> {
}
