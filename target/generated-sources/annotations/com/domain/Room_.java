package com.domain;

import com.domain.embedded.PlayerObject;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Room.class)
public abstract class Room_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<Room, Integer> golfCourseId;
	public static volatile SingularAttribute<Room, String> name;
	public static volatile SingularAttribute<Room, Integer> id;
	public static volatile SingularAttribute<Room, LocalDate> startDate;
	public static volatile ListAttribute<Room, PlayerObject> player;

	public static final String GOLF_COURSE_ID = "golfCourseId";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String PLAYER = "player";

}

