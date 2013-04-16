package com.hansung.treeze.support.spring.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.hansung.treeze.model.UploadedFile;

@Component("fileView")
public class FileView extends AbstractView{
	public FileView(){
		this.setContentType("application/octet-stream");
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		UploadedFile attachFile = (UploadedFile)model.get("attachFile");
		
		File file = new File(attachFile.getFilePath());
		String fileName = attachFile.getFileName();
		
		flush(response, file, fileName);
	}

	protected void flush(HttpServletResponse response, File file, String fileName) throws UnsupportedEncodingException, IOException, FileNotFoundException {
		response.setContentType(this.getContentType());
		response.setContentLength((int)file.length());
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "utf-8" ).replaceAll("\\+", "%20")+ "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		FileInputStream fis = null;		
		try{
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		}finally{
			if(fis != null){
				fis.close();
			}
		}
		out.flush();
	}
}
