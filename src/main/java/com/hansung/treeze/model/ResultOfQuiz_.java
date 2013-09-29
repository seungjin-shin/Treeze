package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ResultOfQuiz.class)
public class ResultOfQuiz_ {

	public static volatile SingularAttribute<ResultOfQuiz, Long> classId;
	public static volatile SingularAttribute<ResultOfQuiz, String> quizId;
	public static volatile SingularAttribute<ResultOfQuiz, String> nodeId;
	public static volatile SingularAttribute<Quiz, String> type;
	public static volatile SingularAttribute<ResultOfQuiz, String> answerContents;
	public static volatile SingularAttribute<ResultOfQuiz, Integer> answerNumber;
	public static volatile SingularAttribute<ResultOfQuiz, String> userEmail;
	
	
}
