package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Note;
import com.hansung.treeze.model.Note_;

public class NoteSpecifications {
	


	public static Specification<Note> isMyNote (final Long classId,final String userEmail,final String position){
		return new Specification<Note>() {
			@Override
			public Predicate toPredicate(Root<Note> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.and(cb.equal(root.<Long>get(Note_.classId), classId),
					      cb.equal(root.<String>get(Note_.userEmail), userEmail),
					      cb.equal(root.<String>get(Note_.position), position));
			}
		};
	}
}
