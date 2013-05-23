package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Feedback.class)
public class Feedback_ {

	public static volatile SingularAttribute<Feedback, Integer> classId;
	public static volatile SingularAttribute<Feedback, Integer> position[];
	public static volatile SingularAttribute<Feedback, String> contents;
	public static volatile SingularAttribute<Feedback, String> userEmail;
}
