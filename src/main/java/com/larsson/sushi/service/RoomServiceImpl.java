package com.larsson.sushi.service;

import com.larsson.sushi.model.Room;
import com.larsson.sushi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class RoomServiceImpl implements RoomService{

    @Autowired
    RoomRepository repository;

    @Override
    public Room updateRoom(Room room) {
        if(repository.existsById(room.getId())) {
            return repository.save(room);
        }
        else throw new NoSuchElementException("No room found with id:" + room.getId());
    }
}
