package com.hansung.treeze.controller.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.hansung.treeze.controller.page.TreezingPageController;
import com.hansung.treeze.filter.LoginFilter;
import com.hansung.treeze.model.Course;
import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.model.User;
import com.hansung.treeze.service.ClassInfoService;
import com.hansung.treeze.service.CourseService;
import com.hansung.treeze.service.ImageService;
import com.hansung.treeze.service.LectureService;
import com.hansung.treeze.service.MindmapService;
import com.hansung.treeze.service.NaviInfoService;
import com.hansung.treeze.service.NoteService;
import com.hansung.treeze.service.SlideService;
import com.hansung.treeze.service.TicketService;
import com.hansung.treeze.service.UserService;



@Controller
@RequestMapping("/ajax")
@SessionAttributes(LoginFilter.SIGNED_USER)
public class MainpageAjaxController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(MainpageAjaxController.class);
	
	@Autowired ClassInfoService classInfoService;
	@Autowired CourseService courseService;
	@Autowired ImageService imageService;
	@Autowired LectureService lectureService;
	@Autowired MindmapService mindMapService;
	@Autowired NaviInfoService naviInfoService;
	@Autowired NoteService noteService;
	@Autowired TicketService ticketService;
	@Autowired UserService userService; 
	@Autowired SlideService slideService;
	
	@RequestMapping("mainpage")
	public String tickets(HttpSession session, ModelMap modelMap) {
		//로그인된 유저 가져오기
		User professor = (User)session.getAttribute(LoginFilter.SIGNED_USER);
		
		//교수의 강의 목록 가져오기
	/*	List<Lecture> lectures = lectureService.findMyLectures(professor.getUserEmail());
		
		long[] counts = new long[lectures.size()];
		for(int i = 0 ; i < lectures.size(); i++) {
			counts[i] = courseService.count(lectures.get(i));
		}
		
		
		modelMap.addAttribute("myLectures", lectures);
		modelMap.addAttribute("courseCounts", counts);*/
		
		List<Ticket> tickets = (List<Ticket>) ticketService.getTickets(new Long(10),"246305717490589");
		modelMap.addAttribute("tickets", tickets);
		return "ajax/mainpageAjax";
	}
	
	@RequestMapping("mycourse")
	public String mycourse(HttpSession session, ModelMap modelMap) {
		//로그인된 유저 가져오기
		User student = (User)session.getAttribute(LoginFilter.SIGNED_USER);
		
		List<Course> courses = (List<Course>) courseService.findMyCourses(student
				.getUserEmail());

		modelMap.addAttribute("courses", courses);
		
		return "ajax/mycourseAjax";
	}
	
	@RequestMapping("allLecture")
	public String allLecture(HttpSession session, ModelMap modelMap) {
		//로그인된 유저 가져오기
		User student = (User)session.getAttribute(LoginFilter.SIGNED_USER);
		
		List<Lecture> lectures = (List<Lecture>) lectureService.findAll();
		modelMap.addAttribute("lectures", lectures);

		return "ajax/allLectureAjax";
	}
	
	@RequestMapping(value = "/addCourse", method = RequestMethod.POST)
	public ModelAndView addCourse(HttpSession session, ModelAndView model, String lectureIdList) {
		
		
		logger.info("lectureIdList : " + lectureIdList );
		
		String[] lectureIdArray =lectureIdList.split(" ");
		
		User student = (User) session.getAttribute(LoginFilter.SIGNED_USER);
		
		List<Course> courses = null;

		boolean already = false;
		
		ArrayList<Course> alreadyCourses = new ArrayList<Course>();
		ArrayList<Course> successCourses = new ArrayList<Course>();
		
		courses = courseService.findMyCourses(student.getUserEmail());
		
		 for(String lectureId : lectureIdArray) {
			 already = false;
			 for(Course course : courses) {
				 if(course.getLectureId().equals(Long.parseLong(lectureId))){
					 alreadyCourses.add(course);
					 already = true;
				 }
			 }
			 if(!already){
				 Course newCourse = new Course();
				 newCourse.setLectureId(Long.parseLong(lectureId));
				 String lectureName = lectureService.findOne(Long.parseLong(lectureId)).getLectureName();
				 newCourse.setLectureName(lectureName);
				 newCourse.setStudentEmail(student.getUserEmail());
				 successCourses.add(courseService.saveCourse(newCourse));
			 }
		 }
		 
		 model.setViewName("ajax/ResultOfAddCourseAjax");
		 model.getModelMap().addAttribute("alreadyCourses", alreadyCourses);
		 model.getModelMap().addAttribute("successCourses", successCourses);

		return model;
	}
}
