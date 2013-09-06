package com.hansung.treeze.service;

import com.hansung.treeze.model.Course;


public interface CourseService {
	
	Course saveCourse(Course course);
	Course findMyCouse(Course course);
	Object findMyCourses(String studentEmail);
	void deleteCourse(Course course);

}
