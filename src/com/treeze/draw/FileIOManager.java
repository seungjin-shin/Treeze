package com.treeze.draw;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOManager {
	
	protected String read(String filePath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));		
		String readContent = "";
		String line = "";
		
		while((line = br.readLine()) != null) {
			readContent += line;
		}
		
		br.close();
		return readContent;
	}
	
	protected void write(String filePath, String contents) throws IOException {
		
		String path = filePath.substring(0,filePath.lastIndexOf('/'));
		
		File file = new File(path);
		file.mkdir();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
		bw.write(contents);
		bw.close();
		
	}
	
	

}
