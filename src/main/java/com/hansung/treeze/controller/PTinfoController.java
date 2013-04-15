package com.hansung.treeze.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hansung.treeze.model.PTinfo;
import com.hansung.treeze.model.PTinfoList;
import com.hansung.treeze.service.PTinfoService;

@Controller
public class PTinfoController {

	@Autowired private PTinfoService ptInfoService;
	
	@RequestMapping(value="/PTinfo/getPTinfo", method=RequestMethod.GET)
	public String getMindmapList(@RequestParam("ptId") Integer ptId ,ModelMap map){
		
		List<PTinfo> ptInfoList = (List<PTinfo>) ptInfoService.findByAllPTId(ptId);
		
		PTinfoList ptInfoArrayList = new PTinfoList();
		ptInfoArrayList.setPTinfoList(ptInfoList);
		
		map.put("ptInfoArrayList", ptInfoArrayList);
		return "jsonView";
	}
	
	@RequestMapping(value="/PTinfo", method=RequestMethod.POST)
	public String saveMindmapList (PTinfo model,ModelMap map){
		
		ptInfoService.save(model);

		map.put("result", "success");
		return "jsonView";
	}
}