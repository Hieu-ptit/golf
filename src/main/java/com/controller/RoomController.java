package com.controller;

import com.model.dto.Response;
import com.model.dto.RoomDto;
import com.model.request.RoomRequest;
import com.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/room")
@Validated
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    public Response<RoomDto> getRoomById(@PathVariable("id") Integer roomId) {
        return Response.ofSucceeded(roomService.getRoomById(roomId));
    }

    @PostMapping
    public Response<RoomDto> create(@RequestHeader("Authorization") String token,  @Valid @RequestBody RoomRequest roomRequest) {
        return Response.ofSucceeded(roomService.create(token, roomRequest));
    }
}
