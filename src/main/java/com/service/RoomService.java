package com.service;

import com.model.dto.RoomDto;
import com.model.request.RoomRequest;

public interface RoomService {

    RoomDto create(String token, RoomRequest roomRequest);

    RoomDto getRoomById(Integer roomId);
}
