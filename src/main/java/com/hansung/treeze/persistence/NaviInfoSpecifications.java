package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.NaviInfo;
import com.hansung.treeze.model.NaviInfo_;

public class NaviInfoSpecifications {
	

	public static Specification<NaviInfo> isClassId (final Long classId){
		return new Specification<NaviInfo>() {
			@Override
			public Predicate toPredicate(Root<NaviInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Long>get(NaviInfo_.classId), classId);
			}
		};
	}
}
