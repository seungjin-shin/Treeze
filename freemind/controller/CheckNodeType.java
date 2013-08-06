package freemind.controller;

import freemind.NodeType.QuestionType;
import freemind.NodeType.SlideType;
import freemind.NodeType.SurveyType;
import freemind.modes.NodeAdapter;

public class CheckNodeType {
	Controller c;
	
	public CheckNodeType(Controller c) {
		this.c = c;
	}
	
	public void checkNodeType(NodeAdapter node){
		NodeAdapter forCheckNodeType = node;
		int i;
		// Question 노드 추가 하기 전 카운트
		int cnt = forCheckNodeType.getChildCount();
		String nodeChkStr;
		
		nodeChkStr = forCheckNodeType.getNodeTypeStr();
		if(nodeChkStr != null){
			if (nodeChkStr.equals("Slide"))
				forCheckNodeType.setNodeType(new SlideType(forCheckNodeType, c));
			else if (nodeChkStr.equals("Question"))
				forCheckNodeType.setNodeType(new QuestionType(forCheckNodeType,
						c));
			else if(nodeChkStr.equals("Survey"))
				forCheckNodeType.setNodeType(new SurveyType());
		}
//		mc.addNew(forAddingQuestionNode, MindMapController.NEW_CHILD, null);

		for (i = 0; i < cnt; i++) {
			checkNodeType((NodeAdapter) forCheckNodeType.getChildAt(i));
		}
	}
}
