package com.hansung.treeze.service.impl;

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
	public Object getTickets(Long classId, String position) {
		// TODO Auto-generated method stub
		//Specifications<Ticket> spec = Specifications.where(TicketSpecifications.isclassId(classId);
		
		return ticketRepository.findAll(TicketSpecifications.isTicketsOnPosition(classId,position));
	}
}
