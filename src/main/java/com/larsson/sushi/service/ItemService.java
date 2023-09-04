package com.larsson.sushi.service;

import com.larsson.sushi.model.Item;

import java.util.List;

public interface ItemService {

    Item getItem(Long id);

    List<Item> getAllItems();
}
