package com.treeze.Module;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.treeze.data.TreezeStaticData;
import com.treeze.draw.ComponentJPanel;
import com.treeze.draw.DrawableObject;
import com.treeze.draw.NoteManager;
import com.treeze.draw.StoredNoteObject;

public class GetNote {
	StringBuffer sbResult = new StringBuffer();

	private String classId;
	private String userEmail;
	private String nodeId;
	private NoteManager nm;
	private Gson gson;
	
	public GetNote(String classId, String userEmail, String nodeId,
			NoteManager nm) {
		// TODO Auto-generated constructor stub

		this.classId = classId;
		this.userEmail = userEmail;
		this.nodeId = nodeId;
		this.nm = nm;
		gson = new Gson();
		File file = new File(TreezeStaticData.NOTE_PATH+File.separator+classId+"_"+nodeId+".txt");
		FileInputStream fileInputStream;
		
		try {
			
			
			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append('\n');
		            line = br.readLine();
		        }
			
		        String note = sb.toString();
			//fileInputStream으로 배열의 내용을 읽어오기
//			fileInputStream.read(buffer);
			
			int content;
			int size = (int) file.length();
			//String note = new String();
			 
			
//			while ((content = reader.read()) != -1) {
//				// convert to char and display it
//				note += (char)content;
//				
//			}
			System.out.println("[GetNote]");
			System.out.println(note);
			StoredNoteObject sno = gson.fromJson(note, StoredNoteObject.class);
			if(sno == null||sno.getMoList()==null) {
				System.out.println("서버에 저장된 필기를 못가져왔씁니다.");
			}else {
				ArrayList<ComponentJPanel> coList =sno.getCOList();
				ArrayList<DrawableObject> doList =sno.getDOList();
				
				for(int i = 0; i < coList.size(); i++) {
					nm.addComponentJPanel(coList.get(i));
				}
				
				for(int i = 0; i < doList.size(); i++) {
					nm.addDrawableObject(doList.get(i));
				}
				nm.setFeatureByRate();
				nm.restore();
				nm.repaint();
				
			}
			//자원해제
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//바이트 길이만큼의 바이트형 배열 생성
		
	}
	
	
}
