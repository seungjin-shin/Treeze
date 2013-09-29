package com.hansung.treeze.controller.page;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Quiz;
import com.hansung.treeze.model.ResultOfMultipleChoiceQuiz;
import com.hansung.treeze.model.ResultOfQuiz;
import com.hansung.treeze.model.User;
import com.hansung.treeze.service.QuizService;
import com.hansung.treeze.service.ResultOfMultipleChoiceQuizService;
import com.hansung.treeze.service.ResultOfQuizService;
import com.hansung.treeze.service.UserService;

@Controller
@RequestMapping("/resultOfQuiz")
// @SessionAttributes(LoginFilter.SIGNED_USER)
public class ResultOfQuizPageController {

	@Autowired
	private ResultOfMultipleChoiceQuizService resultOfMultipleChoiceQuizService;
	@Autowired
	private ResultOfQuizService resultOfQuizService;
	@Autowired
	private QuizService quizService;
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String makeQuiz(@RequestParam("classId") Long classId,
			@RequestParam("nodeId") String nodeId, HttpSession session,
			ModelMap modelMap) {
		/*
		 * // 로그인된 유저 가져오기 User professor =
		 * (User)session.getAttribute(LoginFilter.SIGNED_USER);
		 * 
		 * // 교수의 강의 목록 가져오기 List<Lecture> lectures =
		 * lectureService.findMyLectures(professor.getUserEmail());
		 * 
		 * long[] counts = new long[lectures.size()]; for(int i = 0 ; i <
		 * lectures.size(); i++) { counts[i] =
		 * courseService.count(lectures.get(i)); }
		 * 
		 * 
		 * modelMap.addAttribute("myLectures", lectures);
		 * modelMap.addAttribute("courseCounts", counts);
		 */

		List<ResultOfQuiz> resultOfQuizes = resultOfQuizService
				.getResultOfQuizes(classId, nodeId);
		
		List<ResultOfMultipleChoiceQuiz> resultOfMultipleChoiceQuizes = new ArrayList<ResultOfMultipleChoiceQuiz>();

		List<User> users = new ArrayList<User>();

		for (ResultOfQuiz resultOfQuiz : resultOfQuizes) {
			users.add(userService.findByEmail(resultOfQuiz.getUserEmail()));

			if (resultOfQuiz.getType().equals(ResultOfQuiz.MULTIPLECHOICE))
				resultOfMultipleChoiceQuizes
						.add(resultOfMultipleChoiceQuizService
								.getResultOfMultipleChoiceQuiz(classId,
										resultOfQuiz.getQuizId()));
		}

		List<Quiz> quizes = quizService.getQuizes(classId, nodeId);

		modelMap.addAttribute("quizes", quizes);
		modelMap.addAttribute("resultOfQuizes", resultOfQuizes);
		modelMap.addAttribute("resultOfMultipleChoiceQuizes", resultOfMultipleChoiceQuizes);
		modelMap.addAttribute("users", users);
		

		return "quiz/resultOfQuiz";
	}

}
