package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Note;
import com.hansung.treeze.model.Note_;

public class NoteSpecitications {
	


	public static Specification<Note> isMyNote (int classId,String userEmail,String position){
		return new Specification<Note>() {
			@Override
			public Predicate toPredicate(Root<Note> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Note_.classId), classId);
			}
		};
	}
}
