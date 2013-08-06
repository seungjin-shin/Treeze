package freemind.NodeType;

import javax.swing.JFrame;

import freemind.controller.Controller;
import freemind.controller.TicketFrame;
import freemind.modes.NodeAdapter;



public class QuestionType extends NodeType{

	NodeAdapter node;
	Controller c;
	JFrame fm;
	public QuestionType(NodeAdapter node, Controller c) {
		this.node = node;
		this.c = c;
		fm = new TicketFrame((NodeAdapter) node, c);
	}
	
	@Override
	public void act() {
		if (node.hasChildren()) {
			if (node.isFolded()) {
				c.getMc()._setFolded(node, false);
				c.getMc().nodeChanged(node);
				fm.setVisible(true);
			} else {
				c.getMc()._setFolded(node, true);
				c.getMc().nodeChanged(node);
				fm.setVisible(false);
			}
		}
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void forSurveyAct() {
		// TODO Auto-generated method stub
		
	}
}
