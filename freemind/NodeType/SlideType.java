package freemind.NodeType;

import freemind.Frame.TextDialogue;
import freemind.controller.Controller;
import freemind.controller.FreemindManager;
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
		if(!FreemindManager.getInstance().isSlideShowInfo()){
			new TextDialogue(FreemindManager.getInstance().getFreemindMainFrame(), "Setting SlideShow info.", "Please waiting.", true);
    		return;
    	}
		c.getSlideShow().setfocus(node);
		c.getSlideShow().show();
		c.getSlideShow().sendPosition();
	}

	@Override
	public NodeType getNodeType() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public void forQuizAct() {
		// TODO Auto-generated method stub
		
	}


}
