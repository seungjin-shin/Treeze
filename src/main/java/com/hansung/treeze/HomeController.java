package com.hansung.treeze;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	{
	
		try {
			logger.info("Socket start");
			new SocketManager().init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	// ����Ƽ ������� ����� ��ġ���� �˷��ִ°�..
	@RequestMapping(value = "/mindmap", method = RequestMethod.GET)
	@ResponseBody
	public String flowOfLecture(@RequestParam("id") Long id,ModelMap map){
		//logger.info(""+Long.toString(id));
		return "ok"+id;
	}
	
	//XML �޾Ƽ� �����ϴ� �κ� 
	// �̹��� �޾Ƽ� �����ϴ� �κ�
	// 
	
}
