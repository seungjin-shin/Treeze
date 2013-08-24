package com.treeze.draw;
import java.awt.event.ActionEvent;




import javax.swing.JFrame;

import com.treeze.data.MindNode;
import com.treeze.data.TreezeStaticData;
import com.treeze.util.Var;




public class PPTFrame extends JFrame {
	
	PPTPanel pptPanel;
//	public static final String PPT_ADDR = "C:/Users/dookim/workspace/DrawModule/";

	
	
	public PPTFrame(MindNode node) {
		// TODO Auto-generated constructor stub
		
		pptPanel = new PPTPanel(TreezeStaticData.PPT_IMG_PATH + "/"+node.getImgPath()+".jpg");
		setBounds(500, 100, 600, 500);
		
		pptPanel.setVisible(true);
		this.add(pptPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}



	public void actionPerformed(ActionEvent e) {
		repaint(); // update () -> paint() 순서로 호출
	}



}
