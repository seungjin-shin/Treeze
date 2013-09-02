package com.hansung.treeze.service;

import com.hansung.treeze.model.ClassInfo;


public interface ClassInfoService {

	ClassInfo saveClass(ClassInfo classInfo);
	void deleteClass(ClassInfo classInfo);
	ClassInfo findClass(Long classId);
	Object getClassInfoes(Long lectureId);

}
