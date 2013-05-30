package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Class;
import com.hansung.treeze.model.Class_;

public class ClassSpecitications {
	
	public static Specification<Class> isMyClass(String lectureName, String professorEmail){
		return new Specification<Class>() {
			@Override
			public Predicate toPredicate(Root<Class> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Class_.mindmapXML), mindmapXML);
			}
		};
	}
}
