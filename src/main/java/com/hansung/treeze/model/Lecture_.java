package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Lecture.class) 
public class Lecture_ {

	public static volatile SingularAttribute<Lecture, String> professorEmail;
	public static volatile SingularAttribute<Lecture, String> professorName;
	public static volatile SingularAttribute<Lecture, String> lectureName;
	public static volatile SingularAttribute<Lecture, Long> lectureId;
	public static volatile SingularAttribute<Lecture, Integer> stateOfLecture;
}
