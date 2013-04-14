package freemind.controller;

public class SlideShow {
	SlideData focus;
	void setfocus(SlideData focus){
		this.focus = focus;
		System.out.println(focus.nodeName);
	}
	 SlideData getfocus(){
		return focus;
	}
	 void SlideshowStart(){
		 
	 }
	public void setfocusnext() {
		// TODO Auto-generated method stub
		if(focus.getNext()!=null)
		 this.setfocus(focus.getNext());
	}
	public void show() {
		// TODO Auto-generated method stub
	
	}
}