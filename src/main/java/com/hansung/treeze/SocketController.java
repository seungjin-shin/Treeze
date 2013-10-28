package com.hansung.treeze;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

@Controller
public class SocketController {
	private static Logger logger = LoggerFactory.getLogger(SocketController.class);
	
	
	public static WebSocketServer webSocketServer;
	public static SocketController instance;

	private Gson gson;
	//@Autowired private SurveyService surveyService;
//	@Autowired private SurveyAnswerService surveyAnswerService;

	public SocketController() throws ServletException {
		initSockets();
		
		if(instance == null) {
			instance = this;
		}
	}
	
	
	private void initSockets() throws ServletException {

		
		if (webSocketServer == null) {
			webSocketServer = new WebSocketServer();
		}
		
		logger.info("Socket start");

		webSocketServer.start();
	}
	
	//webSocketServer.sendToClient();
	
	/*@RequestMapping("/socket/students/count/{classId}")
	@ResponseBody
	public int countStudents(@PathVariable long classId) {
		int studentsCount = socketManager.getStudentsCount(classId);
		
		return studentsCount;
	}
	
	@RequestMapping("/socket/send/survey/{classId}/{surveyId}")
	@ResponseBody
	public int sendSurvey(@PathVariable long classId, @PathVariable long surveyId) {
		Survey survey = surveyService.findOne(surveyId);
		survey.setExecuted(true);
		surveyService.save(survey);
		logger.info("send survey : " + survey.toString());
		int receivedCount = socketManager.sendSurvey(classId, survey);
		return receivedCount;
	}


	public static void receivedSurveyAnswer(SurveyAnswer surveyAnswer) {
		instance.saveAndSend(surveyAnswer);
	}
	
	public void saveAndSend(SurveyAnswer surveyAnswer) {
		surveyAnswer = surveyAnswerService.save(surveyAnswer);
		webSocketServer.sendToClient(gson .toJson(surveyAnswer));
	}*/
}
