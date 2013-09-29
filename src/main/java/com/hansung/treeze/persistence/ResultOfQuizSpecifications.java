package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.ResultOfQuiz;
import com.hansung.treeze.model.ResultOfQuiz_;

public class ResultOfQuizSpecifications {
	

	public static Specification<ResultOfQuiz> isQuizNodeId(final String nodeId){
		return new Specification<ResultOfQuiz>() {
			@Override
			public Predicate toPredicate(Root<ResultOfQuiz> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(ResultOfQuiz_.nodeId), nodeId);
			}
		};
	}

	public static Specification<ResultOfQuiz> isThisQuiz (final Long classId,final String quizId){
		return new Specification<ResultOfQuiz>() {
			@Override
			public Predicate toPredicate(Root<ResultOfQuiz> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return    cb.and(cb.equal(root.<Long>get(ResultOfQuiz_.classId), classId),
					      cb.equal(root.<String>get(ResultOfQuiz_.quizId), quizId));
			}
	
		};
	}
}
