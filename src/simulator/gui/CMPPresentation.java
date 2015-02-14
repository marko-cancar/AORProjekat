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

public class CMPPresentation extends ComponentPresentation{

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
		width = 100;
		height = 120;
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
		
		g.drawRect(x+MARGIN_SIDE/2, y, width-MARGIN_SIDE, height);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2-fm.stringWidth(component.getName())/2,
				y+height/2+5);
		
		
		//A,B
		graphics2d.setStroke(new BasicStroke(4));
		for(int i=0; i<2; i++){
			Port p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x, y + height*(1+i)/3, 
					x + MARGIN_SIDE/2, y + height*(1+i)/3);
			g.setColor(c);
			String s = "A";
			if(i==1) s = "B";
			g.drawString(s, x+MARGIN_SIDE/2 + 2, y + height*(1+i)/3+5);	
		}
		
		graphics2d.setStroke(new BasicStroke(1));

		//output signal
		Port p = component.getPort(2);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width-MARGIN_SIDE/2, y+height/2, x + width,  y+height/2);
		g.setColor(c);
	}
	
}
