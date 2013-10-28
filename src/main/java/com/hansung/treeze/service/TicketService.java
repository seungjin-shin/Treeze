package com.hansung.treeze.service;

import java.util.List;

import com.hansung.treeze.model.Ticket;


public interface TicketService {

	Ticket saveTicket(Ticket ticket);
	void deleteTicket(Ticket ticket);
	List<Ticket> getTickets(Long classId,String parentNodeId);
	List<Ticket> getMyTickets(Long classId,String userEmail);
	List<Ticket> getAllTickets(Long classId);
}
