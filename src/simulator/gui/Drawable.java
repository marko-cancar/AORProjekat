package simulator.gui;

import java.awt.*;

import simulator.Loadable;

public interface Drawable extends Loadable {
	
	public static final boolean hq = true;

	public void draw(Graphics g);

	public void drawSelected(Graphics g);
	
	public boolean isClickable();
	public void setClickable(boolean clickable);

	public void moveFor(int x, int y);

	public void moveTo(int x, int y);

	public void resize(double percent);

	public void resize(double percentX, double percentY);

	public void rotate(double alfa);

	public void flip(int f);

	public void zoom(float f);

	public Point getPosition();

	public void setPosition(Point p);

	public void setPosition(int x, int y);

	public Dimension getDimension();

	public void setDimension(Dimension d);

	public void setDimension(int width, int height);

	public void calcDimension();

	public String getText();

	public void setText(String text);

	public String getImageURL();

	public void setImageURL(String url);

	public float getLineSize();

	public void setLineSize(float size);

	public int getLineType();

	public void setLineType(int type);
	
	public boolean isOver(int x, int y);
	
	public int getPanelIndex();

	@SuppressWarnings("unchecked")
	public <T> Object execute(T... data);
}
