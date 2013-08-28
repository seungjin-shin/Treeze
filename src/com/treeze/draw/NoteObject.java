package com.treeze.draw;

import javax.swing.JPanel;

public class NoteObject {
	
	int x;
	int y;
	int width;
	int height;
	protected transient ClickPanel clickPanel;
	
	
	public void removeSelectedItem(NoteManager nm) {
		if (clickPanel != null && clickPanel.isVisible()) {
			clickPanel.removeClicked();
		}
	}
	
	public void setUnClicked(NoteManager nm) {

		if (clickPanel != null && clickPanel.isVisible()) {

			JPanel panel = (JPanel)clickPanel.getParent();
			panel.remove(clickPanel);
			clickPanel = null;
		}
	}
	

	public ClickPanel getClickPanel() {
		return clickPanel;
	}

	public void setClickPanel(ClickPanel clickPanel) {
		this.clickPanel = clickPanel;
	}

	


	


}
