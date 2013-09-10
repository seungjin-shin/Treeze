package com.treeze.Module;

import java.awt.ScrollPane;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JScrollPane;

public class ListPanelMouseWheelListener implements MouseWheelListener {
	JScrollPane listPanel;

	public ListPanelMouseWheelListener(JScrollPane listPanel) {
		// TODO Auto-generated constructor stub
		this.listPanel = listPanel;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {

		listPanel.getVerticalScrollBar().setValue(
				listPanel.getVerticalScrollBar().getValue()
						+ arg0.getWheelRotation() * 10);

	}

}