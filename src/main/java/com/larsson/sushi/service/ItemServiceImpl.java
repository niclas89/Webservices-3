package com.larsson.sushi.service;

import com.larsson.sushi.model.Item;
import com.larsson.sushi.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    ItemRepository repository;


    @Override
    public Item getItem(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public List<Item> getAllItems() {
        return repository.findAll();
    }
}
