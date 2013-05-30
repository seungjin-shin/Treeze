package com.hansung.treeze.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.persistence.TicketRepository;
import com.hansung.treeze.persistence.TicketSpecitications;
import com.hansung.treeze.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired private TicketRepository ticketRepository;

	@Override
	public Ticket saveTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(mindmap);
	}

	@Override
	public Object getTickets(int classId) {
		// TODO Auto-generated method stub
		return ticketRepository.findAll(Specifications.where(TicketSpecitications.isclassId(classId)));
	}
}
