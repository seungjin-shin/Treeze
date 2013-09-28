package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Quiz;
import com.hansung.treeze.persistence.QuizRepository;
import com.hansung.treeze.persistence.QuizSpecifications;
import com.hansung.treeze.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired private QuizRepository quizRepository;

	@Override
	public Quiz saveQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		return quizRepository.save(quiz);
	}

	@Override
	public void deleteQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		quiz = getQuiz(quiz.getClassId(),quiz.getNodeId());
		quizRepository.delete(quiz);
	}

	@Override
	public List<Quiz> getQuizes(Long classId, String nodeId) {
		// TODO Auto-generated method stub
		return quizRepository.findAll(Specifications
				.where(QuizSpecifications.isQuizId(nodeId)));
	}
	
	@Override
	public Quiz getQuiz(Long classId, String quizId) {
		// TODO Auto-generated method stub
		return quizRepository.findOne(Specifications
				.where(QuizSpecifications.isThisQuiz(classId,quizId)));
	}


}