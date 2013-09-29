package com.hansung.treeze.service;

import java.util.List;

import com.hansung.treeze.model.ResultOfQuiz;


public interface ResultOfQuizService {

	ResultOfQuiz saveResultOfQuiz(ResultOfQuiz resultOfQuiz);
	void deleteResultOfQuiz(ResultOfQuiz quiz);
	List<ResultOfQuiz> getResultOfQuizes(Long classId,String nodeId);
	public ResultOfQuiz getResultOfQuiz(Long classId, String quizId);
	

}
