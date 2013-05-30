package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Mindmap;
import com.hansung.treeze.model.Mindmap_;

public class MindmapSpecitications {
	
	public static Specification<Mindmap> isClassId(final int classId){
		return new Specification<Mindmap>() {
			@Override
			public Predicate toPredicate(Root<Mindmap> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<Integer>get(Mindmap_.classId), classId);
			}
		};
	}
}
