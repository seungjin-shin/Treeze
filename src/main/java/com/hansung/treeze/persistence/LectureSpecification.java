package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.model.Lecture_;

public class LectureSpecitications {
	
	public static Specification<Lecture> islectureName(String lectureName){
		return new Specification<Lecture>() {
			@Override
			public Predicate toPredicate(Root<Class> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Lecture_.lectureName), lectureName);
			}
		};
	}


	public static Specification<Lecture> isMyLecture (String lectureName, String professorEmail){
		return new Specification<Lecture>() {
			@Override
			public Predicate toPredicate(Root<Lecture> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Lecture_.mindmapXML), mindmapXML);
			}
		};
	}
}
