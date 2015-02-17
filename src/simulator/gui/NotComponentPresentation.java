package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class NotComponentPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		NotGate notGate = (NotGate) component;
		notGate.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 60;
		height = 40;
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
		
		g.setColor(Color.black);
		g.drawLine(x + MARGIN_SIDE/2, y, x + MARGIN_SIDE/2, y + height);
		g.drawLine(x+MARGIN_SIDE/2, y, x+width-MARGIN_SIDE/2-6, y + height/2);
		g.drawLine(x+MARGIN_SIDE/2, y+height, x+width-MARGIN_SIDE/2-6, y+height/2);
		g.drawOval(x+width-MARGIN_SIDE/2-6, y+height/2-3, 6, 6);
		g.setColor(c);


		Port p = component.getPort(0);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x, y + height/2,
				x + MARGIN_SIDE/2, y+height/2);
		g.setColor(Color.black);
		
		
		p = component.getPort(1);//out
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+width-MARGIN_SIDE/2, y+height/2,x + width, y+height/2);
		
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke());
	}
	
}
