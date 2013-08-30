package com.treeze.draw;

import java.util.ArrayList;

import javax.swing.JComponent;


//gson Áß°è³¢ ¿ªÇÒ
public class StoredNoteObject {

	private ArrayList<LineObject> loList;
	private ArrayList<FigureObject> foList;
	private ArrayList<ImageObject> ioList;

	private ArrayList<MemoObj> moList;
	private ArrayList<PostItObj> piList;
	
	public StoredNoteObject(ArrayList<DrawableObject> drawobjList,
			ArrayList<ComponentJPanel> componentList) {
		
		loList = new ArrayList<LineObject>();
		foList = new ArrayList<FigureObject>();
		ioList = new ArrayList<ImageObject>();
		
		moList = new ArrayList<MemoObj>();
		piList = new ArrayList<PostItObj>();
		
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
				MemoObj mo = new MemoObj(mp.getX(), mp.getY(), mp.getWidth(),
						mp.getHeight(),mp.getBackgroundWidth(),mp.getBackgroundHeight(), mp.getText());
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
