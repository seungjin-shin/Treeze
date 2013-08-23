package freemind.NodeType;

import javax.swing.JFrame;

import freemind.controller.Controller;
import freemind.controller.TicketFrame;
import freemind.modes.NodeAdapter;



public class QuestionType extends NodeType{

	NodeAdapter node;
	Controller c;
	TicketFrame fm;

	public QuestionType(NodeAdapter node, Controller c) {
		this.node = node;
		this.c = c;
		fm = new TicketFrame((NodeAdapter) node, c);
	}
	
	public TicketFrame getFm() {
		return fm;
	}
	@Override
	public void act() {
		
		if(fm.isVisible()){
			fm.setVisible(false);
			
			if(node.hasChildren()){
				c.getMc()._setFolded(node, true);
				c.getMc().nodeChanged(node);
			}
		}
		else{
			fm.updateTickets();
			fm.setVisible(true);
			if(node.hasChildren()){
				c.getMc()._setFolded(node, false);
				c.getMc().nodeChanged(node);
			}
		}
		
		
		

//		if (node.isFolded()) { // 접혀있어
//			c.getMc()._setFolded(node, false);
//			c.getMc().nodeChanged(node);
//		} else {
//			if (node.hasChildren()) {
//				c.getMc()._setFolded(node, true);
//				c.getMc().nodeChanged(node);
//			}
//			
//			fm.setVisible(false);
//		}
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
