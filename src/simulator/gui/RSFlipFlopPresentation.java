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

public class RSFlipFlopPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		RSFlipFlop rsff = (RSFlipFlop) component;
		rsff.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 110;
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
		
		g.drawRect(x+MARGIN_SIDE/2, y, width - MARGIN_SIDE, height);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2-fm.stringWidth(component.getName())/2,
				y+height/2+5);
		
		Port p = component.getPort(0);//clk
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*1/2, x + MARGIN_SIDE/2, y + height*1/2);
		g.setColor(c);
		g.drawString("CLK", x+MARGIN_SIDE/2+4, y+height*1/2+5);
		
		p = component.getPort(1);//set
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*1/6, x + MARGIN_SIDE/2, y + height*1/6);
		g.setColor(c);
		g.drawString("S", x+MARGIN_SIDE/2+4, y+height*1/6+5);
		
		p = component.getPort(2);//reset
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*5/6, x + MARGIN_SIDE/2, y + height*5/6);
		g.setColor(c);
		g.drawString("R", x+MARGIN_SIDE/2+4, y+height*5/6+5);
		
		p = component.getPort(3);//Q
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*1/6, x + width, y + height*1/6);
		g.setColor(c);
		g.drawString("Q", x+width - MARGIN_SIDE/2 - fm.stringWidth("Q "), y+height*1/6+5);
		
		p = component.getPort(4);//notQ
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*5/6, x + width, y + height*5/6);
		g.setColor(c);
		g.drawString("notQ", x+width - MARGIN_SIDE/2 - fm.stringWidth("notQ "), y+height*5/6+5);
		
		graphics2d.setStroke(new BasicStroke());
	}
	
}
