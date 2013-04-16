package com.hansung.treeze.support.spring.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.hansung.treeze.model.UploadedFile;

@Component("thumbnailView")
public class ThumbnailView extends ImageView{
	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadedFile uploadedFile = (UploadedFile)model.get("uploadedFile");
		Integer size = (Integer)model.get("displaySize");
		
		String filePath = uploadedFile.getFilePath();
		int lastIndex = uploadedFile.getFilePath().lastIndexOf(".");
		StringBuilder sb = new StringBuilder();
		sb.append(filePath.substring(0, lastIndex));
		sb.append("_thumb").append(String.valueOf(size));
		sb.append(filePath.substring(lastIndex));
		
		File file = new File(sb.toString());
		String fileName = uploadedFile.getFileName();
		
		flush(response, file, fileName);
	}
}
