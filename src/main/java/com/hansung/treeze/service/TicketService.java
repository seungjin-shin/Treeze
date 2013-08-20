package com.hansung.treeze.service;

import com.hansung.treeze.model.Ticket;


public interface TicketService {

	Ticket saveTicket(Ticket ticket);
	void deleteTicket(Ticket ticket);
	Object getTickets(Long classId,String parentNodeId);
	Object getAllTickets(Long classId);
}
