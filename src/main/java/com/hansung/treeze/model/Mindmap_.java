package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mindmap.class)
public class Mindmap_ {
	
	public static volatile SingularAttribute<Mindmap, String> mindmapPath;
	public static volatile SingularAttribute<Mindmap, Integer> ptId;

}
