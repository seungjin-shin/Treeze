package freemind.NodeType;

import java.awt.Desktop;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import freemind.Frame.StartQuizFrame;
import freemind.controller.FreeMindPopupMenu;
import freemind.controller.FreemindLectureManager;
import freemind.controller.FreemindManager;
import freemind.controller.LectureInfo;
import freemind.json.FreemindGson;
import freemind.json.QuizInfo;
import freemind.json.Survey;
import freemind.json.TreezeData;
import freemind.modes.NodeAdapter;

public class QuizType extends NodeType{

	NodeAdapter node;
	String jsonStr;
	FreemindGson myGson = new FreemindGson();
	OutputStream os;
	
	public QuizType(NodeAdapter node) {
		this.node = node;
	}
	
	@Override
	public void act() {
//		FreemindManager fManager = FreemindManager.getInstance();
//		boolean didQuizChk = false;
//		
//		fManager.getUploadToServer().setCurFrame(fManager.getFreemindMainFrame());
//		
//		didQuizChk = fManager.getUploadToServer().getStartChk(fManager.getClassId());
//		
//		 
//
//		if(didQuizChk){// result
//			
//		}
//		else{
//			String time = FreemindManager.getInstance().getUploadToServer().getLimitTime(node.getNodeID(), FreemindManager.getInstance().getClassId());
//			new StartQuizFrame(time);
//		}
		
	}

	@Override
	public NodeType getNodeType() {
		return this;
	}

	//	public static boolean start = false;
	//	public static String endTime;
	
	@Override
	public void forQuizAct() {
		System.out.println("Survey func");
		
		QuizInfo quiz = new QuizInfo();
		
//		survey.setSurveyContent(node.getSurveyContents());
		quiz.setClassId((long) FreemindManager.getInstance().getClassId());
		quiz.setNodeId(node.getNodeID());
		
		jsonStr = myGson.toJson(quiz);
		
		TreezeData t = new TreezeData();
		t.setDataType(TreezeData.QUIZ);
		t.getArgList().add(jsonStr);
		
		jsonStr = myGson.toJson(t);
		os = FreemindManager.getInstance().getOs();
		try {
			System.out.println("QuizType : " + jsonStr);
			os.write(jsonStr.getBytes("UTF-8"));
			os.flush();
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}	

}
