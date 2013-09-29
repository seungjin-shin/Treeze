package com.hansung.treeze.service;

import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;


public interface ResultOfMultipleChoiceQuizService {

	ResultOfMultipleChoiceQuiz saveResultOfMultipleChoiceQuiz(ResultOfMultipleChoiceQuiz resultOfMultipleChoiceQuiz);

	public ResultOfMultipleChoiceQuiz getResultOfMultipleChoiceQuiz(Long classId, String quizId);
	

}
