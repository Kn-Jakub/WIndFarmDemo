package sk.fri.uniza.api;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Phone.class)
public abstract class Phone_ {

	public static volatile SingularAttribute<Phone, Person> owner;
	public static volatile SingularAttribute<Phone, String> number;
	public static volatile SingularAttribute<Phone, Long> id;

	public static final String OWNER = "owner";
	public static final String NUMBER = "number";
	public static final String ID = "id";

}

