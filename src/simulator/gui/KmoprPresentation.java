package simulator.gui;

import java.awt.*;
import simulator.*;
import simulator.digitalcomponents.*;

public class KmoprPresentation extends ComponentPresentation{
private int portDist;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		Kmopr kmopr = (Kmopr) component;
		kmopr.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 180;
		height = 80;
		portDist = 36;
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
		g.drawString("KMOPR", 
				x + width/2 - fm.stringWidth("KMOPR")/2+4,
				y + height/2 + 9);
		
		Port p = component.getPort(0);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+portDist+5, y+5, x+portDist+5, y-8);
		g.setColor(c);
		g.drawString(""+0, x+portDist+2, y+20);
		g.drawString(Integer.toHexString(Kmopr.OUTPUT[0]), x+portDist+2, y+height-1);
		
		p = component.getPort(1);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+portDist*2+5, y+5, x+portDist*2+5, y-8);
		g.setColor(c);
		g.drawString(""+1, x+portDist*2+2, y+20);
		g.drawString(Integer.toHexString(Kmopr.OUTPUT[1]), x+portDist*2+2, y+height-1);
		
		g.drawString(". . .", x+portDist*3, y+20);
		g.drawString(". . .", x+portDist*3, y+height-1);
		
		p = component.getPort(Kmopr.PORTS_CNT-1);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+portDist*4+5, y+5, x+portDist*4+5, y-8);
		g.setColor(c);
		g.drawString(""+(Kmopr.PORTS_CNT-1), x+portDist*4+2, y+20);
		g.drawString(Integer.toHexString(Kmopr.OUTPUT[Kmopr.PORTS_CNT-1]), x+portDist*4+2, y+height-1);
		
		
		p = component.getPort(Kmopr.PORTS_CNT);//out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2+4, y+height+6, x + width/2+4, y +height+30);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-4, y+height+25, x+width/2+8, y+height+9);
		g.drawString(((Integer)component.getPort(Kmopr.PORTS_CNT).getValue().getSize()).toString(),
				x+width/2+8, y+height+25);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width/2-3-fm.stringWidth(Integer.toHexString(p.getValue().getUIntValue())),
				y+height+36);
		graphics2d.setStroke(new BasicStroke());
	}
}
