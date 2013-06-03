package com.hansung.treeze.service;

import com.hansung.treeze.model.ClassInfo;


public interface ClassInfoService {

	ClassInfo saveClass(ClassInfo classInfo);
	Object getClassInfoes(Long lectureId);

}
