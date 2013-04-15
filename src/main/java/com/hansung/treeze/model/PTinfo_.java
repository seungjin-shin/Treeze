package com.hansung.treeze.model;

import javax.persistence.metamodel.StaticMetamodel;
import javax.persistence.metamodel.SingularAttribute;

@StaticMetamodel(PTinfo.class)
public class PTinfo_ {

	public static volatile SingularAttribute<PTinfo, Integer> ptId;
	public static volatile SingularAttribute<PTinfo, Integer> imgCnt;
	public static volatile SingularAttribute<PTinfo, String> nodeName;
}
