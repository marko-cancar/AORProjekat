package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.Acc;

public class AccPresentation extends ComponentPresentation {
	
	static final int MARGIN_UP_AND_DOWN = 50;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		Acc reg = (Acc) component;
		reg.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 160;
		height = 80;
		y += MARGIN_UP_AND_DOWN/2;
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
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*7/8, x + width, y + height*7/8);
		g.setColor(c);
		g.drawString("CLK", x+width-MARGIN_SIDE/2-fm.stringWidth("CLK "), y+height*7/8+5);
		
		p = component.getPort(1);//ld
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*5/8, x + width, y + height*5/8);
		g.setColor(c);
		g.drawString("LD", x+width-MARGIN_SIDE/2-fm.stringWidth("LD "), y+height*5/8+5);
		
		p = component.getPort(2);//inc
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*5/8, x + MARGIN_SIDE/2, y + height*5/8);
		g.setColor(c);
		g.drawString("INC", x+MARGIN_SIDE/2+2, y+height*5/8+5);
		
		p = component.getPort(3);//dec
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*7/8, x + MARGIN_SIDE/2, y + height*7/8);
		g.setColor(c);
		g.drawString("DEC", x+MARGIN_SIDE/2+2, y+height*7/8+5);
		//////////////
		p = component.getPort(5);// SHL
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*3/8, x + width, y + height*3/8);
		g.setColor(c);
		g.drawString("SHL", x+width-MARGIN_SIDE/2-fm.stringWidth("CLK "), y+height*3/8+5);
		
		p = component.getPort(7);// IL
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE/2, y + height*1/8, x + width, y + height*1/8);
		g.setColor(c);
		g.drawString("IL", x+width-MARGIN_SIDE/2-fm.stringWidth("LD "), y+height*1/8+5);
		
		p = component.getPort(4);//SHR
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*3/8, x + MARGIN_SIDE/2, y + height*3/8);
		g.setColor(c);
		g.drawString("SHR", x+MARGIN_SIDE/2+2, y+height*3/8+5);
		
		p = component.getPort(6);//IR
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height*1/8, x + MARGIN_SIDE/2, y + height*1/8);
		g.setColor(c);
		g.drawString("IR", x+MARGIN_SIDE/2+2, y+height*1/8+5);
		
		p = component.getPort(8);//in
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2 - 2, y, x + width/2-2, y - MARGIN_UP_AND_DOWN/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-8, y-5, x+width/2+4, y-15);
		g.drawString(((Integer)p.getValue().getSize()).toString(),
				x+width/2+5, y-5);
		
		p = component.getPort(9);//out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2 - 2, y+height, x + width/2-2, y +height+ MARGIN_UP_AND_DOWN/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-8, y+height+15, x+width/2+4, y+height+5);
		g.drawString(((Integer)component.getPort(5).getValue().getSize()).toString(),
				x+width/2+5, y+height+15);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width/2-5-fm.stringWidth(Integer.toHexString(p.getValue().getUIntValue())),
				y+height+26);
		graphics2d.setStroke(new BasicStroke());
		
	
	}
	
}
