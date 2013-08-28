package freemind.NodeType;

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
		// TODO Auto-generated method stub
		FreemindManager fManager = FreemindManager.getInstance();
		
		if (!fManager.isSlideShowInfo()) {

			NodeAdapter root = (NodeAdapter) fManager.getMc().getRootNode();
			NodeAdapter next;// = (NodeAdapter)mc.getRootNode();

			// set FreemindManager isSlideshow
			fManager.setSlideShowInfo(true);

			// set root
			root.setPrev(null);
			if (root.hasChildren()) {
				next = (NodeAdapter) root.getChildAt(0);
				root.setNext(next);

				for (int i = 0; i < root.getChildCount(); i++) { // root direct
																	// childs
																	// set
					fManager.getC().recurSetSlideShowInfo((NodeAdapter) root.getChildAt(i));
				}
				System.out.println("Controller : set slideShowInfo");
			} else {
				System.out.println("Controller : only root");
				return;
			}
    	}
		
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
