package com.hansung.treeze.service;

import java.util.List;

import com.hansung.treeze.model.Quiz;


public interface QuizService {

	Quiz saveQuiz(Quiz quiz);
	void deleteQuiz(Quiz quiz);
	List<Quiz> getQuizes(Long classId,String nodeId);
	public Quiz getQuiz(Long classId, String quizId);
}
