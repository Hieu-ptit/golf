package com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AccountProduct.class)
public abstract class AccountProduct_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<AccountProduct, Integer> accountId;
	public static volatile SingularAttribute<AccountProduct, Integer> productId;
	public static volatile SingularAttribute<AccountProduct, Integer> id;

	public static final String ACCOUNT_ID = "accountId";
	public static final String PRODUCT_ID = "productId";
	public static final String ID = "id";

}

