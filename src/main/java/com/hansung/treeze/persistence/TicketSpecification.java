package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.model.Ticket_;

public class TicketSpecitications {
	
	public static Specification<Ticket> isclassId(int classId){
		return new Specification<Ticket>() {
			@Override
			public Predicate toPredicate(Root<Class> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Ticket_.classId), classId);
			}
		};
	}
}
