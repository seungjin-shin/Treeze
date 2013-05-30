package com.hansung.treeze.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hansung.treeze.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> ,JpaSpecificationExecutor<Ticket>{

}
