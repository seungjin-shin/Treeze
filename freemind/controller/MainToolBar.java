/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2001  Joerg Mueller <joergmueller@bigfoot.com>
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/*$Id: MainToolBar.java,v 1.16.14.2.4.8 2009/07/04 20:38:27 christianfoltin Exp $*/

package freemind.controller;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;

import freemind.modes.MindIcon;
import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;
import freemind.modes.mindmapmode.MindMapController;

public class MainToolBar extends FreeMindToolBar {
	private JComboBox zoom;
	Controller controller;
	String userDefinedZoom;
	private static Logger logger = null;

	public MainToolBar(final Controller controller) {
		super();
		this.setRollover(true);
		this.controller = controller;
		if (logger == null) {
			logger = controller.getFrame().getLogger(this.getClass().getName());
	
		}
		userDefinedZoom = controller.getResourceString("user_defined_zoom");

		add(controller.navigationPreviousMap);
		add(controller.navigationNextMap);
		add(controller.printDirect);
		JToggleButton btnFilter = new JToggleButton(
				controller.showFilterToolbarAction);
		// don't paint the border, in order to look like every other toolbar
		// item.
		// btnFilter.setBorderPainted(false);
		// set null margin, in order to look like every other toolbar item.
		btnFilter.setMargin(new Insets(0, 0, 0, 0));
		btnFilter.setFocusable(false);
		btnFilter.setContentAreaFilled(false);
		btnFilter
				.setToolTipText(controller.getResourceString("filter_toolbar"));
		add(btnFilter);

		zoom = new JComboBox(controller.getZooms());
		zoom.setSelectedItem("100%");
		zoom.addItem(userDefinedZoom);
		// Focus fix.
		zoom.setFocusable(false);
		add(zoom);
		zoom.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				// todo: dialog with user zoom value, if user zoom is chosen.
				// change proposed by dimitri:
				if (e.getStateChange() == ItemEvent.SELECTED) {
					setZoomByItem(e.getItem());
				}
			}
		});
	}

	private void setZoomByItem(Object item) {
		if (((String) item).equals(userDefinedZoom))
			return; // nothing to do...
		// remove '%' sign
		final float zoomValue = getZoomValue(item);
		controller.setZoom(zoomValue);

	}

	private float getZoomValue(Object item) {
		String dirty = (String) item;
		String cleaned = dirty.substring(0, dirty.length() - 1);
		// change representation ("125" to 1.25)
		final float zoomValue = Integer.parseInt(cleaned, 10) / 100F;
		return zoomValue;
	}

	public void zoomOut() {
		final float currentZoomIndex = getCurrentZoomIndex();
		if (currentZoomIndex > 0) {
			setZoomByItem(zoom.getItemAt((int) (currentZoomIndex - 0.5f)));
			
		}
	}

	private float getCurrentZoomIndex() {
		final int selectedIndex = zoom.getSelectedIndex();
		final int itemCount = zoom.getItemCount();
		if (selectedIndex != itemCount - 1) {
			return selectedIndex;
		}
		final float userZoom = controller.getView().getZoom();
		for (int i = 0; i < itemCount - 1; i++) {
			if (userZoom < getZoomValue(zoom.getItemAt(i))) {
				return i - 0.5f;
			}
		}
		return itemCount - 0.5f;
	}

	public void zoomIn() {
		final float currentZoomIndex = getCurrentZoomIndex();
		if (currentZoomIndex < zoom.getItemCount() - 1) {
			setZoomByItem(zoom.getItemAt((int) (currentZoomIndex + 1f)));
		}
	}

	public String getItemForZoom(float f) {
		return (int) (f * 100F) + "%";
	}

	public void setZoomComboBox(float f) {
		logger.fine("setZoomComboBox is called with " + f + ".");
		String toBeFound = getItemForZoom(f);
		for (int i = 0; i < zoom.getItemCount(); ++i) {
			if (toBeFound.equals((String) zoom.getItemAt(i))) {
				// found
				zoom.setSelectedItem(toBeFound);
				return;
			}
		}
		zoom.setSelectedItem(userDefinedZoom);
	}

	public void setAllActions(boolean enabled) {
		if (zoom != null) {
			zoom.setEnabled(enabled);
		}
	}

	public void SlideShowStart() {
		SlideShow slide = new SlideShow();
		slide.stacknode.clear();
		slide.setFocus((NodeAdapter) controller.getMap().getRootNode());
		slide.getFocus().setBackgroundColor(Color.BLUE);
	}

	public void SlideShow() {
		// TODO Auto-generated method stub
		SlideShow slide = new SlideShow();
		 
		  
		int temp;
		if (slide.getFocus() == null) {
			//slide.getFocus().getChildAt(0);
			// slide.setFocus(controller.getMap().getRootNode());
			// 진행안됨;
			
			return;

		}// 슬라이드쇼가 시작안될경우
		else if (slide.getFocus().isLeaf()) { //립 일경우 (자식없을경우_)
			System.out.println(slide.getFocus().toString());	
			//slide.getFocus().setColor(Color.RED);
			slide.getFocus().setBackgroundColor(Color.YELLOW);
			slide.getFocus().setDid(true);
			//slide.getFocus().addIcon(MindIcon.factory, position)
			slide.getFocus().setStyle("bubble");
			String iconName = new String("full-3");
            int position = 1;
           // MindIcon icon = MindIcon.factory(iconName);
           // slide.getFocus().addIcon(icon, position);
            //modeController.nodeChanged(node);
			
			while (true) {
				
				if (slide.stacknode.isEmpty()) {
					System.out.println("마지막슬라이드입니다.");
					slide.setFocus(null);
					return;
				}
				temp = slide.stacknode.pop() + 1;
				if (slide.getFocus().getParent().getChildCount() - 1 >= temp) {
					 
					slide.setFocus((NodeAdapter) slide.getFocus().getParent()
				.getChildAt(temp));
					slide.stacknode.push(temp);
					break;
				} else{
					
					slide.setFocus((NodeAdapter) slide.getFocus().getParent());
				}

			}
		} else {
			//controller.getView().arrowadd((MindMapNode)slide.getFocus(), (MindMapNode)slide.getFocus().getChildAt(0));
			while (slide.getFocus().getChildCount() != 0) {
				System.out.println(slide.getFocus().toString());
				slide.getFocus().setBackgroundColor(Color.YELLOW);
				slide.getFocus().getChildAt(0);
				slide.getFocus().setDid(true);
				//slide.getFocus().setBold(true);
				//slide.getFocus().setBackgroundColor(Color.RED);
				slide.setFocus((NodeAdapter) slide.getFocus().getChildAt(0));
				slide.stacknode.push(0);
				break;

			}
		}

	}
}