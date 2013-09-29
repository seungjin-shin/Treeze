package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;
import com.hansung.treeze.persistence.ResultOfMultipleChoiceQuizRepository;
import com.hansung.treeze.persistence.ResultOfMultipleChoiceQuizSpecifications;
import com.hansung.treeze.service.ResultOfMultipleChoiceQuizService;

@Service
public class ResultOfMultipleChoiceQuizServiceImpl implements ResultOfMultipleChoiceQuizService{

	@Autowired private ResultOfMultipleChoiceQuizRepository resultOfMultipleChoiceQuizRepository;

	@Override
	public ResultOfMultipleChoiceQuiz saveResultOfMultipleChoiceQuiz(
			ResultOfMultipleChoiceQuiz resultOfMultipleChoiceQuiz) {
		// TODO Auto-generated method stub
		return resultOfMultipleChoiceQuizRepository.save(resultOfMultipleChoiceQuiz);
	}

	@Override
	public ResultOfMultipleChoiceQuiz getResultOfMultipleChoiceQuiz(
			Long classId, String quizId) {
		// TODO Auto-generated method stub
		return resultOfMultipleChoiceQuizRepository.findOne(Specifications
				.where(ResultOfMultipleChoiceQuizSpecifications.isThisQuiz(classId,quizId)));
	}



}