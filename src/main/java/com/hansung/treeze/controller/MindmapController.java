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

@Controller
public class MindmapController {
	private static final Logger logger = LoggerFactory.getLogger(MindmapController.class);
	@Autowired private MindmapService mindmapService;
	
	@RequestMapping(value="/Mindmap/getMindmap", method=RequestMethod.GET)
	public String getMindmapList(@RequestParam("ptId") Long ptId ,ModelMap map){
		
		Mindmap mindmap = mindmapService.findByPTId(ptId);
		
		map.put("mindmap", mindmap);
		return "jsonView";
	}
	
	@RequestMapping(value="/Mindmap", method=RequestMethod.GET)
	public String saveMindmapList (@RequestParam("ptId") Long ptId ,@RequestParam("mindmapXML") MultipartFile multipartFile, ModelMap map){
		
		//Mindmap mindmap = new Mindmap(multipartFile,ptId.intValue());
		logger.info("saveMindmapList"+Long.toString(ptId));
	//	mindmapService.save(mindmap);
		logger.info("saveMindmapList");
		map.put("result", "success");
		return "jsonView";
	}
}
