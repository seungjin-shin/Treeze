package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Ticket.class) 
public class Ticket_ {
	
	public static volatile SingularAttribute<Ticket, Integer> classId;
	public static volatile SingularAttribute<Ticket, String> position;
	public static volatile SingularAttribute<Ticket, String> contents;
	public static volatile SingularAttribute<Ticket, Integer> ticketNumber;
	public static volatile SingularAttribute<Ticket, String> userEmail;
}
