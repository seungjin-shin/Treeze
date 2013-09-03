package freemind.controller;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class AddGrid {
	public Insets insets = new Insets(0, 0, 0, 0);
	public GridBagLayout gbl = new GridBagLayout();
	public GridBagConstraints gbc = new GridBagConstraints();
	
	public AddGrid() {
		gbc.fill = GridBagConstraints.BOTH;
	}
	
	public GridBagLayout getGbl() {
		return gbl;
	}
	
	public GridBagConstraints getGbc() {
		return gbc;
	}
	public Insets getInsets() {
		return insets;
	}

	public void addGrid(GridBagLayout gbl, GridBagConstraints gbc,
			Component c, int gridx, int gridy, int gridwidth, int gridheight,
			int weightx, int weighty, Container container) {
		gbc.gridx = gridx;
		gbc.gridy = gridy;
		gbc.gridwidth = gridwidth;
		gbc.gridheight = gridheight;
		gbc.weightx = weightx;
		gbc.weighty = weighty;

		gbc.insets = insets;

		gbl.setConstraints(c, gbc);
			
		container.add(c);
	}
}
