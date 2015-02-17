package simulator.gui;

import simulator.*;
import simulator.digitalcomponents.Kmadr;

import java.awt.*;

public class KmadrPresentation extends ComponentPresentation{

	private int portDist;
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		Kmadr kmadr = (Kmadr) component;
		kmadr.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 180;
		height = 80;
		portDist = width / (Kmadr.PORTS_CNT+1);
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
		g.drawString("KMADR", 
				x + width/2 - fm.stringWidth("KMADR")/2+4,
				y + height/2 + 9);
		Port p;
		for(int i=1; i<=Kmadr.PORTS_CNT; i++){
			p = component.getPort(i-1);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x+portDist*i+5, y+5, x+portDist*i+5, y-8);
			g.setColor(c);
			g.drawString(""+(i-1), x+portDist*i+2, y+20);
			g.drawString(Integer.toHexString(Kmadr.OUTPUT[i-1]), x+portDist*i+2, y+height-1);
		}
		
		p = component.getPort(Kmadr.PORTS_CNT);//out
		g.setColor(ColorSignalConverter.convert(p));
		graphics2d.setStroke(new BasicStroke(4));
		g.drawLine(x + width/2+4, y+height+6, x + width/2+4, y +height+30);
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke(1));
		g.drawLine(x+width/2-4, y+height+25, x+width/2+8, y+height+9);
		g.drawString(((Integer)component.getPort(Kmadr.PORTS_CNT).getValue().getSize()).toString(),
				x+width/2+8, y+height+25);
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x+width/2-3-fm.stringWidth(Integer.toHexString(p.getValue().getUIntValue())),
				y+height+36);
		graphics2d.setStroke(new BasicStroke());
	}
}
