package com.hansung.treeze.service;

import com.hansung.treeze.model.Class;


public interface ClassService {

	Class saveClass(Class classInfo);
	Object getClasses(String lectureName, String professorEmail);

}
