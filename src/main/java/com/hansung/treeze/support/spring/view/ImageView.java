package com.hansung.treeze.support.spring.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.hansung.treeze.model.UploadedFile;

@Component("imageView")
public class ImageView extends FileView{
	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadedFile uploadedFile = (UploadedFile)model.get("uploadedFile");
		
		File file = new File(uploadedFile.getFilePath());
		String fileName = uploadedFile.getFileName();
		
		flush(response, file, fileName);
	}
}
