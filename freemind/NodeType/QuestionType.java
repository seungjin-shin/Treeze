package freemind.NodeType;

import freemind.controller.TicketFrame;
import freemind.modes.NodeAdapter;
import freemind.modes.mindmapmode.MindMapController;



public class QuestionType extends NodeType{

	NodeAdapter node;
	MindMapController mc;
	
	public QuestionType(NodeAdapter node, MindMapController c) {
		this.node = node;
		this.mc = c;
	}
	
	@Override
	public void act() {
		if(node.hasChildren() && !node.isFolded())
			new TicketFrame((NodeAdapter)node, mc);
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return this;
	}
}
