package com.hansung.treeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.Mindmap;
import com.hansung.treeze.service.MindmapService;

@Controller
public class MindmapController {

	@Autowired private MindmapService mindmapService;
	
	@RequestMapping(value="/Mindmap/getMindmap", method=RequestMethod.GET)
	public String getMindmapList(@RequestParam("ptId") Long ptId ,ModelMap map){
		
		Mindmap mindmap = mindmapService.findByPTId(ptId);
		
		map.put("mindmap", mindmap);
		return "jsonView";
	}
	
	@RequestMapping(value="/Mindmap", method=RequestMethod.GET)
	public String saveMindmapList (@RequestParam("ptId") Long ptId ,@RequestParam("mindmapPath")String mindmapPath,ModelMap map){
		
		Mindmap mindmap = new Mindmap(mindmapPath,ptId.intValue());
		
		mindmapService.save(mindmap);

		map.put("result", "success");
		return "jsonView";
	}
}
