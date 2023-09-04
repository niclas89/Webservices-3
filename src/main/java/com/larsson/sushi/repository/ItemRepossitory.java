package com.larsson.sushi.repository;

import com.larsson.sushi.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepossitory extends JpaRepository<Item,Long> {
}
