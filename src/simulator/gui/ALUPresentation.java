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

public class ALUPresentation extends ComponentPresentation{
	
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
		width = 200;
		height = 130;
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
		
		g.drawRect(x + MARGIN_SIDE/2, y + MARGIN_BOTTOM/2, width - MARGIN_SIDE, height - MARGIN_BOTTOM);
		
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
		
		/*boolean add = ports.get(2).getValue().getBooleanValue();
		boolean sub = ports.get(3).getValue().getBooleanValue();
		boolean and = ports.get(4).getValue().getBooleanValue();
		boolean or = ports.get(5).getValue().getBooleanValue();
		boolean xor = ports.get(6).getValue().getBooleanValue();
		boolean not = ports.get(7).getValue().getBooleanValue();
		Port out = ports.get(8);
		Port c15 = ports.get(9);*/
		
		graphics2d.setStroke(new BasicStroke());
		
		Port p = component.getPort(2);//add
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + (height-MARGIN_BOTTOM)*1/4 + MARGIN_BOTTOM/2, 
				x + MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*1/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("add", x+MARGIN_SIDE/2 + 5, y + (height-MARGIN_BOTTOM)*1/4+MARGIN_BOTTOM/2+5);
		
		
		p = component.getPort(3);//sub
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + (height-MARGIN_BOTTOM)*2/4 + MARGIN_BOTTOM/2, 
				x + MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*2/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("sub", x+MARGIN_SIDE/2 + 5, y + (height-MARGIN_BOTTOM)*2/4+MARGIN_BOTTOM/2+5);
		
		
		p = component.getPort(4);//and
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + (height-MARGIN_BOTTOM)*3/4 + MARGIN_BOTTOM/2, 
				x + MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*3/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("and", x+MARGIN_SIDE/2 + 5, y + (height-MARGIN_BOTTOM)*3/4+MARGIN_BOTTOM/2+5);
		
		
		p = component.getPort(5);//or
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width-MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*1/4 + MARGIN_BOTTOM/2, 
				x + width, y + (height-MARGIN_BOTTOM)*1/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("or", x+width-MARGIN_SIDE/2 - fm.stringWidth("or "),
				y + (height-MARGIN_BOTTOM)*1/4+MARGIN_BOTTOM/2+5);
		
	
		p = component.getPort(6);//xor
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width-MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*2/4 + MARGIN_BOTTOM/2, 
				x + width, y + (height-MARGIN_BOTTOM)*2/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("xor", x+width-MARGIN_SIDE/2 - fm.stringWidth("xor "),
				y + (height-MARGIN_BOTTOM)*2/4+MARGIN_BOTTOM/2+5);
		
		
		p = component.getPort(7);//not
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width-MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)*3/4 + MARGIN_BOTTOM/2, 
				x + width, y + (height-MARGIN_BOTTOM)*3/4+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("not", x+width-MARGIN_SIDE/2 - fm.stringWidth("not "),
				y + (height-MARGIN_BOTTOM)*3/4+MARGIN_BOTTOM/2+5);
		

		//output signal
		p = component.getPort(8);
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2, y, x + width/2,  y + MARGIN_BOTTOM/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x + width/2 -5, y + 15,
				x + width/2+5, y + 5);
		g.drawString("16", x + width/2-18, y+15);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
			x + width/2 + 10,y+ +5);
		
		g.setColor(c);
		
		
		p = component.getPort(9);//C15
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width*4/5-MARGIN_SIDE, y, 
				x + width*4/5 - MARGIN_SIDE, y+MARGIN_BOTTOM/2);
		g.setColor(c);
		g.drawString("C15", x+width*4/5-MARGIN_SIDE - 8,
				y+MARGIN_BOTTOM/2+12);
	}
	
}
