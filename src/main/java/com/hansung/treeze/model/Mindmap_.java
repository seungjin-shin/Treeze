package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Mindmap.class) 
public class Mindmap_ {

	public static volatile SingularAttribute<Mindmap, Long> classId; 
	public static volatile SingularAttribute<Mindmap, String> mindmapXML;
}
