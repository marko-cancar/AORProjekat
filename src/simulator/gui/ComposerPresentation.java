package simulator.gui;

import java.awt.Graphics;

public class ComposerPresentation extends ComponentPresentation{
	
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
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawLine(x, y, x, y+40);
		g.drawLine(x + 5, y, x + 5, y + 40);
	}
	
}
