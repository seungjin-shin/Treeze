package com.treeze.Module;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import com.treeze.data.TreezeStaticData;
import com.treeze.uploadthread.CreateNote;

public class CreateNoteFileThread extends Thread {
	private Long classId;
	private String nodeId;
	private String contents;
	private String userEmail;

	public CreateNoteFileThread(Long classId, String nodeId, String contents,
			String userEmail) {
		// TODO Auto-generated constructor stub

		this.classId = classId;
		this.nodeId = nodeId;
		this.contents = contents;
		this.userEmail = userEmail;
		this.setPriority(MAX_PRIORITY);
		System.out.println(contents);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		File notePath =new File(TreezeStaticData.NOTE_PATH);
		
		// SDCardRoot.mkdir();
		notePath.mkdirs();
		// + "/Trezee/" + classinfo.getClassId()
		File file = new File(notePath, classId+"_"+nodeId+".txt");
	
			
			try {
				FileOutputStream fileOutput = new FileOutputStream(file);
				Writer out = new BufferedWriter(new OutputStreamWriter(
					    new FileOutputStream(file), "UTF-8"));
				out.write(contents);
				
				fileOutput.close();
				 out.close();
				 new CreateNote(classId, nodeId, contents, userEmail).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("[파일생성실패]");
				e.printStackTrace();
			}

		
	
	
	}
}
