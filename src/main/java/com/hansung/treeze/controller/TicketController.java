package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hansung.treeze.model.Ticket;
import com.hansung.treeze.service.TicketService;

/*
9. 질문이나 답변을 달시 url로 서버에게 송신한다.(송신) "한개달때마다 서버에 송신한다."

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/createTicket
- Format : JSON
- Class name : Ticket

10. 교수또는 학생이 질문 데이터 요청시 Ticket 정보를 서버에서 URL요청해 갖고온다.(수신) "학생또는 교수는 주기적으로 자신의 Class의 질문들을 갖고온다."

- Rest : GET방식
- URL : http://113.198.84.74:8080/treeze/getTickets?{classId}
- Format : JSON
- Class name : Ticket
- Return Value : Ticket 리스트를 json으로 

 * */

@Controller
public class TicketController {
	private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
	@Autowired private TicketService ticketService;
	
	@RequestMapping(value="/createTicket", method=RequestMethod.POST)
	public String createTicket(Ticket model, ModelMap map) {
		ticketService.saveTicket(model);
		map.put("result", "success");

		return "jsonView";
	}

		@RequestMapping(value="/getTickets/{classId}", method=RequestMethod.GET)
	public String getTickets(@PathVariable Integer classId, ModelMap map) {

		map.put("Ticket", ticketService.getTickets(classId));
		return "jsonView";	
	}
}
