package com.treeze.Abstract;

import com.treeze.data.MindNode;

public class NodeInterfaceQuiz implements NodeInterface{

	@Override
	public void getAction(MindNode node) {
		// TODO Auto-generated method stub
		node.StartQuiz();
	}

}
