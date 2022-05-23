package com.domain;

import java.time.Instant;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Account.class)
public abstract class Account_ extends com.domain.BaseEntity_ {

	public static volatile SingularAttribute<Account, LocalDate> birthDay;
	public static volatile SingularAttribute<Account, String> password;
	public static volatile SingularAttribute<Account, String> address;
	public static volatile SingularAttribute<Account, String> langKey;
	public static volatile SingularAttribute<Account, Instant> resetDate;
	public static volatile SingularAttribute<Account, String> imageUrl;
	public static volatile SingularAttribute<Account, String> fullName;
	public static volatile SingularAttribute<Account, String> id;
	public static volatile SingularAttribute<Account, String> activationKey;
	public static volatile SingularAttribute<Account, String> resetKey;
	public static volatile SingularAttribute<Account, String> email;
	public static volatile SingularAttribute<Account, Integer> age;

	public static final String BIRTH_DAY = "birthDay";
	public static final String PASSWORD = "password";
	public static final String ADDRESS = "address";
	public static final String LANG_KEY = "langKey";
	public static final String RESET_DATE = "resetDate";
	public static final String IMAGE_URL = "imageUrl";
	public static final String FULL_NAME = "fullName";
	public static final String ID = "id";
	public static final String ACTIVATION_KEY = "activationKey";
	public static final String RESET_KEY = "resetKey";
	public static final String EMAIL = "email";
	public static final String AGE = "age";

}

