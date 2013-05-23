package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class) 
public class User_ {
	
	
	public static volatile SingularAttribute<User, Integer> userImgId;
	public static volatile SingularAttribute<User, String> userName;
	public static volatile SingularAttribute<User, String> userEmail;
	
}
