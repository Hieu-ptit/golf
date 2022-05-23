package com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PropertySetting.class)
public abstract class PropertySetting_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<PropertySetting, Float> distance;
	public static volatile SingularAttribute<PropertySetting, String> name;
	public static volatile SingularAttribute<PropertySetting, String> description;
	public static volatile SingularAttribute<PropertySetting, Integer> greenSpeed;
	public static volatile SingularAttribute<PropertySetting, Integer> id;
	public static volatile SingularAttribute<PropertySetting, Float> holeCup;
	public static volatile SingularAttribute<PropertySetting, Float> height;

	public static final String DISTANCE = "distance";
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String GREEN_SPEED = "greenSpeed";
	public static final String ID = "id";
	public static final String HOLE_CUP = "holeCup";
	public static final String HEIGHT = "height";

}

