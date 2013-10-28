package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Slide;
import com.hansung.treeze.model.Slide_;

public class SlideSpecifications {
	public static Specification<Slide> isClassId(final Long classId) {
		return new Specification<Slide>() {
			@Override
			public Predicate toPredicate(Root<Slide> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long>get(Slide_.classId), classId);
			}
		};
	}
	
	public static Specification<Slide> isPage(final Integer page) {
		return new Specification<Slide>() {
			@Override
			public Predicate toPredicate(Root<Slide> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer>get(Slide_.page), page);
			}
		};
	}
	
	public static Specification<Slide> isPath(final String path) {
		return new Specification<Slide>() {
			@Override
			public Predicate toPredicate(Root<Slide> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String>get(Slide_.filename), path);
			}
		};
	}
	
	public static Specification<Slide> isSlideAndPage(final Long classId, final Integer page) {
		return new Specification<Slide>() {
			@Override
			public Predicate toPredicate(Root<Slide> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				return cb.and(cb.equal(root.<Long>get(Slide_.classId), classId),
						cb.equal(root.<Integer>get(Slide_.page), page));
			}
		};
	}
}
