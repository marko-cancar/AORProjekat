package simulator.gui;

import java.awt.Graphics;

public class RectanglePresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		return null;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
	}
	
}
