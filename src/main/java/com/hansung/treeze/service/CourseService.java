package com.hansung.treeze.service;

import java.util.List;

import com.hansung.treeze.model.Course;


public interface CourseService {
	
	Course saveCourse(Course course);
	Course findMyCouse(Course course);
	List<Course> findMyCourses(String studentEmail);
	void deleteCourse(Course course);

}
