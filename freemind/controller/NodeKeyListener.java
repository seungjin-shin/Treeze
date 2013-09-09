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
/*$Id: NodeKeyListener.java,v 1.16.18.2 2006/01/12 23:10:12 christianfoltin Exp $*/

package freemind.controller;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import freemind.Frame.SurveyFrame;
import freemind.json.ArrayLecture;
import freemind.json.ArrayTicket;
import freemind.json.FreemindGson;
import freemind.json.Lecture;
import freemind.json.Ticket;
import freemind.json.TmpTicket;
import freemind.json.TreezeData;
//import freemind.main.ProfileFrame.LectureListItem;
import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;
import freemind.modes.UploadToServer;
import freemind.modes.mindmapmode.MindMapController;
import freemind.view.mindmapview.EditNodeTextField;

/**
 * The KeyListener which belongs to the node and cares for Events like C-D
 * (Delete Node). It forwards the requests to NodeController.
 */
public class NodeKeyListener implements KeyListener {

	private Controller c;
	private KeyListener mListener;
	private boolean pressedShiftKey = false; 
	
	public NodeKeyListener(Controller controller) {
		c = controller;
	}

	public void register(KeyListener listener) {
		this.mListener = listener;
	}

	public void deregister() {
		mListener = null;
	}

	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_SHIFT){
			pressedShiftKey = true;
		}
		else if (e.getKeyCode() == KeyEvent.VK_F5) {
			if(pressedShiftKey){
				if(c.getSlideShow().getfocus() == null)
					return;
				
				c.getSlideShow().show();
				
				c.getSlideShow().sendPosition();
				return;
			}
		}
		else if(e.getKeyCode() == KeyEvent.VK_F6){
//			UploadToServer uts = FreemindManager.getInstance().getUploadToServer();
//			uts.doExeFileUpload();
		}
		else if(e.getKeyCode() == KeyEvent.VK_F7){
		}
		
		if (mListener != null)
			mListener.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		if (mListener != null)
			mListener.keyReleased(e);
		if(e.getKeyCode() == KeyEvent.VK_SHIFT)
			pressedShiftKey = false;
	}

	public void keyTyped(KeyEvent e) {
		if (mListener != null)
			mListener.keyTyped(e);
	}
	
}
	

