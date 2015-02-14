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

public class MemModulePresentation extends ComponentPresentation{
	
	static final int MARGIN_LEFT = 10;
	static final int MARGIN_RIGHT = 30;
	static final int MARGIN_BOTTOM = 30;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		MemModule mm = (MemModule) component;
		mm.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 200;
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
		
		g.drawRect(x+MARGIN_LEFT, y, width - MARGIN_RIGHT-MARGIN_LEFT, height-MARGIN_BOTTOM);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString("MEM MODULE", 
				x + (width-22)/2-fm.stringWidth("MEM MODULE")/2,
				y+(height-MARGIN_BOTTOM)/2+5);
		
		Port p = component.getPort(0);//rd
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + (height-MARGIN_BOTTOM)*1/3, x + MARGIN_LEFT, y+(height-MARGIN_BOTTOM)*1/3);
		g.setColor(c);
		g.drawString("RD", x + MARGIN_LEFT+5, y+(height-MARGIN_BOTTOM)*1/3+5);
		
		p = component.getPort(1);//wr
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + (height-MARGIN_BOTTOM)*2/3, x + MARGIN_LEFT, y + (height-MARGIN_BOTTOM)*2/3);
		g.setColor(c);
		g.drawString("WR", x+MARGIN_LEFT+5, y+(height-MARGIN_BOTTOM)*2/3+5);
		
		p = component.getPort(2);//addres in
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width, y+height/2, x + width-MARGIN_RIGHT, y + height/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width - 20, y + height/2 +5,
				x+width - 10, y + height/2 -5);
		g.drawString("16", x+width- 20, y + height/2 -5);
		
		p = component.getPort(3);//data in
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width*2/3, y+height, x + width*2/3, y +height- MARGIN_BOTTOM);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width*2/3-5, y+height-10, x+width*2/3+5, y+height-20);
		g.drawString("8",x+width*2/3-10, y+height-17);
		g.drawString("DIN", x+width*2/3-10, y+height-MARGIN_BOTTOM-3);
		
		p = component.getPort(4);//data out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width*1/4, y+height, x + width*1/4, y +height- MARGIN_BOTTOM);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width*1/4-5, y+height-10, x+width*1/4+5, y+height-20);
		g.drawString("8",x+width*1/4-10, y+height-17);
		g.drawString("DOUT", x+width*1/4-16, y+height-MARGIN_BOTTOM-3);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width*1/4 + 5,
				y+height-10);
		
		graphics2d.setStroke(new BasicStroke());
		
	
	}
	
}
