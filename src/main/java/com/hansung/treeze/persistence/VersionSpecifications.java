package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Version;
import com.hansung.treeze.model.Version_;

public class VersionSpecifications {
	
	public static Specification<Version> isUserType(final String userType){
		return new Specification<Version>() {

			@Override
			public Predicate toPredicate(Root<Version> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.<String>get(Version_.userType), userType);
			}
		};
	}
}
