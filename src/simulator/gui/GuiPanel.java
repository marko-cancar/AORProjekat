package simulator.gui;

import java.awt.Graphics;
import java.util.*;

import javax.swing.*;

public class GuiPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	List<Drawable> drawables;

	public GuiPanel(JFrame guiFrame, List<Drawable> drawables) {
		super();
		this.drawables = drawables;
		this.setSize(490, 390);
		this.setVisible(true);
	}

	public List<Drawable> getDrawables() {
		return drawables;
	}

	public void setDrawables(List<Drawable> drawables) {
		this.drawables = drawables;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (Drawable drawable : drawables) {
			drawable.draw(g);
		}
	}

}
