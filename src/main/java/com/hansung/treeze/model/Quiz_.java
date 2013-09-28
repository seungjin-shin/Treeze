package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Quiz.class)
public class Quiz_ {

	public static volatile SingularAttribute<Quiz, Long> classId;
	public static volatile SingularAttribute<Quiz, String> nodeId;
	public static volatile SingularAttribute<Quiz, String> quizId;
	public static volatile SingularAttribute<Quiz, String> type;
	public static volatile SingularAttribute<Quiz, String> contents;
	public static volatile SingularAttribute<Quiz, String> answerContents;
	public static volatile SingularAttribute<Quiz, Integer> answerNumber;
	public static volatile SingularAttribute<Quiz, String> example1;
	public static volatile SingularAttribute<Quiz, String> example2;
	public static volatile SingularAttribute<Quiz, String> example3;
	public static volatile SingularAttribute<Quiz, String> example4;
	public static volatile SingularAttribute<Quiz, String> example5;
	
}
