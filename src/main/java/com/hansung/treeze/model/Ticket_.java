package com.hansung.treeze.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Ticket.class) 
public class Ticket_ {
	public static volatile SingularAttribute<Ticket, Long> ticketId;
	public static volatile SingularAttribute<Ticket, Long> classId;
	public static volatile SingularAttribute<Ticket, String> parentNodeId;
	public static volatile SingularAttribute<Ticket, String> contents;
	public static volatile SingularAttribute<Ticket, String> userName;
	public static volatile SingularAttribute<Ticket, String> ticketTitle;
}
