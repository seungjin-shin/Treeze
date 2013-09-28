package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Quiz;
import com.hansung.treeze.service.QuizService;


@Controller
public class QuizController {
	private static final Logger logger = LoggerFactory.getLogger(QuizController.class);
	@Autowired private QuizService quizService;
	
	@RequestMapping(value="/createQuiz", method=RequestMethod.POST)
	public String createQuiz(Quiz model, ModelMap map) {
		
		Quiz quiz = quizService.saveQuiz(model);

		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value="/deleteQuiz", method=RequestMethod.POST)
	public String deleteQuiz(Quiz model, ModelMap map) {
		quizService.deleteQuiz(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value="/getQuizes", method=RequestMethod.GET)
	public String getQuizes(@RequestParam("classId") Long classId, @RequestParam("nodeId") String nodeId, ModelMap map) {

		map.put("Quizes", quizService.getQuizes(classId, nodeId));
		return "jsonView";	
	}
	
}
