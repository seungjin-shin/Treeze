package freemind.json;

import java.util.ArrayList;

public class ArrayTicket {
	ArrayList<Ticket> Ticket = new ArrayList<Ticket>();

	public ArrayList<Ticket> getTickets() {
		return Ticket;
	}

	public void setTickets(ArrayList<Ticket> tickets) {
		this.Ticket = tickets;
	}
}
