package freemind.controller;

import java.awt.event.KeyEvent;

import freemind.Frame.TextDialogue;
import freemind.json.Ticket;
import freemind.modes.MindIcon;
import freemind.modes.NodeAdapter;
import freemind.modes.common.CommonNodeKeyListener;
import freemind.modes.common.CommonNodeKeyListener.EditHandler;
import freemind.modes.mindmapmode.MindMapController;
import freemind.view.mindmapview.EditNodeTextField;

public class AddTicketThread extends Thread{
	Ticket ticket;
	
	public AddTicketThread(Ticket t) {
		ticket = t;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		FreemindManager.getInstance().setTicket(ticket);
		FreemindManager.getInstance().getC().recurAddTicketNode((NodeAdapter) FreemindManager.getInstance().getMc().getRootNode());

		if(ticket.getUserName().equals("±³¼ö"))
			return;
		
		NodeAdapter updateNode = FreemindManager.getInstance().getReceiveQNode();
		if (updateNode.getIcons() != null) {
			if (updateNode.getIcons().size() == 1) { // icon only one
				MindIcon icon = MindIcon.factory("Mail");
				updateNode.addIcon(icon, 1);
				FreemindManager.getInstance().getMc().nodeChanged(updateNode);
			}
		}
		
		if (ticket.getParentNodeId().equals(((NodeAdapter)updateNode.getParentNode()).getNodeID())) {
			MovingTextFrameThread movingTextFrame = new MovingTextFrameThread();
			movingTextFrame.start();
		}
	}
}
