package com.repository;

import com.domain.Room;
import com.model.bo.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer>, InsertUpdateRepository<Room>{

    Optional<Room> findByIdAndStatus(Integer id, RoomStatus status);
}
