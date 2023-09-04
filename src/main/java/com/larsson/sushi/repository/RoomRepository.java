package com.larsson.sushi.repository;

import com.larsson.sushi.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
