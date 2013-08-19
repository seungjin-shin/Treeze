package com.hansung.treeze.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hansung.treeze.model.Mindmap;
import com.hansung.treeze.service.MindmapService;


/*
2-2. 援��媛�留����㏊ �≪����.(�≪�)

- Rest : POST諛⑹�
- URL : http://113.198.84.74:8080/treeze/createMindMap
- Format : JSON
- Class name : MindMap
- Return Value : Boolean 

4-2. �����留����㏊��媛���⑤�.(���)

- Rest : GET諛⑹�
- URL 1 : http://113.198.84.74:8080/treeze/getMindMap?{classId}
- Format : JSON
- Class name : MindMap
- Return Value : MindMap
 * */

@Controller
public class MindmapController {
	private static final Logger logger = LoggerFactory.getLogger(MindmapController.class);
	@Autowired private MindmapService mindmapService;
	

	@RequestMapping(value="/createMindMap", method=RequestMethod.POST)
	public String createMindMap(Mindmap model, ModelMap map) {
		logger.info("xml::::"+model.getMindmapXML());
		mindmapService.saveMindmap(model);
		map.put("result", "success");

		return "jsonView";
	}
	
	@RequestMapping(value="/updateMindMap", method=RequestMethod.POST)
	public String updateMindMap(Mindmap model, ModelMap map) {
		logger.info("xml::::"+model.getMindmapXML());
		mindmapService.saveMindmap(model);
		map.put("result", "success");

		return "jsonView";
	}
	
@RequestMapping(value="/getMindMap", method=RequestMethod.GET)
	public String getMindMap(@RequestParam("classId") Long classId, ModelMap map) {
		Mindmap mindmap = mindmapService.findByclassId(classId);
		logger.info("xml::::"+mindmap.getMindmapXML());
		map.put("mindmap", mindmap);
		return "jsonView";

	}
}
