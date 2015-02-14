package simulator.gui;

import java.awt.Graphics;

public class StringPresentation extends ComponentPresentation{
	
	String s;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		return 10;
	}
	
	@Override
	public void init(String[] data) {
		componentId = Integer.parseInt(data[1]);
		index = Integer.parseInt(data[2]);
		x = Integer.parseInt(data[3]);
		y = Integer.parseInt(data[4]);
		s = data[5];
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawString(s, x,y);
	}
	
}
