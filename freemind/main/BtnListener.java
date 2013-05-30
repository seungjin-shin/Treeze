package freemind.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;

public class BtnListener implements ActionListener{
	JFrame frame;
	public BtnListener(){}
	
	public BtnListener(JFrame f) {
		frame = f;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.setVisible(false);
	}
}