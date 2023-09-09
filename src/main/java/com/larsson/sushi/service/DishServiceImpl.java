package com.larsson.sushi.service;

import com.larsson.sushi.exceptionHandling.BusinessException;
import com.larsson.sushi.model.Dish;
import com.larsson.sushi.model.Item;
import com.larsson.sushi.repository.DishRepository;
import com.larsson.sushi.repository.ItemRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DishServiceImpl implements DishService{

    private static final Logger DishLogger = Logger.getLogger(Dish.class);

    @Autowired
    private DishRepository repository;

    @Autowired
    private ItemRepository itemRepository;

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
            if(!itemRepository.existsByDish_Id(id)) {
                repository.deleteById(id);
                DishLogger.info("Admin deleted dish, id:" + id);
                return true;
            }else throw new BusinessException("must delete orders containing dish",200);
        }else
            throw  new NoSuchElementException("No dish found with id:" + id);


    }
}
