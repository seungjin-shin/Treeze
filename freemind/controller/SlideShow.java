package freemind.controller;

import java.util.Stack;

import javax.swing.tree.TreeNode;

import com.sun.org.apache.bcel.internal.generic.NEW;

import freemind.modes.MindMapNode;
import freemind.modes.NodeAdapter;

public class SlideShow {

	static private NodeAdapter focus =null;

	static public Stack<Integer> stacknode  = new Stack<Integer>();

	

	public static NodeAdapter getFocus() {
		return focus;
	}

	public static void setFocus(NodeAdapter focus) {
		SlideShow.focus = focus;
	}

}
