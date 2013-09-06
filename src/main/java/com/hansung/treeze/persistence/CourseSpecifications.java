package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Course;
import com.hansung.treeze.model.Course_;
import com.hansung.treeze.model.Note_;

public class CourseSpecifications {
	

	public static Specification<Course> isMyCourse (final String studentEmail){
		return new Specification<Course>() {
			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Course_.studentEmail), studentEmail);
			}
		};
	}
	
	public static Specification<Course> isMyCourse (final String studentEmail, final Long lectureId){
		return new Specification<Course>() {
			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				
				return cb.and(cb.equal(root.<String>get(Course_.studentEmail), studentEmail),
					      cb.equal(root.<Long>get(Course_.lectureId), lectureId));
			}
		};
	}
}
