package freemind.controller;

import java.awt.Robot;
import java.awt.event.KeyEvent;

public class BlockScreenSaverThread extends Thread{
	
	@Override
	public void run() {
		try {
			Robot robot = new Robot();
			while (true) {
				Thread.sleep(55 * 1000); // one minute
				robot.keyPress(KeyEvent.VK_F14);
				Thread.sleep(50);
				robot.keyRelease(KeyEvent.VK_F14);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
