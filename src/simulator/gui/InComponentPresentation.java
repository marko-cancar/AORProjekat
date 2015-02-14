package simulator.gui;

import java.awt.Color;
import java.awt.Graphics;

import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class InComponentPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		InGate ingate = (InGate) component;
		ingate.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 80;
		height = 40;
		setClickable(true);
	}
	
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.drawRect(x, y, width - MARGIN_SIDE, height);
		g.drawString(component.getName(), x + width/10, y+height/2+5);
		Port p = component.getPort(0);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width - MARGIN_SIDE, y + height/2, x + width, y + height/2);
		g.setColor(c);
	}
	
}
