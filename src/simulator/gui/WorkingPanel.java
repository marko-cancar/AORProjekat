package simulator.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class WorkingPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JFrame parent;
	private String title;
	JLabel label;

	List<Drawable> drawables;

	public WorkingPanel(JFrame guiFrame, List<Drawable> drawables) {
		super();
		label = new JLabel("000,000");
		this.setLayout(null);
		this.add(label);
		this.parent = guiFrame;
		this.setSize(490, 390);
		this.addMouseListener(new MyMouseListener());
		this.addMouseMotionListener(new MyMouseMotionListener());

		this.drawables = drawables;
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
		Color c = g.getColor();
		g.setColor(Color.white);
		for (int i = 0; i < getWidth(); i = i + 100) {
			g.drawLine(i, 0, i, getHeight());
		}
		for (int i = 0; i < getHeight(); i = i + 100) {
			g.drawLine(0, i, getWidth(), i);
		}
		g.setColor(c);
		for (Drawable drawable : drawables) {
			drawable.draw(g);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	class MyMouseListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount() > 0) {
				boolean result = false;
				Object r = null;
				for (Drawable drawable : drawables) {
					if (drawable.isOver(event.getX(), event.getY())&& drawable.isClickable()) {
						r = drawable.execute(event);
						result = result || (r != null);
						break;
					}
				}
				if (result) {
					try {
						((GuiFrame) parent).addPanel((int) r);
					} catch (ClassCastException e) {
						
					}
					parent.repaint();
				}
			}
		}

	}
	
	class MyMouseMotionListener extends MouseMotionAdapter{
		
		@Override
		public void mouseMoved(MouseEvent e){
			boolean overComponent = false;
			label.setText(e.getX() + ","+e.getY());
			label.setBounds(e.getX()-50,e.getY()-50,100,50);
			for (Drawable drawable : drawables) {
				if (drawable.isOver(e.getX(), e.getY()) && drawable.isClickable()) {
					overComponent = true;
					break;
				}
			}
			if(overComponent){
				setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}else{
				setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		}
	}

}
