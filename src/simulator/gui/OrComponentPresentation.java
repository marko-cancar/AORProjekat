package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;

import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class OrComponentPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		OrGate orGate = (OrGate) component;
		orGate.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 60;
		height = 40;
	}
	
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke());
		
		if(hq){
			graphics2d = (Graphics2D) g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g.setColor(Color.black);
		Arc2D.Double arc2d = new Arc2D.Double(x, y, width-MARGIN_SIDE/2, height, 90, -180, Arc2D.OPEN);
		graphics2d.draw(arc2d);
		arc2d = new Arc2D.Double(x-width/2+7, y, width-MARGIN_SIDE/2, height, 90, -180, Arc2D.OPEN);
		graphics2d.draw(arc2d);
		g.drawLine(x+MARGIN_SIDE/2, y, x+width/2-MARGIN_SIDE/2+1, y);
		g.drawLine(x+MARGIN_SIDE/2, y+height, x+width/2-MARGIN_SIDE/2+1, y+height);
		g.setColor(c);
		
		int numInputs = ((OneOutputGate) component).getNumInputs();
		boolean odd = false;
		if(numInputs%2==1){
			odd = true;
			numInputs--;
		}
		int inc = height/2-5;
		int distance = inc;
		int mid = y+height/2;
		for(int i=0; i<numInputs; i++){
			if(i%2==0 && i>0)distance += inc;
			Port p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			double lenght = Math.sqrt(Math.pow(width/2,2)-Math.pow(distance,2));
			if(i>1) lenght = MARGIN_SIDE/2;
			g.drawLine(x, (int) (mid+ Math.pow(-1, i)*distance),
					(int) (x + lenght) , (int) (mid + Math.pow(-1, i)*distance));
			g.setColor(Color.black);
			if(i>1){
				g.drawLine(x+MARGIN_SIDE/2, (int) (mid + Math.pow(-1,i)*distance),
					x + MARGIN_SIDE/2, (int) (y+height/2*(1+Math.pow(-1,i))));
			}
		}
		
		if(odd){
			Port p = component.getPort(numInputs-1);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x, mid,x + width/2, mid);
		}
		
		Port p = component.getPort(numInputs);//out
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width-MARGIN_SIDE/2, mid,x + width, mid);
		
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke());
	}
	
}
