package freemind.controller;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

import javax.swing.ImageIcon;

import freemind.modes.NodeAdapter;

public class SetResizeImgThread extends Thread{
	FreemindManager fManager;
	public SetResizeImgThread() {
		fManager = FreemindManager.getInstance();
	}
	
	public void run() {
		NodeAdapter tmp;
		Toolkit tk = Toolkit.getDefaultToolkit();
		double xsize = tk.getScreenSize().getWidth();
		double ysize = tk.getScreenSize().getHeight();
		HashMap<String, Image> resizeImgHashMap = fManager.slideShow.getResizeImgHashMap();
		tmp = (NodeAdapter) fManager.getMc().getRootNode();
		
		while(tmp != null){
			if(tmp.getImgPath() != null){
				ImageIcon imgIcon = new ImageIcon(fManager.getDownPath() + System.getProperty("file.separator") + fManager.getClassId() + System.getProperty("file.separator") + tmp.getImgPath() + ".jpg");
				Image img = fManager.makeResizedImageIcon((int)xsize, (int)ysize, imgIcon.getImage()).getImage();
				resizeImgHashMap.put(tmp.getImgPath(), img);
			}
			tmp = tmp.getNext();
		}
		fManager.setSlideShowInfo(true);
		System.out.println("SetResizeImgThread : set SlideShow Info");
	}
}
