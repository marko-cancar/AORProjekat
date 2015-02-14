package simulator.gui;

import java.awt.*;

import simulator.ColorSignalConverter;
import simulator.Netlist;
import simulator.Signal;

public class SignalPresentation implements Drawable {

	Signal signal;
	int signalId;
	int index;
	Polygon line;
	String name;
	float lineSize = 1;
	boolean showValue = false;
	int xVal;
	int yVal;

	@Override
	public void init(String[] data) {
		// data[0] class name, data[1] signal id, data[2]... x, y coordinates

		signalId = Integer.parseInt(data[1]);
		index = Integer.parseInt(data[2]);
		line = new Polygon();
		try {
			int index = 0;
			for (int i = 3; i < data.length;) {
				if(data[i].equals("ShowValueAt")){
					showValue = true;
					index = i;
					break;
				}
				line.addPoint(Integer.parseInt(data[i++]),
						Integer.parseInt(data[i++]));
			}
			if(showValue){
				xVal = Integer.parseInt(data[++index]);
				yVal = Integer.parseInt(data[++index]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void init(Object... parameters) {
		Netlist netlist = (Netlist) parameters[0];
		signal = netlist.getSignal(signalId);
	}

	@Override
	public String[] state() {
		String[] result = new String[2 + line.npoints];
		result[0] = this.getClass().getName();
		result[1] = "" + signal.getId();
		for (int i = 0; i < line.npoints;) {
			result[2 + i * 2] = "" + line.xpoints[i];
			result[2 + i * 2 + 1] = "" + line.ypoints[i];
		}
		return result;
	}

	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();

		Graphics2D graphics2d = (Graphics2D) g;

		graphics2d.setStroke(new BasicStroke(lineSize));
		
		if(signal.getValue().getSize()>1){
			graphics2d.setStroke(new BasicStroke(4));
		}

		g.setColor(ColorSignalConverter.convert(signal));

		g.drawPolyline(line.xpoints, line.ypoints, line.npoints);

		g.setColor(c);
		
		if(showValue){
			if(signal.getValue().isHighZ()){
				g.drawString("HIGH_Z", xVal, yVal);
			}else{
				g.drawString(Integer.toHexString(signal.getValue().getUIntValue()), xVal, yVal);
			}
		}
		graphics2d.setStroke(new BasicStroke(lineSize));
	}

	@Override
	public void drawSelected(Graphics g) {
		Color c = g.getColor();

		Graphics2D graphics2d = (Graphics2D) g;

		graphics2d.setStroke(new BasicStroke(lineSize));

		g.setColor(Color.red);

		g.drawPolyline(line.xpoints, line.ypoints, line.npoints);

		g.setColor(c);
	}

	@Override
	public void moveFor(int x, int y) {
		for (int i = 0; i < line.npoints;) {
			line.xpoints[i] = line.xpoints[i] + x;
			line.ypoints[i] = line.ypoints[i] + y;
		}
	}

	@Override
	public void moveTo(int x, int y) {
		moveFor(line.xpoints[0] - x, line.ypoints[0] - y);

	}

	@Override
	public void resize(double percent) {
		for (int i = 0; i < line.npoints;) {
			line.xpoints[i] = (int) (line.xpoints[i] * percent);
			line.ypoints[i] = (int) (line.ypoints[i] * percent);
		}
	}

	@Override
	public void resize(double percentX, double percentY) {
		for (int i = 0; i < line.npoints;) {
			line.xpoints[i] = (int) (line.xpoints[i] * percentX);
			line.ypoints[i] = (int) (line.ypoints[i] * percentY);
		}
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
		Point result = new Point(line.xpoints[0], line.ypoints[0]);
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
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = 0;
		int maxY = 0;

		for (int i = 0; i < line.npoints;) {
			if (line.xpoints[i] < minX) {
				minX = line.xpoints[i];
			}
			if (line.xpoints[i] > maxX) {
				maxX = line.xpoints[i];
			}
			if (line.ypoints[i] < minY) {
				minY = line.ypoints[i];
			}
			if (line.ypoints[i] > maxY) {
				maxY = line.ypoints[i];
			}
		}
		Dimension result = new Dimension(Math.abs(maxX - minX), Math.abs(maxY
				- minY));
		return result;
	}

	@Override
	public void setDimension(Dimension d) {
		Dimension currentResult = getDimension();
		resize(d.getWidth() / currentResult.getWidth(), d.getHeight()
				/ currentResult.getHeight());
	}

	@Override
	public void setDimension(int width, int height) {
		Dimension currentResult = getDimension();
		resize(width / currentResult.getWidth(),
				height / currentResult.getHeight());
	}

	@Override
	public void calcDimension() {

	}

	@Override
	public String getText() {
		return name;
	}

	@Override
	public void setText(String text) {
		this.name = text;

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
		return lineSize;
	}

	@Override
	public void setLineSize(float size) {
		lineSize = size;
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
		return line.contains(x, y);
	}

	@Override
	public int getPanelIndex() {
		return index;
	}

	@Override
	public boolean isClickable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setClickable(boolean clickable) {
		// TODO Auto-generated method stub
	}

}
