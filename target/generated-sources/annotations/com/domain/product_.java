package com.domain;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(product.class)
public abstract class product_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<product, BigDecimal> price;
	public static volatile SingularAttribute<product, String> name;
	public static volatile SingularAttribute<product, Integer> id;
	public static volatile SingularAttribute<product, Integer> categoryId;

	public static final String PRICE = "price";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String CATEGORY_ID = "categoryId";

}

