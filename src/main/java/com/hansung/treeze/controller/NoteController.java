package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hansung.treeze.model.Note;
import com.hansung.treeze.service.NoteService;

/*

11-1. 학생이 서버에게 Note정보를 보낸다.(송신)
- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/attachNotes
- Format : JSON
- Class name : Note
- Return Value :  Boolean

11-2. 학생이 서버로부터 Note정보를 갖고온다.(수신)
- Rest : GET방식
- URL : http://113.198.84.74:8080/treeze/getNotes?{classId}&{userEmail}&{position}
- Format : JSON
- Class name : Note
- Return Value :  Note 리스트를 json으로 

 * */

@Controller
public class NoteController {
	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	@Autowired private NoteService noteService;
	
	@RequestMapping(value="/createNote", method=RequestMethod.POST)
	public String add(Note model, ModelMap map) {
		noteService.saveNote(model);
		map.put("result", "success");

		return "jsonView";
	}

	@RequestMapping(value="/getNotes/{classId}/{userEmail}/{position}", method=RequestMethod.GET)
	public String getNotes(@PathVariable Integer classId, @PathVariable String userEmail, @PathVariable String position, ModelMap map) {

		map.put("Note", noteService.getNotes(classId, userEmail,position));
		return "jsonView";	
	}
}
