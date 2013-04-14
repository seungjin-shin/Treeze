package freemind.controller;

import java.util.ArrayList;

public class SlideData {

	ArrayList<Integer> idxList = new ArrayList<Integer>();
	int imgNum = 0;
	String imgPath;
	String data;
	int sCnt;
	
	public ArrayList<Integer> getIdxList() {
		return idxList;
	}
	public void setIdxList(ArrayList<Integer> idxList) {
		this.idxList = idxList;
	}
	public int getsCnt() {
		return sCnt;
	}
	public void setsCnt(int sCnt) {
		this.sCnt = sCnt;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getImgNum() {
		return imgNum;
	}
	public void setImgNum(int imgNum) {
		this.imgNum = imgNum;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
