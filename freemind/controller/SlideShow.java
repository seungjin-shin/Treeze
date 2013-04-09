package freemind.controller;

import java.util.ArrayList;

public class SlideShow {

	ArrayList<String> imgIdx = new ArrayList<String>();
	ArrayList<String> imgNum = new ArrayList<String>();
	ArrayList<String> imgPath = new ArrayList<String>();
	
	public ArrayList<String> getImgIdx() {
		return imgIdx;
	}
	public void setImgIdx(ArrayList<String> imgIdx) {
		this.imgIdx = imgIdx;
	}
	public ArrayList<String> getImgNum() {
		return imgNum;
	}
	public void setImgNum(ArrayList<String> imgNum) {
		this.imgNum = imgNum;
	}
	public ArrayList<String> getImgPath() {
		return imgPath;
	}
	public void setImgPath(ArrayList<String> imgPath) {
		this.imgPath = imgPath;
	}

}
