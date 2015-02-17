package simulator.gui;

import java.awt.*;

import javax.swing.ImageIcon;

import simulator.*;

public class ComponentPresentation implements Drawable {

	int componentId;
	int x;
	int y;
	int width;
	int height;
	int index;
	String imageFile;
	transient Image imm;
	boolean clickable;

	LogicComponent component;

	public ComponentPresentation() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	@Override
	public void init(String[] data) {
		// data[0] class name, data[1] signal id, data[2] x,
		// data[3] y, data[4] width, data[5] height, data[6] image

		componentId = Integer.parseInt(data[1]);
		index = Integer.parseInt(data[2]);
		x = Integer.parseInt(data[3]);
		y = Integer.parseInt(data[4]);
		width = Integer.parseInt(data[5]);
		height = Integer.parseInt(data[6]);

		try {
			imageFile = data[7];
			imm = (new ImageIcon(imageFile)).getImage();
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

	@Override
	public void init(Object... parameters) {
		Netlist netlist = (Netlist) parameters[0];
		component = netlist.getComponent(componentId);
	}

	@Override
	public String[] state() {
		String[] result = new String[7];
		result[0] = this.getClass().getName();
		result[1] = "" + component.getId();
		result[2] = "" + x;
		result[3] = "" + y;
		result[4] = "" + width;
		result[5] = "" + height;
		result[6] = imageFile;
		return result;
	}

	static final int MARGIN_SIDE = 10;

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();

		g.drawRect(x, y, width - 2 * MARGIN_SIDE, height);

		int in = 1;
		int out = 1;
		for (Port p : component.getPorts()) {
			g.setColor(ColorSignalConverter.convert(p));
			if (p.getType() == Port.IN || p.getType() == Port.INOUT) {
				g.drawLine(x, y + MARGIN_SIDE * in, x + MARGIN_SIDE, y + MARGIN_SIDE * in);
				in++;
			} else {
				g.drawLine(x + width - 2 * MARGIN_SIDE, y + MARGIN_SIDE * out, x + width
						- MARGIN_SIDE, y + MARGIN_SIDE * out);
				out++;
			}
		}
		g.setColor(c);
	}

	@Override
	public void drawSelected(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.red);

		g.drawRect(x, y, width - 2 * MARGIN_SIDE, height);

		int in = 1;
		int out = 1;
		for (Port p : component.getPorts()) {
			if (p.getType() == Port.IN || p.getType() == Port.INOUT) {
				g.drawLine(x, y + MARGIN_SIDE * in, x + MARGIN_SIDE, y + MARGIN_SIDE * in);
				in++;
			} else {
				g.drawLine(x + width - 2 * MARGIN_SIDE, y + MARGIN_SIDE * out, x + width
						- MARGIN_SIDE, y + MARGIN_SIDE * out);
				out++;
			}
		}
		g.setColor(c);
	}

	@Override
	public void moveFor(int x, int y) {
		this.x += x;
		this.y += y;
	}

	@Override
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void resize(double percent) {
		width *= percent;
		height *= percent;
	}

	@Override
	public void resize(double percentX, double percentY) {
		width *= percentX;
		height *= percentY;
	}

	@Override
	public void rotate(double alfa) {
		// TODO Auto-generated method stub

	}

	@Override
	public void flip(int f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void zoom(float f) {
		// TODO Auto-generated method stub

	}

	@Override
	public Point getPosition() {
		Point result = new Point(x, y);
		return result;
	}

	@Override
	public void setPosition(Point p) {
		moveTo(p.x, p.y);
	}

	@Override
	public void setPosition(int x, int y) {
		moveTo(x, y);
	}

	@Override
	public Dimension getDimension() {
		Dimension result = new Dimension(width, height);
		return result;
	}

	@Override
	public void setDimension(Dimension d) {
		this.width = d.width;
		this.height = d.height;
	}

	@Override
	public void setDimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void calcDimension() {

	}

	@Override
	public String getText() {
		return "";
	}

	@Override
	public void setText(String text) {

	}

	@Override
	public String getImageURL() {
		return null;
	}

	@Override
	public void setImageURL(String url) {

	}

	@Override
	public float getLineSize() {
		return 1;
	}

	@Override
	public void setLineSize(float size) {

	}

	@Override
	public int getLineType() {
		return 0;
	}

	@Override
	public void setLineType(int type) {

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		return null;
	}

	@Override
	public boolean isOver(int x, int y) {
		return (this.x < x) && (x < this.x + width) && (this.y < y)
				&& (y < this.y + height);
	}

	@Override
	public int getPanelIndex() {
		return index;
	}

	@Override
	public boolean isClickable() {
		return clickable;
	}

	@Override
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
}
