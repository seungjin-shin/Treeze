package freemind.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;

class BtnListener implements ActionListener{
	JFrame frame;
	MindMapController mc;
	public BtnListener(){}
	public BtnListener(MindMapController mc){
		this.mc = mc;
	}
	public BtnListener(JFrame f) {
		frame = f;
	}
	public BtnListener(JFrame f, MindMapController mc) {
		this.mc = mc;
		frame = f;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String btn = e.getActionCommand();
		if(btn.equals("New Lecture"))
			new InputLectureFrame();
		else if(btn.equals("Create lecture")){
			UploadToServer UTS = new UploadToServer();
//			//UTS.doFileUpload("C:\\test\\양식있음 수학의 정석\\지수.jpg","http://localhost:8080/ImageUploadTest/file.jsp");
//			//UTS.doFileUpload(mmFilePath + ".mm","http://localhost:8080/ImageUploadTest/file.jsp");
			frame.setVisible(false);
		}
		else if(btn.equals("New Class")){
			new InputClassFrame(mc);
		}
		else if(btn.equals("Create class")){
			frame.setVisible(false);
		}
		else if(btn.equals("select PDF")){
			mc.open(null);
		}
	}
}