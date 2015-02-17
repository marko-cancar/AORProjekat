package simulator.gui;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class StringInRectanglePresentation extends ComponentPresentation{
	
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
		width = Integer.parseInt(data[5]);
		height = Integer.parseInt(data[6]);
		s = data[7];
	}
	
	@Override
	public void draw(Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		g.drawString(s, x + width/2-fm.stringWidth(s)/2,y+height/2+5);
	}
	
}
