package freemind.controller;

import freemind.json.Ticket;
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
	}
}
