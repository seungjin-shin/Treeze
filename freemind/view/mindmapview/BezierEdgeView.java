/*FreeMind - A Program for creating and viewing Mindmaps
 *Copyright (C) 2000-2001  Joerg Mueller <joergmueller@bigfoot.com>
 *See COPYING for Details
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/*$Id: BezierEdgeView.java,v 1.13.18.1.2.6 2008/06/09 21:01:15 dpolivaev Exp $*/

package freemind.view.mindmapview;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.RenderingHints.Key;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.CubicCurve2D.Float;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.lang.annotation.Target;
import java.text.AttributedCharacterIterator;
import java.util.Map;

import org.codehaus.groovy.control.Phases;

/**
 * This class represents a single Edge of a MindMap.
 */
public class BezierEdgeView extends EdgeView {

	CubicCurve2D.Float graph = new CubicCurve2D.Float();
	progressNodeThread pthread;

	
	private static final int XCTRL = 12;// the distance between endpoint and
										// controlpoint
	private static final int CHILD_XCTRL = 20; // -||- at the child's end

	public BezierEdgeView() {
		super();
	}

	private void update() {
		// YCTRL could be implemented but then we had to check whether target is
		// above or below source.
		int sign = (getTarget().isLeft()) ? -1 : 1;
		int sourceSign = 1;
		if (getSource().isRoot()
				&& !VerticalRootNodeViewLayout.USE_COMMON_OUT_POINT_FOR_ROOT_NODE) {
			sourceSign = 0;
		}
		int xctrl = getMap().getZoomed(sourceSign * sign * XCTRL);
		int childXctrl = getMap().getZoomed(-1 * sign * CHILD_XCTRL);
		graph.setCurve(start.x, start.y,start.x + xctrl, start.y, end.x
				+ childXctrl, end.y, end.x, end.y);
	}

	@SuppressWarnings("deprecation")
	protected void paint(Graphics2D g) {
		update();
		 Stroke stroke;
		final Color color = getColor();

		if (getTarget().getModel().getDid()) {
			g.setColor(Color.RED);
			 stroke = new BasicStroke(getMap().getZoom()+3,
			 BasicStroke.CAP_BUTT,
			 BasicStroke.JOIN_MITER);	
//			if(pthread == null) pthread = new progressNodeThread();
//			if(pthread.isAlive()){
//				pthread.stop();
//				System.out.println("ÀÎÅÍ·´Æ®");
//		}
//			pthread = new progressNodeThread();
//				pthread.set(g, graph);
//				pthread.start();
//				return;
//				
			}
		 else {
			stroke = getStroke();
			g.setColor(Color.BLACK);
		}
		
		
		g.setStroke(stroke);
	
		g.draw(graph);
	
		//System.out.println(graph.x1 + " " + graph.x2);
		if (isTargetEclipsed()) {

			g.setColor(g.getBackground());
			g.setStroke(getEclipsedStroke());
			g.draw(graph);
			// g.setStroke(stroke);
			g.setColor(color);
		}
	}

	public Color getColor() {
		return getModel().getColor();
	}
	class  progressNodeThread extends Thread{
		 Graphics2D g  ;
		CubicCurve2D.Float graph ;
		public progressNodeThread(Graphics2D g, Float graph) {
			// TODO Auto-generated constructor stub
			this.g = g;
			this.graph = graph;
			
		}
	
		public progressNodeThread() {
			// TODO Auto-generated constructor stub
		}
		public void set(Graphics2D g, Float graph){
			this.g = g;
			this.graph = graph;
		}
		public void run(){
			Stroke stroke;
			int a=1;
			System.out.println(graph.x1+" "+graph.x2+" "+graph.ctrlx1+" "+graph.ctrlx2);
		
			g.drawLine((int)graph.x1, (int)graph.y1,(int)graph.x2/2+100,(int) graph.y2);
			g.clearRect((int)graph.x2,(int) graph.y2, 10,10);
			g.drawLine((int)graph.x1, (int)graph.y1,(int)graph.x2/2,(int) graph.y2);
			
//			while(true){
//				if(a>graph.x2) break;
//				g.drawLine((int)graph.x1, (int)graph.y1,a,(int) graph.y2);
//				a = a+10;
//				
//			}
//			graph.x2 /=2;
//			while (true){
//			
//			
//					//System.out.println("Â¦");
//				float dash[] = {10.0f,10.0f };
//			stroke = new BasicStroke(3,
//					 BasicStroke.CAP_BUTT,
//					 BasicStroke.JOIN_MITER);
//			
//				g.setStroke(stroke);	
//				g.draw(graph);
//				
//				
//	
//				//System.out.println(graph.x1+" "+graph.x2);
//				//System.out.println(a);
//					
//				
//			
//				
//			}
			
		}
	}
}
