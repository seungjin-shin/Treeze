package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.Mindmap;
import com.hansung.treeze.service.MindmapService;


/*
2-2. 교수가 마인드맵 송신한다.(송신)

- Rest : POST방식
- URL : http://113.198.84.74:8080/treeze/createMindMap
- Format : JSON
- Class name : MindMap
- Return Value : Boolean 

4-2. 학생이 마인드맵을 갖고온다.(수신)

- Rest : GET방식
- URL 1 : http://113.198.84.74:8080/treeze/getMindMap?{classId}
- Format : JSON
- Class name : MindMap
- Return Value : MindMap `
 * */

@Controller
public class MindmapController {
	private static final Logger logger = LoggerFactory.getLogger(MindmapController.class);
	@Autowired private MindmapService mindmapService;
	

	@RequestMapping(value="/createMindMap", method=RequestMethod.POST)
	public String createMindMap(Mindmap model, ModelMap map) {
		mindmapService.saveMindmap(model);
		map.put("result", "success");

		return "jsonView";
	}
	
@RequestMapping(value="/getMindMap/{classId}", method=RequestMethod.GET)
	public String getMindMap(@PathVariable Long classId, ModelMap map) {
		Mindmap mindmap = mindmapService.findByclassId(classId);
		map.put("mindmap", mindmap);
		return "jsonView";

	}
}
