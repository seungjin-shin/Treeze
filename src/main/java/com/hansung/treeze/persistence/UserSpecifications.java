package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.User;
import com.hansung.treeze.model.User_;

public class UserSpecifications {
	
	public static Specification<User> isEmail(final String email){
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.<String>get(User_.userEmail), email);
			}
		};
	}
	
	public static Specification<User> isIdentificationNumber(final Integer identificationNumber){
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.<Integer>get(User_.identificationNumber), identificationNumber);
			}
		};
	}
}
