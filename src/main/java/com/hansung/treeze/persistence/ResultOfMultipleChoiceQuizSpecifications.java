package com.hansung.treeze.persistence;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;
import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz_;

public class ResultOfMultipleChoiceQuizSpecifications {
	


	public static Specification<ResultOfMultipleChoiceQuiz> isThisQuiz (final Long classId,final String quizId){
		return new Specification<ResultOfMultipleChoiceQuiz>() {
			@Override
			public Predicate toPredicate(Root<ResultOfMultipleChoiceQuiz> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return    cb.and(cb.equal(root.<Long>get(ResultOfMultipleChoiceQuiz_.classId), classId),
					      cb.equal(root.<String>get(ResultOfMultipleChoiceQuiz_.quizId), quizId));
			}
	
		};
	}
}
