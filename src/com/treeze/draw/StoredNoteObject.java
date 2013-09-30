package com.treeze.draw;

import java.util.ArrayList;

import javax.swing.JComponent;

import com.treeze.data.TreezeStaticData;

import JDIalog.StringDialog;


//gson Áß°è³¢ ¿ªÇÒ
public class StoredNoteObject {

	private ArrayList<LineObject> loList= new ArrayList<LineObject>();
	private ArrayList<FigureObject> foList= new ArrayList<FigureObject>();
	private ArrayList<ImageObject> ioList= new ArrayList<ImageObject>();;
	 
	
	private ArrayList<MemoObj> moList = new ArrayList<MemoObj>();
	private ArrayList<PostItObj> piList = new ArrayList<PostItObj>();
	boolean empty = false;
	public boolean isEmpty() {
		return empty;
	}



	public void setEmpty(boolean empty) {
		this.empty = empty;
	}



	public StoredNoteObject(ArrayList<DrawableObject> drawobjList,
			ArrayList<ComponentJPanel> componentList) {
		
		
		
		for (int i = 0; i < drawobjList.size(); i++) {

			DrawableObject drawObj = drawobjList.get(i);

			if (drawObj instanceof LineObject) {

				loList.add((LineObject) (drawObj));

			} else if (drawObj instanceof FigureObject) {

				foList.add((FigureObject) (drawObj));

			} else if (drawObj instanceof ImageObject) {

				ioList.add((ImageObject) (drawObj));

			}

		}
		
		for (int i = 0; i < componentList.size(); i++) {

			JComponent component = componentList.get(i);

			if (component instanceof PostItPanel) {
				PostItPanel pip = (PostItPanel) component;
				NoteManager nm = pip.getNoteManager();

				PostItObj pio = new PostItObj(pip.getX(), pip.getY(),
						pip.getWidth(), pip.getHeight(),pip.getBackgroundWidth(), pip.getBackgroundHeight(), nm.getDrawobjList(),
						nm.getComponentList());
				System.out.println("postit size : "+nm.getComponentList().size());
				piList.add(pio);
			} else if (component instanceof MemoPanel) {
				MemoPanel mp = (MemoPanel) component;
				if(mp.getWidth()==0){
					empty = true;
				}
				MemoObj mo = new MemoObj(mp.getX(), mp.getY(), mp.getWidth(),
						mp.getHeight(),TreezeStaticData.PPT_PANEL_WIDTH,TreezeStaticData.PPT_PANEL_HEIGHT, mp.getText());
				moList.add(mo);

			}
		}
		
		
	}



	public ArrayList<DrawableObject> getDOList() {
		ArrayList<DrawableObject> doList = new ArrayList<DrawableObject>();

		for (int i = 0; i < loList.size(); i++) {
			doList.add(loList.get(i));
		}

		for (int i = 0; i < foList.size(); i++) {
			doList.add(foList.get(i));
		}

		for (int i = 0; i < ioList.size(); i++) {
			doList.add(ioList.get(i));
		}

		return doList;
	}

	public ArrayList<ComponentJPanel> getCOList() {
		ArrayList<ComponentJPanel> coList = new ArrayList<ComponentJPanel>();

		for (int i = 0; i < moList.size(); i++) {
			coList.add(moList.get(i).makeComponent());
		}

		for (int i = 0; i < piList.size(); i++) {
			coList.add(piList.get(i).makeComponent());
		}

		return coList;
	}

	public ArrayList<LineObject> getLoList() {
		return loList;
	}

	public void setLoList(ArrayList<LineObject> loList) {
		this.loList = loList;
	}

	public ArrayList<FigureObject> getFoList() {
		return foList;
	}

	public void setFoList(ArrayList<FigureObject> foList) {
		this.foList = foList;
	}

	public ArrayList<ImageObject> getIoList() {
		return ioList;
	}

	public void setIoList(ArrayList<ImageObject> ioList) {
		this.ioList = ioList;
	}

	public ArrayList<MemoObj> getMoList() {
		return moList;
	}

	public void setMoList(ArrayList<MemoObj> moList) {
		this.moList = moList;
	}

	public ArrayList<PostItObj> getPiList() {
		return piList;
	}

	public void setPiList(ArrayList<PostItObj> piList) {
		this.piList = piList;
	}

}
