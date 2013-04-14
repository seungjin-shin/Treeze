package freemind.controller;

import java.util.ArrayList;

public class SlideData {
	SlideData next;
	SlideData prev;
	ArrayList<Integer> idxList = new ArrayList<Integer>();
	int imgCnt = 0;
	String imgPath;
	String nodeName;
	int sCnt;
	
	public SlideData getNext() {
		return next;
	}
	public void setNext(SlideData next) {
		this.next = next;
	}
	public SlideData getPrev() {
		return prev;
	}
	public void setPrev(SlideData prev) {
		this.prev = prev;
	}
	public int getsCnt() {
		return sCnt;
	}
	public void setsCnt(int sCnt) {
		this.sCnt = sCnt;
	}
	public ArrayList<Integer> getIdxList() {
		return idxList;
	}
	public void setIdxList(ArrayList<Integer> idxList) {
		this.idxList = idxList;
	}
	public int getImgCnt() {
		return imgCnt;
	}
	public void setImgCnt(int imgCnt) {
		this.imgCnt = imgCnt;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
