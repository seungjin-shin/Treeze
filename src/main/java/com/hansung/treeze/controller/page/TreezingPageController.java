package com.hansung.treeze.controller.page;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.google.gson.Gson;
import com.hansung.treeze.filter.LoginFilter;
import com.hansung.treeze.model.ClassInfo;
import com.hansung.treeze.model.Course;
import com.hansung.treeze.model.Lecture;
import com.hansung.treeze.model.Slide;
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
import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;

@Controller
@RequestMapping("/treezing")
@SessionAttributes(LoginFilter.SIGNED_USER)
public class TreezingPageController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(TreezingPageController.class);

	private static final String KEY_CLASS_ID = "classId";

	private static final String KEY_SLIDES = "slides";

	private static final String KEY_SURVEYS = "surveys";
	
	private static final int THUMB_WIDTH = 780;
	private static final int THUMB_HEIGHT = 555;

	private static final String KEY_SLIDES_PATH = "file.slides.path";
	
	private static final String KEY_SLIDE_THUMBS_PATH = "file.slide.thumbs.path";

	public static final String KEY_FILE_PATH = "filePath";
	
	public static String ERROR_MESSAGE = "com.hansung.treeze.key.ERROR_MESSAGE";

	@Value("#{message-ko['signin.wrongPassword']")
	public static String MSG_WRONG_PASSWORD;

	@Value("#{message-ko['signin.noSuchEmail']")
	public static String MSG_NO_SUCH_EMAIL;
	
	
	//============================================================//
	// Autowired service beans
	//============================================================//
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
	//@Autowired SurveyService surveyService;
	//@Autowired SurveyOptionService surveyOptionService;
	//@Autowired SurveyAnswerService surveyAnswerService;
	
	Gson gson;
	
	@Resource(name = "defaultProperties")
	private Properties defaultProperties;
	
	@Resource(name = "interactionProperties")
	private Properties interactionProperties;

	@Resource(name = "slideView")
	private View slideView;
	
	@Resource(name = "jsonView")
	private View jsonView;

	@RequestMapping("professor")
	public String TreezingForProfessor(HttpSession session, ModelMap modelMap) {

		User professor = (User) session.getAttribute(LoginFilter.SIGNED_USER);

		// 교수의 강의 목록 가져오기
		List<Lecture> lectures = (List<Lecture>) lectureService.findMyLectures(professor
				.getUserEmail());

		modelMap.addAttribute("myLectures", lectures);
	
		List<Ticket> tickets = (List<Ticket>) ticketService.getTickets(
				new Long(10), "246305717490589");
		modelMap.addAttribute("tickets", tickets);
		return "treezing/professorTreeze";
	}
	
	@RequestMapping("student")
	public String TreezingForStudent(HttpSession session, ModelMap modelMap) {

		User student = (User) session.getAttribute(LoginFilter.SIGNED_USER);

		// 교수의 강의 목록 가져오기
		List<Course> courses = (List<Course>) courseService.findMyCourses(student
				.getUserEmail());
		List<Lecture> lectures = (List<Lecture>) lectureService.findAll();

		modelMap.addAttribute("courses", courses);
		modelMap.addAttribute("lectures", lectures);
		

		List<Ticket> tickets = (List<Ticket>) ticketService.getTickets(
				new Long(10), "246305717490589");
		modelMap.addAttribute("tickets", tickets);
		return "treezing/studentTreeze";
	}

	
	@RequestMapping(value ="/upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam MultipartFile upload, @RequestParam long classId) {
		byte[] byteArray = null;
		try {
			byteArray = upload.getBytes();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String slidePath = interactionProperties.getProperty(KEY_SLIDES_PATH);
		String thumbPath = interactionProperties.getProperty(KEY_SLIDE_THUMBS_PATH);
		
		ByteBuffer buffer = ByteBuffer.allocate(byteArray.length);
		buffer.put(byteArray);
		Slide slide;
		
		try {
			PDFFile pdf = new PDFFile(buffer);
			
			
			// pdf pages
			int numPages = pdf.getNumPages();
			
			for(int i=0 ; i<numPages ; i++) {
				logger.info("처리 중 ... " + (i+1) + "페이지");
				
				PDFPage page = pdf.getPage(i);
	
				// create the image
				Rectangle rect = new Rectangle(0, 0, (int) page.getBBox().getWidth(),
				                                 (int) page.getBBox().getHeight());
				BufferedImage bufferedImage = new BufferedImage(rect.width, rect.height,
				                                  BufferedImage.TYPE_INT_RGB);
				
				
	
				Image image = page.getImage(rect.width, rect.height,    // width & height
				                            rect,                       // clip rect
				                            null,                       // null for the ImageObserver
				                            true,                       // fill background with white
				                            true                        // block until drawing is done
				);
				
				// 섬네일 만들기
				
				Graphics2D bufImageGraphics = bufferedImage.createGraphics();
				bufImageGraphics.drawImage(image, 0, 0, null);
				ImageIO.write(bufferedImage, "png", new File(slidePath + classId + "_" + i + ".png"));
				bufferedImage = new BufferedImage(THUMB_WIDTH, THUMB_HEIGHT, BufferedImage.TYPE_INT_RGB);
				image = image.getScaledInstance(THUMB_WIDTH, THUMB_HEIGHT, Image.SCALE_SMOOTH);
				bufImageGraphics = bufferedImage.createGraphics();
				bufImageGraphics.drawImage(image, 0, 0, null);
				ImageIO.write(bufferedImage, "png", new File(thumbPath + classId + "_" + i + ".png"));
				
				slide = new Slide();
				slide.setClassId(classId);
				slide.setFilename(classId + "_" + i + ".png");
				slide.setPage(i+1);
				slideService.save(slide);
			}
			
			ClassInfo ci = classInfoService.findClass(classId);
			ci.setUploaded(true);
			classInfoService.saveClass(ci);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "ok";
	}
	
	

	/**
	 * Sign in page 요청
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin() {
		return "treezing/signin";
	}

	/**
	 * Sign in form action
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView signin(String before, ModelAndView model, String email,
			String password) {
		logger.info("email : " + email + ", password : " + password);

		User user = userService.findByEmail(email);
		String url = "";
		if (null != user) {
			boolean isCorrect = user.getPassword().equals(password);

			// email, password가 일치하는 경우
			if (isCorrect) {
				logger.info("Correct");

				if (user.getUserType().equals(User.PROFESSOR)) {
					url = "treezing/professorTreeze";
					
					model.setViewName(url);
					model.getModelMap().addAttribute(LoginFilter.SIGNED_USER, user);
					
				} else if (user.getUserType().equals(User.STUDENT)) {
					url = "treezing/studentTreeze";
					
					model.setViewName(url);
					model.getModelMap().addAttribute(LoginFilter.SIGNED_USER, user);
					

					// 교수의 강의 목록 가져오기
					List<Course> courses = (List<Course>) courseService.findMyCourses(user
							.getUserEmail());
					List<Lecture> lectures = (List<Lecture>) lectureService.findAll();

					model.getModelMap().addAttribute("courses", courses);
					model.getModelMap().addAttribute("lectures", lectures);
					

					List<Ticket> tickets = (List<Ticket>) ticketService.getTickets(
							new Long(10), "246305717490589");
					model.getModelMap().addAttribute("tickets", tickets);
				}

				return model;
			}
			// password가 맞지 않는 경우
			else {
				logger.info("잘못된 비밀번호");
				// TODO: 메시지 Properties 파일로 추출하기
				model.getModelMap().addAttribute(ERROR_MESSAGE,
						MSG_WRONG_PASSWORD + "a");
			}
		}
		// email에 해당하는 유저가 존재하지 않는 경우
		else {
			logger.info("이메일이 존재하지 않음");
			// TODO: 메시지 Properties 파일로 추출하기
			model.getModelMap().addAttribute(ERROR_MESSAGE,
					MSG_NO_SUCH_EMAIL + "b");
		}
		/*
		 * String url = "/treezing/signin" + ((before == null) ? "" : "before="
		 * + before); model.setViewName(url);
		 */
		url="/";
		model.setViewName(url);
		return model;
	}

	/**
	 * Sign out 요청, Session 정보를 무효시키며, index 페이지로 이동함
	 */
	@RequestMapping("/signout")
	public String signout(SessionStatus status) {
		status.setComplete();

		return "redirect:.";
	}


}
