package com.hansung.treeze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.persistence.TicketRepository;
import com.hansung.treeze.persistence.TicketSpecifications;
import com.hansung.treeze.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired private TicketRepository ticketRepository;

	@Override
	public Ticket saveTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(ticket);
	}

	@Override
	public List<Ticket>  getTickets(Long classId, String parentNodeId) {
		// TODO Auto-generated method stub
		//Specifications<Ticket> spec = Specifications.where(TicketSpecifications.isclassId(classId);
		
		return ticketRepository.findAll(TicketSpecifications.isTicketsOnParentNodeId(classId,parentNodeId));
	}
	
	@Override
	public List<Ticket>  getAllTickets(Long classId) {
		// TODO Auto-generated method stub
		//Specifications<Ticket> spec = Specifications.where(TicketSpecifications.isclassId(classId);
		
		return ticketRepository.findAll(TicketSpecifications.isTicketsOnClass(classId));
	}

	@Override
	public void deleteTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticket = ticketRepository.findOne(TicketSpecifications.isTicketId(ticket.getTicketId()));
		ticketRepository.delete(ticket);
		
	}

	@Override
	public List<Ticket>  getMyTickets(Long classId, String userEmail) {
		// TODO Auto-generated method stub
		return ticketRepository.findAll(TicketSpecifications.isTicketsOnParentNodeId(classId,userEmail));
	}
}
