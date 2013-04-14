package freemind.controller;

public class SlideShow {
	SlideData focus;
	void setfocus(SlideData focus){
		this.focus = focus;
	}
	 SlideData getfocus(){
		return focus;
	}
	 void SlideshowStart(){
		 
	 }
	public void setfocusnext() {
		// TODO Auto-generated method stub
		this.focus = focus.getNext();
	}
}