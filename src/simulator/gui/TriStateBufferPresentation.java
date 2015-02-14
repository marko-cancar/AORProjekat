package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class TriStateBufferPresentation extends ComponentPresentation{
	
	static final int MARGIN_BOT = 10;
	static final int MARGIN_SIDE = 30;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		TriStateBuffer tsb = (TriStateBuffer) component;
		tsb.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 60;
		height = 30;
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
		
		g.drawLine(x+MARGIN_SIDE/2, y +(height-MARGIN_BOT)/2,x+width-MARGIN_SIDE/2, y);
		g.drawLine(x+MARGIN_SIDE/2, y +(height-MARGIN_BOT)/2,
				x+width-MARGIN_SIDE/2, y+height-MARGIN_BOT);
		g.drawLine(x+width-MARGIN_SIDE/2, y,x+width-MARGIN_SIDE/2, y+height-MARGIN_BOT);
		
		Port p = component.getPort(0);//enable
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + (width)/2, y + (height-MARGIN_BOT)*3/4,
				x + (width)/2, y + height);
		
		int dataSize = ((TriStateBuffer)component).getDataSize();
		
		p = component.getPort(1);//in
		g.setColor(ColorSignalConverter.convert(p));
		if(dataSize>1){
			graphics2d.setStroke(new BasicStroke(4));
		}
		g.drawLine(x + width - MARGIN_SIDE/2 , y+(height-MARGIN_BOT)/2, 
				x + width, y+(height-MARGIN_BOT)/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		if(dataSize>1){
			g.drawLine(x + width - MARGIN_SIDE/4-6, y+(height-MARGIN_BOT)/2+6, 
				x + width - MARGIN_SIDE/4+4, y+(height-MARGIN_BOT)/2-6);
			g.drawString(((Integer)p.getValue().getSize()).toString(),
				x + width - MARGIN_SIDE/4, y+(height-MARGIN_BOT)/2-5);
		}
		
		p = component.getPort(2);//out
		g.setColor(ColorSignalConverter.convert(p));
		if(dataSize>1){
			graphics2d.setStroke(new BasicStroke(4));
		}
		g.drawLine(x, y+(height-MARGIN_BOT)/2, 
				x + MARGIN_SIDE/2, y+(height-MARGIN_BOT)/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		if(dataSize>1){
			g.drawLine(x + 2, y+(height-MARGIN_BOT)/2+6, 
					x + 12, y+(height-MARGIN_BOT)/2-6);
			g.drawString(((Integer)p.getValue().getSize()).toString(),
					x + 6, y+(height-MARGIN_BOT)/2-5);
		}
		if(!p.getValue().isHighZ())
			g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x + 2, y+(height-MARGIN_BOT)/2+15);
		else{
			g.drawString("HIGH_Z",
					x -20, y+(height-MARGIN_BOT)/2+25);
		}
		
		g.setColor(c);
	}
	
}
