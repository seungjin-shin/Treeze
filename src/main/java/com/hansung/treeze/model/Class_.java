package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Class.class)
public class Class_ {
	
	public static volatile SingularAttribute<Class, Integer> classId;
	public static volatile SingularAttribute<Class, String> className;
	public static volatile SingularAttribute<Class, String>  classIP;
	public static volatile SingularAttribute<Class, Integer> port;
	public static volatile SingularAttribute<Class, String> lectureName;
	public static volatile SingularAttribute<Class, String> professorEmail;

	
}
