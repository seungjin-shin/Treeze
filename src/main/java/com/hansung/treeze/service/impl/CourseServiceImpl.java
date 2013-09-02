package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Course;
import com.hansung.treeze.persistence.CourseRepository;
import com.hansung.treeze.persistence.CourseSpecifications;
import com.hansung.treeze.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired private CourseRepository courseRepository;



	@Override
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepository.save(course);
	}
		@Override
	public Object findMyCourses(String studentEmail) {
		// TODO Auto-generated method stub
		return courseRepository.findAll(Specifications.where(CourseSpecifications.isMyCourse(studentEmail)));
		
	}
		@Override
		public void deleteCourse(Course course) {
			// TODO Auto-generated method stub
			courseRepository.delete(course);
		}

}
