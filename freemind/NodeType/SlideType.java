package freemind.NodeType;

import freemind.controller.Controller;
import freemind.modes.NodeAdapter;

public class SlideType extends NodeType{

	NodeAdapter node;
	Controller c;
	public SlideType(NodeAdapter node, Controller c) {
		this.node = node;
		this.c = c;
	}
	
	@Override
	public void act() {
		// TODO Auto-generated method stub
		c.getSlideShow().setfocus(node);
		c.getSlideShow().show();
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
