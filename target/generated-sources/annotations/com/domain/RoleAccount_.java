package com.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RoleAccount.class)
public abstract class RoleAccount_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<RoleAccount, String> accountId;
	public static volatile SingularAttribute<RoleAccount, String> roleName;
	public static volatile SingularAttribute<RoleAccount, Integer> id;

	public static final String ACCOUNT_ID = "accountId";
	public static final String ROLE_NAME = "roleName";
	public static final String ID = "id";

}

