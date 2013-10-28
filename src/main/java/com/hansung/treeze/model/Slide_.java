package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Slide.class)
public class Slide_ {
	public static volatile SingularAttribute<Slide, Long> classId;
	public static volatile SingularAttribute<Slide, Integer> page;
	public static volatile SingularAttribute<Slide, String> filename;

}
