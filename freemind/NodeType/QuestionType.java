package freemind.NodeType;

import javax.swing.JFrame;

import freemind.Frame.TicketFrame;
import freemind.controller.Controller;
import freemind.modes.MindIcon;
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
			if(node.getIcons() != null)
				if(!node.getIcons().isEmpty())
					if(node.getIcons().size() == 2)
						node.removeIcon(1);
    		
			fm.updateTickets();
			fm.setVisible(true);
			if(node.hasChildren()){
				c.getMc()._setFolded(node, false);
				c.getMc().nodeChanged(node);
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
