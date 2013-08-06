package freemind.controller;

import freemind.modes.ControllerAdapter;
import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;
import freemind.modes.mindmapmode.MindMapController;

public class AddQuestionNode {
	Controller c;

	public AddQuestionNode(Controller c) {
		this.c = c;
	}

	public void addNodeForQuestion(MindMapNode node) {

		MindMapNode forNodeForQuestion = node;
		int i;
		// Question 노드 추가 하기 전 카운트
		int cnt = forNodeForQuestion.getChildCount();

		c.getMc().addNew(forNodeForQuestion, MindMapController.NEW_CHILD, null);

		for (i = 0; i < cnt; i++) {
			addNodeForQuestion((MindMapNode) forNodeForQuestion.getChildAt(i));
		}
	}
	
	public void modifyForQuestion(MindMapNode node) {

		MindMapNode modifyForQuestion = node;
		NodeAdapter forModifyNodeName;
		int i;
		// Question 노드 추가 하기 전 카운트
		int cnt = modifyForQuestion.getChildCount();
		if(cnt != 0){
			forModifyNodeName = (NodeAdapter)modifyForQuestion.getChildAt(cnt - 1);
			forModifyNodeName.setText("Q");
			forModifyNodeName.setNodeTypeStr("Question");
			c.getMc().nodeChanged(forModifyNodeName);
		}
		
		for (i = 0; i < cnt; i++) {
			modifyForQuestion((MindMapNode) modifyForQuestion.getChildAt(i));
		}
	}
}
