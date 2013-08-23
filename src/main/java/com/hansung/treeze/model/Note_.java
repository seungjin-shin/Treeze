package com.hansung.treeze.model;

import javax.persistence.Column;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Note.class) 
public class Note_ {
	

	public static volatile SingularAttribute<Note, Long> classId;
	public static volatile SingularAttribute<Note, String> nodeId;
	public static volatile SingularAttribute<Note, String> contents;
	public static volatile SingularAttribute<Note, String> userEmail;
	
	
	
}
