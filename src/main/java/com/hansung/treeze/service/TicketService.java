package com.hansung.treeze.service;

import com.hansung.treeze.model.Ticket;


public interface TicketService {

	Ticket saveTicket(Ticket ticket);
	Object getTickets(Long classId,String position);
	Object getAllTickets(Long classId);
}
