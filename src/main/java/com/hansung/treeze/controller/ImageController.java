package com.hansung.treeze.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.hansung.treeze.service.ImageService;

@Controller
public class ImageController {
	
    @Autowired
	private ImageService imageService;

	@Resource(name="jsonView")
	private View jsonView;
	@Resource(name="imageView")
	private View imageView;
	@Resource(name="thumbnailView")
	private View thumbnailView;

	@RequestMapping(value="/upload/img")
	public String uploadImg(@RequestParam("upload") MultipartFile multipartFile, ModelMap map) {
		map.put("file", imageService.uploadImage(multipartFile));
		return "uploadImage";
	}
	
	
	@RequestMapping(value="/img/{id}")
	public ModelAndView image(@PathVariable Long id) {
		return this.getFileModelAndView(imageView, id, null);	
	}
	
	@RequestMapping(value="/thumb/{id}/{size}")
	public ModelAndView thumbnail(@PathVariable Long id, @PathVariable Integer size) {
		return this.getFileModelAndView(thumbnailView, id, size);	
	}
	
	private ModelAndView getFileModelAndView(View view, Long id, Integer size){
		ModelAndView modelAndView = new ModelAndView(view);
		modelAndView.addObject("uploadedFile", imageService.getUploadedImage(id));
		modelAndView.addObject("displaySize", size);
		return modelAndView;	
	}
}
