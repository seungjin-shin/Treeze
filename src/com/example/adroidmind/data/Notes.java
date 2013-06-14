package com.example.adroidmind.data;

import android.R.bool;
import android.graphics.Paint;
import android.graphics.Path;
import android.widget.PopupWindow;

public class Notes {
	String note;
	float x;
	float y;
	PopupWindow notePopupWindow;
	public PopupWindow getNotePopupWindow() {
		return notePopupWindow;
	}
	public void setNotePopupWindow(PopupWindow notePopupWindow) {
		this.notePopupWindow = notePopupWindow;
	}
	Boolean open = false;
	public Boolean getOpen() {
		return open;
	}
	public void setOpen() {
		this.open = !this.open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	static int xScale = 50;
	static int yScale = 50;
	static	public  int getXScale() {
		return xScale;
	}
	static	public  int getYScale() {
		return yScale;
	}
//	public static  void setScale(int scale) {
//		this.scale = scale;
//	}
	public Notes(float x,float y){
		this.x = x;
		this.y = y;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Object clickNote(float x, float y) {
		if (this.x < x && this.y < y && this.x+xScale > x && this.y+yScale > y) {

			return this;
		}
		return null;

	}
	public void setPopupWindow(PopupWindow notePopupWindow) {
		// TODO Auto-generated method stub
		this.notePopupWindow = notePopupWindow;
	}
}
