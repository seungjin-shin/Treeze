package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Timer;
import com.hansung.treeze.model.Timer_;

public class TimerSpecifications {
	
	
	public static Specification<Timer> isThisTimer(final Long classId){
		return new Specification<Timer>() {

			@Override
			public Predicate toPredicate(Root<Timer> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.equal(root.<Long>get(Timer_.classId), classId);
			}
		};
	}
	

}
