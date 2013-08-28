package freemind.controller;

import freemind.json.Ticket;
import freemind.modes.MindIcon;
import freemind.modes.NodeAdapter;

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
		
		NodeAdapter updateNode = FreemindManager.getInstance().getReceiveQNode();
		if(updateNode.getIcons().isEmpty()){ // icon only one
			MindIcon icon = MindIcon.factory("help");
			updateNode.addIcon(icon, -1);
			FreemindManager.getInstance().getMc().nodeChanged(updateNode);
		}
	}
}
