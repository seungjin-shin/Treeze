package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.model.Ticket_;

public class TicketSpecifications {
	
	public static Specification<Ticket> isTicketsOnParentNodeId(final Long classId, final String parentNodeId){
		return new Specification<Ticket>() {

			@Override
			public Predicate toPredicate(Root<Ticket> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.and(cb.equal(root.<Long>get(Ticket_.classId), classId),cb.equal(root.<String>get(Ticket_.parentNodeId), parentNodeId));
			}
		};
	}
	
	public static Specification<Ticket> isTicketsOnClass(final Long classId){
		return new Specification<Ticket>() {

			@Override
			public Predicate toPredicate(Root<Ticket> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				// TODO Auto-generated method stub
				return cb.and(cb.equal(root.<Long>get(Ticket_.classId), classId));
			}
		};
	}
	

}
