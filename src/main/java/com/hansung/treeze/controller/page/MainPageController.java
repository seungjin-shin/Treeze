package com.hansung.treeze.controller.page;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hansung.treeze.filter.LoginFilter;



@Controller
@RequestMapping("/main")
//@SessionAttributes(LoginFilter.SIGNED_USER)
public class MainPageController {
	
	@RequestMapping("newTreeze")
	public String newTreeze(HttpSession session, ModelMap modelMap) {
		/*// 로그인된 유저 가져오기
		User professor = (User)session.getAttribute(LoginFilter.SIGNED_USER);
		
		// 교수의 강의 목록 가져오기
		List<Lecture> lectures = lectureService.findMyLectures(professor.getUserEmail());
		
		long[] counts = new long[lectures.size()];
		for(int i = 0 ; i < lectures.size(); i++) {
			counts[i] = courseService.count(lectures.get(i));
		}
		
		
		modelMap.addAttribute("myLectures", lectures);
		modelMap.addAttribute("courseCounts", counts);*/
		return "main/newTreeze";
	}

}
