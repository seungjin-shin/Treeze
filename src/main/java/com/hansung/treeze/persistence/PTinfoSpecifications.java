package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.PTinfo;
import com.hansung.treeze.model.PTinfo_;

public class PTinfoSpecifications {
	
	public static Specification<PTinfo> isPTId(final Integer ptId){
		return new Specification<PTinfo>() {
			@Override
			public Predicate toPredicate(Root<PTinfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Integer>get(PTinfo_.ptId), ptId);
			}
		};
	}
}
