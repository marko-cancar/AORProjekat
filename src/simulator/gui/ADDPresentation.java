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

public class ADDPresentation extends ComponentPresentation{
	
	static final int MARGIN_BOTTOM = 40;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		MultiplexerComponent mx = (MultiplexerComponent) component;
		mx.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 140;
		height = 90;
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
		
		g.drawRect(x, y + MARGIN_BOTTOM/2, width, height - MARGIN_BOTTOM);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2-fm.stringWidth(component.getName())/2,
				y+height/2+5);
		
		
		//A,B
		graphics2d.setStroke(new BasicStroke(4));
		for(int i=0; i<2; i++){
			Port p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x+width*(1+i)/3, y + height -MARGIN_BOTTOM/2, 
					x + width*(1+i)/3, y + height);
			g.setColor(c);
			String s = "A";
			if(i==1) s = "B";
			g.drawString(s, x+width*(1+i)/3 -2, y + height-MARGIN_BOTTOM/2-5);	
			graphics2d.setStroke(new BasicStroke(1));
			g.drawLine(x+width*(1+i)/3-5, y + height-5,
					x+width*(1+i)/3+5,  y + height-15);
			g.drawString("16",x+width*(1+i)/3-18, y + height-4);
			graphics2d.setStroke(new BasicStroke(4));
		}

		//output signal
		Port p = component.getPort(2);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width/2, y, x + width/2,  y + MARGIN_BOTTOM/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x + width/2 -5, y + 15,
				x + width/2+5, y + 5);
		g.drawString("16", x + width/2-18, y+15);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
			x + width/2 + 10,y+ +5);
		
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke());
	}
	
}
