package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.ClassInfo_;
import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.model.Lecture_;

public class LectureSpecifications {
	
	public static Specification<Lecture> isLectureId(final Long lectureId){
		return new Specification<Lecture>() {

			@Override
			public Predicate toPredicate(Root<Lecture> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.<Long>get(Lecture_.lectureId), lectureId);
			}
		};
	}


	public static Specification<Lecture> isMyLecture (final String professorEmail){
		return new Specification<Lecture>() {
			@Override
			public Predicate toPredicate(Root<Lecture> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Lecture_.professorEmail), professorEmail);
			}
		};
	}
}
