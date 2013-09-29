package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.ResultOfQuiz;
import com.hansung.treeze.service.ResultOfQuizService;


@Controller
public class ResultOfQuizController {
	private static final Logger logger = LoggerFactory.getLogger(ResultOfQuizController.class);
	@Autowired private ResultOfQuizService resultOfQuizService;
	
	@RequestMapping(value="/createResultOfQuiz", method=RequestMethod.POST)
	public String createResultOfQuiz(ResultOfQuiz model, ModelMap map) {
		
		ResultOfQuiz ResultOfQuiz = resultOfQuizService.saveResultOfQuiz(model);

		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value="/deleteResultOfQuiz", method=RequestMethod.POST)
	public String deleteResultOfQuiz(ResultOfQuiz model, ModelMap map) {
		resultOfQuizService.deleteResultOfQuiz(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value="/getResultOfQuizes", method=RequestMethod.GET)
	public String getResultOfQuizes(@RequestParam("classId") Long classId, @RequestParam("nodeId") String nodeId, ModelMap map) {

		map.put("ResultOfQuizes", resultOfQuizService.getResultOfQuizes(classId, nodeId));
		return "jsonView";	
	}
	
}
