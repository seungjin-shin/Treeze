package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Timer.class)
public class Timer_ {
	
	public static volatile SingularAttribute<Timer, Long> classId;
	public static volatile SingularAttribute<Timer, Boolean> start;
	public static volatile SingularAttribute<Timer, String> endTime;
}
