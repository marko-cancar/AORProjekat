package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class MicroMemPresentation extends ComponentPresentation{

	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		MicroMem mm = (MicroMem) component;
		mm.calculate();
		
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 180;
		height = 140;
	}
	
	@Override
	public void draw(Graphics g){
		Color c = g.getColor();
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke());
		
		if(hq){
			graphics2d = (Graphics2D) g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g.drawRect(x+5, y+5, width, height);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2 - fm.stringWidth(component.getName())/2+4,
				y + height/2 + 9);
		
		Port p = component.getPort(0);
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x+width/2+5, y+5, x+width/2+5, y-8);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-2, y-3, x+width/2+9, y-6);
		g.drawString(((Integer)component.getPort(0).getValue().getSize()).toString(),
				x+width/2+8, y-2);
		
		g.drawString("ADRin", x+width/2-fm.stringWidth("ADRin")/2+4, y+20);
		g.drawString("DATAout", x+width/2-fm.stringWidth("DATAout")/2+4, y+height-1);

		
		p = component.getPort(1); //out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2+4, y+height+6, x + width/2+4, y +height+30);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-4, y+height+25, x+width/2+8, y+height+9);
		g.drawString(((Integer)component.getPort(1).getValue().getSize()).toString(),
				x+width/2+8, y+height+25);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width/2-3-fm.stringWidth(Integer.toHexString(p.getValue().getUIntValue())),
				y+height+36);
		graphics2d.setStroke(new BasicStroke());
	}

}
