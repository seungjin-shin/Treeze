package com.hansung.treeze.controller.page;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Quiz;
import com.hansung.treeze.service.QuizService;



@Controller
@RequestMapping("/examPaper")
//@SessionAttributes(LoginFilter.SIGNED_USER)
public class ExamPaperPageController {

	
	@Autowired private QuizService quizService;

	
	
	@RequestMapping(method = RequestMethod.GET)
	public String makeQuiz(@RequestParam("classId") Long classId,@RequestParam("nodeId") String nodeId,HttpSession session, ModelMap modelMap) {
		/*// 로그인된 유저 가져오기
		User professor = (User)session.getAttribute(LoginFilter.SIGNED_USER);
		
		// 교수의 강의 목록 가져오기
		List<Lecture> lectures = lectureService.findMyLectures(professor.getUserEmail());
		
		long[] counts = new long[lectures.size()];
		for(int i = 0 ; i < lectures.size(); i++) {
			counts[i] = courseService.count(lectures.get(i));
		}
		
		*/
		System.out.println("asdfasdfffffffffffffffffffff");
		/*
		modelMap.addAttribute("myLectures", lectures);
		modelMap.addAttribute("courseCounts", counts);*/
		

		List<Quiz> quizes = quizService.getQuizes(classId, nodeId);
		
		modelMap.addAttribute("quizes", quizes);	
		return "quiz/examPaper";
	}
	
}
