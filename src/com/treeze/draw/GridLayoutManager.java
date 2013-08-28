package com.treeze.draw;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;

public class GridLayoutManager {

	GridBagLayout gbl;
	GridBagConstraints gbc;
	JComponent jc;
	Insets insets;

	public GridLayoutManager(JComponent jc) {
		// TODO Auto-generated constructor stub
		this.jc = jc;
		gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		insets = new Insets(0, 0, 0, 0);
		jc.setLayout(gbl);

	}
	//처음 행렬 
	//두번째 먹는거
	//세번째 비율
	protected void addGrid(Component component, int gridx, int gridy, int gridwidth,
			int gridheight, int weightx, int weighty, Container container) {
		// make GBC
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;
		gbc.insets = insets;

		gbl.setConstraints(component, gbc);
		container.add(component);
	}

}
