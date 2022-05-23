package com.service.impl;

import com.domain.GolfCourse;
import com.domain.Room;
import com.domain.embedded.GolfCourseObject;
import com.domain.embedded.PlayerObject;
import com.exception.BusinessException;
import com.mapper.RoomMapper;
import com.model.dto.DetailUser;
import com.model.dto.RoomDto;
import com.model.request.PlayerRequest;
import com.model.request.RoomRequest;
import com.repository.GolfCourseRepository;
import com.repository.RoomRepository;
import com.security.jwt.TokenProvider;
import com.service.RoomService;
import com.util.ErrorCode;
import com.util.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    private final TokenProvider tokenProvider;

    private final GolfCourseRepository golfCourseRepository;

    @Override
    public RoomDto create(String token, RoomRequest roomRequest) {
        DetailUser detailUser = getToken(token);
        GolfCourse golfCourse = golfCourseRepository.findById(roomRequest.getGolfCourseId()).orElseThrow(()-> new BusinessException(ErrorCode.GOLF_COURSE_NOT_FOUND, ErrorCode.GOLF_COURSE_NOT_FOUND.getMessage()));
        PlayerObject playerObject = new PlayerObject().setName(detailUser.getName()).setId(detailUser.getUserId());
        GolfCourseObject golfCourseObject = new GolfCourseObject().setId(golfCourse.getId()).setName(golfCourse.getName());
        Room room = roomMapper.mapToEntity(roomRequest);
        room.setPlayer(List.of(playerObject));
        room.setGolfCourseObject(golfCourseObject);
        roomRepository.insert(room);
        return roomMapper.mapToDto(room);
    }

    @Override
    public RoomDto getRoomById(Integer roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(()-> new BusinessException(ErrorCode.ROOM_NOT_FOUND, ErrorMessage.ROOM_NOT_FOUND));
        return roomMapper.mapToDto(room);
    }

    private DetailUser getToken(String tokenRequest){
        String token = null;
        if (org.springframework.util.StringUtils.hasText(tokenRequest) && tokenRequest.startsWith("Bearer "))
            token = tokenRequest.substring(7);
        return tokenProvider.getClaim(token);
    }
}
