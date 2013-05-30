package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Class;
import com.hansung.treeze.model.Class_;

public class ClassSpecifications {
	
	public static Specification<Class> isMyClass(final String lectureName,final String professorEmail){
		return new Specification<Class>() {
			@Override
			public Predicate toPredicate(Root<Class> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.and(cb.equal(root.<String>get(Class_.lectureName), lectureName),
					      cb.equal(root.<String>get(Class_.professorEmail), professorEmail));
			}
		};
	}
}
