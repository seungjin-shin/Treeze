package freemind.controller;

public class ProgressThread extends Thread{
@Override
public void run() {
	ProgressFrame progFrame = new ProgressFrame();
	int i = 0;
	while(progFrame.getBar().getValue() != 100){
		System.out.println("whlie");
		progFrame.getBar().setValue(i);
//		progFrame.repaint();
		i++;
	}
	progFrame.setVisible(false);
}
}
