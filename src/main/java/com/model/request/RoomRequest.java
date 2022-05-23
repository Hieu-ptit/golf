package com.model.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoomRequest {

    private String name;

    private Integer golfCourseId;
}
