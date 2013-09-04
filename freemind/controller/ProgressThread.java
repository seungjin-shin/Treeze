package freemind.controller;

public class ProgressThread extends Thread{
@Override
public void run() {
	ProgressFrame progFrame = new ProgressFrame();
	int i = 0;
	while(i != 100){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		progFrame.getBar().setValue(i);
		progFrame.repaint();
		i++;
	}
	progFrame.setVisible(false);
}
}
