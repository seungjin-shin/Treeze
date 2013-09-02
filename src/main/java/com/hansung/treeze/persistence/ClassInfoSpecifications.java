package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.ClassInfo_;

public class ClassInfoSpecifications {
	
	public static Specification<ClassInfo> isLectureId(final Long lectureId){
		return new Specification<ClassInfo>() {
			@Override
			public Predicate toPredicate(Root<ClassInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Long>get(ClassInfo_.lectureId), lectureId);
			}
		};
	}
	public static Specification<ClassInfo> isProfessorEmail(final String professorEmail){
		return new Specification<ClassInfo>() {
			@Override
			public Predicate toPredicate(Root<ClassInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(ClassInfo_.professorEmail), professorEmail);
			}
		};
	}
	public static Specification<ClassInfo> isClassId(final Long classId){
		return new Specification<ClassInfo>() {
			@Override
			public Predicate toPredicate(Root<ClassInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Long>get(ClassInfo_.classId), classId);
			}
		};
	}
		
}
