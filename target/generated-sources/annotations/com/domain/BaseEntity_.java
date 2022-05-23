package com.domain;

import java.time.ZonedDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseEntity.class)
public abstract class BaseEntity_ {

	public static volatile SingularAttribute<BaseEntity, ZonedDateTime> createdAt;
	public static volatile SingularAttribute<BaseEntity, ZonedDateTime> updatedAt;

	public static final String CREATED_AT = "createdAt";
	public static final String UPDATED_AT = "updatedAt";

}

