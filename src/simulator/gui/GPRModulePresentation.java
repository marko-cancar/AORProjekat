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

public class GPRModulePresentation extends ComponentPresentation{
	
	static final int MARGIN_TB = 40;

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
		
		g.drawRect(x+MARGIN_SIDE/2, y+MARGIN_TB/2, width - MARGIN_SIDE, height-MARGIN_TB);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString("GPR", 
				x + width/2-fm.stringWidth("GPR")/2,
				y+height/2+5);
		
		Port p = component.getPort(0);//wrGPR
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width, y + height/2, x + width - MARGIN_SIDE/2, y+height/2);
		g.setColor(c);
		g.drawString("WR", x + width - MARGIN_SIDE-20, y+height/2+5);
		
		p = component.getPort(1);//address
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x, y+height/2, x + MARGIN_SIDE/2, y + height/2);
		g.setColor(c);
		g.drawString("A", x+MARGIN_SIDE, y+height/2+5);
		graphics2d.setStroke(new BasicStroke(1));
		
		p = component.getPort(2);//data in
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2, y, x + width/2, y + MARGIN_TB/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-5, y+20, x+width/2+5, y+10);
		g.drawString("16",x+width/2+7, y+17);
		g.drawString("DIN", x+width/2-10, y+MARGIN_TB/2+15);
		
		p = component.getPort(3);//data out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2, y+height, x + width/2, y +height- MARGIN_TB/2);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-5, y+height-5, x+width*1/2+5, y+height-15);
		g.drawString("16",x+width*1/2+5, y+height-5);
		g.drawString("DOUT", x+width*1/2-16, y+height-MARGIN_TB/2-3);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width*1/2-15,
				y+height);
		
		graphics2d.setStroke(new BasicStroke());
		
	
	}
	
}
