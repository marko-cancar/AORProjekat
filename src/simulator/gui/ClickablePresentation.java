package simulator.gui;

import java.awt.Graphics;

public class ClickablePresentation extends ComponentPresentation{
	
	int x1, y1, retVal;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		return retVal;
	}
	
	@Override
	public void init(String[] data) {

		componentId = Integer.parseInt(data[1]);
		index = Integer.parseInt(data[2]);
		x = Integer.parseInt(data[3]);
		y = Integer.parseInt(data[4]);
		x1 = Integer.parseInt(data[5]);
		y1 = Integer.parseInt(data[6]);
		retVal = Integer.parseInt(data[7]);

		width = x1-x;
		height = y1-y;
		setClickable(true);
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, x1-x, y1-y);
	}
	
}
