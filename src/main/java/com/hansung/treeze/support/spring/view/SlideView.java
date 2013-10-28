package com.hansung.treeze.support.spring.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.hansung.treeze.controller.page.TreezingPageController;

@Component("slideView")
public class SlideView extends FileView{
	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		setContentType("image/png");
		String fullPath = get(model, TreezingPageController.KEY_FILE_PATH);
		
		File file = new File(fullPath);
		
		String fileName = file.getName();
		
		flush(response, file, fileName);
	}
	
	@SuppressWarnings({"unchecked", "rawtypes"})
	private <T> T get(Map model, String key) {
		return (T)model.get(key);
	}
}
