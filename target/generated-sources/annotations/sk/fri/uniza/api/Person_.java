package sk.fri.uniza.api;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ extends sk.fri.uniza.core.User_ {

	public static volatile SingularAttribute<Person, String> FirstName;
	public static volatile SingularAttribute<Person, String> LastName;
	public static volatile SingularAttribute<Person, String> email;
	public static volatile SetAttribute<Person, Phone> phoneNumbers;

	public static final String FIRST_NAME = "FirstName";
	public static final String LAST_NAME = "LastName";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBERS = "phoneNumbers";

}

