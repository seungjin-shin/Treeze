package com.hansung.treeze.controller;

import javax.annotation.Resource;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.hansung.treeze.service.FileService;

@Controller
public class FileController {
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
	private FileService fileService ;
	@Resource(name="jsonView")
	private View jsonView;
	@Resource(name="fileView")
	private View fileView;


	@RequestMapping(value="/upload/file")
	public String uploadFile(@RequestParam("version") String version,@RequestParam("userType") String userType,@RequestParam("upload") MultipartFile multipartFile, ModelMap map) {
		logger.info("uploadFile" + version +"::::"+ multipartFile.getOriginalFilename());

		map.put("file", fileService.uploadFile(multipartFile, version,userType));
		
		return "uploadFile";
	}
	
	@RequestMapping(value = "/file", method = RequestMethod.GET)
	public String getFileInfoes(@RequestParam("version") String version,@RequestParam("userType") String userType, ModelMap map) {
		Object files = fileService.findByversion(version,userType);
		map.put("files", files);
		return "jsonView";
 
	}
	
	@RequestMapping(value="/file/{id}")
	public ModelAndView file(@PathVariable Long id) {
		return this.getFileModelAndView(fileView, id);	
	}
	
	private ModelAndView getFileModelAndView(View view, Long id){
		ModelAndView modelAndView = new ModelAndView(view);
		modelAndView.addObject("uploadedFile", fileService.getUploadedFile(id));
		//modelAndView.addObject("displaySize", size);
		return modelAndView;	
	}
}
