package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;
import com.hansung.treeze.model.ResultOfQuiz;
import com.hansung.treeze.service.ResultOfMultipleChoiceQuizService;
import com.hansung.treeze.service.ResultOfQuizService;

@Controller
public class ResultOfQuizController {
	private static final Logger logger = LoggerFactory
			.getLogger(ResultOfQuizController.class);
	@Autowired
	private ResultOfQuizService resultOfQuizService;
	@Autowired
	private ResultOfMultipleChoiceQuizService resultOfMultipleChoiceQuizService;

	@RequestMapping(value = "/createResultOfQuiz", method = RequestMethod.POST)
	public String createResultOfQuiz(ResultOfQuiz model, ModelMap map) {

		ResultOfQuiz ResultOfQuiz = resultOfQuizService.saveResultOfQuiz(model);

		if (model.getType().equals(ResultOfQuiz.MULTIPLECHOICE)) {
			ResultOfMultipleChoiceQuiz resultOfMultipleChoiceQuiz = resultOfMultipleChoiceQuizService
					.getResultOfMultipleChoiceQuiz(model.getClassId(),
							model.getQuizId());
			
			if (resultOfMultipleChoiceQuiz == null) {
				resultOfMultipleChoiceQuiz = new ResultOfMultipleChoiceQuiz();
				resultOfMultipleChoiceQuiz.setClassId(model.getClassId());
				resultOfMultipleChoiceQuiz.setQuizId(model.getQuizId());
			}
			System.out.println(resultOfMultipleChoiceQuiz.getCountOfexample2());
			if (model.getAnswerNumber1())
				resultOfMultipleChoiceQuiz
						.setCountOfexample1(resultOfMultipleChoiceQuiz
								.getCountOfexample1() + 1);
			if (model.getAnswerNumber2())
				resultOfMultipleChoiceQuiz
						.setCountOfexample2(resultOfMultipleChoiceQuiz
								.getCountOfexample2() + 1);
			if (model.getAnswerNumber3())
				resultOfMultipleChoiceQuiz
						.setCountOfexample3(resultOfMultipleChoiceQuiz
								.getCountOfexample3() + 1);
			if (model.getAnswerNumber4())
				resultOfMultipleChoiceQuiz
						.setCountOfexample4(resultOfMultipleChoiceQuiz
								.getCountOfexample4() + 1);
			if (model.getAnswerNumber5())
				resultOfMultipleChoiceQuiz
						.setCountOfexample5(resultOfMultipleChoiceQuiz
								.getCountOfexample5() + 1);

			resultOfMultipleChoiceQuizService
					.saveResultOfMultipleChoiceQuiz(resultOfMultipleChoiceQuiz);
		}
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/deleteResultOfQuiz", method = RequestMethod.POST)
	public String deleteResultOfQuiz(ResultOfQuiz model, ModelMap map) {
		resultOfQuizService.deleteResultOfQuiz(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value = "/getResultOfQuizes", method = RequestMethod.GET)
	public String getResultOfQuizes(@RequestParam("classId") Long classId,
			@RequestParam("nodeId") String nodeId, ModelMap map) {

		map.put("ResultOfQuizes",
				resultOfQuizService.getResultOfQuizes(classId, nodeId));
		return "jsonView";
	}

}
