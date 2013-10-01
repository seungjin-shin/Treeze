package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.Note_;
import com.hansung.treeze.model.Quiz;
import com.hansung.treeze.model.Quiz_;

public class QuizSpecifications {
	

	public static Specification<Quiz> isQuizNodeId(final String nodeId){
		return new Specification<Quiz>() {
			@Override
			public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.<String>get(Quiz_.nodeId), nodeId);
			}
		};
	}

	public static Specification<Quiz> isThisQuiz (final Long classId,final String quizId){
		return new Specification<Quiz>() {
			@Override
			public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return    cb.and(cb.equal(root.<Long>get(Quiz_.classId), classId),
					      cb.equal(root.<String>get(Quiz_.quizId), quizId));
			}
	
		};
	}

}
