package freemind.NodeType;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import freemind.controller.FreemindLectureManager;
import freemind.controller.FreemindManager;
import freemind.controller.LectureInfo;
import freemind.json.FreemindGson;
import freemind.json.Survey;
import freemind.json.TreezeData;
import freemind.modes.NodeAdapter;

public class SurveyType extends NodeType{

	NodeAdapter node;
	String jsonStr;
	FreemindGson myGson = new FreemindGson();
	OutputStream os;
	
	public SurveyType(NodeAdapter node) {
		os = FreemindManager.getInstance().getOs();
		this.node = node;
	}
	
	@Override
	public void act() {
		forSurveyAct();
	}

	@Override
	public NodeType getNodeType() {
		return this;
	}

	@Override
	public void forSurveyAct() {
		System.out.println("Survey func");
		Survey survey = new Survey();
		
		survey.setSurveyContent(node.getSurveyContents());
		
		LectureInfo lectureInfo;
		lectureInfo = FreemindLectureManager.getInstance();
		
		lectureInfo.setSurverTitle(node.getSurveyContents());
		
		jsonStr = myGson.toJson(survey);
		
		TreezeData t = new TreezeData();
		t.setDataType(TreezeData.SURVEY);
		t.getArgList().add(jsonStr);
		
		jsonStr = myGson.toJson(t);
		try {
			System.out.println("SurveyType : " + jsonStr);
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
