package sk.fri.uniza.core;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, byte[]> salt;
	public static volatile SetAttribute<User, String> roles;
	public static volatile SingularAttribute<User, Long> id;
	public static volatile SingularAttribute<User, byte[]> secrete;
	public static volatile SingularAttribute<User, String> userName;

	public static final String SALT = "salt";
	public static final String ROLES = "roles";
	public static final String ID = "id";
	public static final String SECRETE = "secrete";
	public static final String USER_NAME = "userName";

}

