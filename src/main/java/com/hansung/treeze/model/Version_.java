package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Version.class) 
public class Version_ {
	
	
	public static volatile SingularAttribute<Version, String> versionId;
	public static volatile SingularAttribute<Version, String> userType;
}
