package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.ResultOfQuiz;
import com.hansung.treeze.persistence.ResultOfQuizRepository;
import com.hansung.treeze.persistence.ResultOfQuizSpecifications;
import com.hansung.treeze.service.ResultOfQuizService;

@Service
public class ResultOfQuizServiceImpl implements ResultOfQuizService{

	@Autowired private ResultOfQuizRepository resultOfQuizRepository;

	@Override
	public ResultOfQuiz saveResultOfQuiz(ResultOfQuiz resultOfQuiz) {
		// TODO Auto-generated method stub
		return resultOfQuizRepository.save(resultOfQuiz);
	}

	@Override
	public void deleteResultOfQuiz(ResultOfQuiz quiz) {
		// TODO Auto-generated method stub
		
		resultOfQuizRepository.delete(getResultOfQuiz(quiz.getClassId(),quiz.getQuizId()));
		
	}

	@Override
	public List<ResultOfQuiz> getResultOfQuizes(Long classId, String nodeId) {
		// TODO Auto-generated method stub
		return resultOfQuizRepository.findAll(Specifications
				.where(ResultOfQuizSpecifications.isQuizNodeId(nodeId)));
	}

	@Override
	public ResultOfQuiz getResultOfQuiz(Long classId, String quizId) {
		// TODO Auto-generated method stub
		return resultOfQuizRepository.findOne(Specifications
				.where(ResultOfQuizSpecifications.isThisQuiz(classId,quizId)));
	}



}