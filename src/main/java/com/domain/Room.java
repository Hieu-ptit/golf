package com.domain;

import com.converter.JsonConverterGolfCourseObject;
import com.converter.JsonConverterPlayer;
import com.domain.embedded.GolfCourseObject;
import com.domain.embedded.PlayerObject;
import com.model.bo.RoomStatus;
import com.model.request.PlayerRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "room")
@Table(indexes = {
//    @Index(name="idx_driver_phone", columnList = "username"),
//    @Index(name="idx_driver_status", columnList = "status")
})
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Room extends BaseEntity{

    @Id
    @SequenceGenerator(name = "room_id_seq", sequenceName = "room_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "room_id_seq")
    Integer id;

    @Column(columnDefinition = "timestamp")
    LocalDate startDate;

    @Column(columnDefinition = "int default 0", nullable = false)
    Integer golfCourseId;

    @Column(columnDefinition = "text")
    @Convert(converter = JsonConverterGolfCourseObject.class)
    GolfCourseObject golfCourseObject;

    @Column(columnDefinition = "varchar(50)", nullable = false)
    String name;

    @Column(columnDefinition = "varchar(20) default 'ACTIVE'", nullable = false)
    @Enumerated(EnumType.STRING)
    RoomStatus status;

    @Column(columnDefinition = "text")
    @Convert(converter = JsonConverterPlayer.class)
    List<PlayerObject> player;
}
