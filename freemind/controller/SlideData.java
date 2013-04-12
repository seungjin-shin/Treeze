package freemind.controller;

import java.util.ArrayList;

public class SlideData {

	String imgIdx;
	int imgNum = 0;
	String imgPath;
	String data;

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getImgIdx() {
		return imgIdx;
	}
	public void setImgIdx(String imgIdx) {
		this.imgIdx = imgIdx;
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
