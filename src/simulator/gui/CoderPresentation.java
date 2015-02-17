package simulator.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import simulator.ColorSignalConverter;
import simulator.Port;
import simulator.digitalcomponents.*;

public class CoderPresentation extends ComponentPresentation {
	
	static final int MARGIN_BOTTOM = 20;
	static final int MARGIN_SIDE = 20;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		Coder cd = (Coder) component;
		cd.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 120;
		height = 100;
	}
	
	private static int MARGIN_INPUT = 30;
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		int outCnt = 2;
		int inputCnt = 4;
		height = (inputCnt+1)*MARGIN_INPUT;
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke());
		
		if(hq){
			graphics2d = (Graphics2D) g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g.drawRect(x+MARGIN_SIDE/2, y, width-MARGIN_SIDE, height - MARGIN_BOTTOM);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2-fm.stringWidth(component.getName())/2,
				y+height/2+5-MARGIN_BOTTOM/2);
		
		//enable signals
		Port p;
		
		//input signals
		for(int i=0; i<inputCnt; i++){
			p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2, 
					x + MARGIN_SIDE/2, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2);
			g.setColor(c);
			g.drawString(""+(i), x+MARGIN_SIDE/2 + 5, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2+5);
		}
		
		//output signals
		for(int i=inputCnt; i<inputCnt+outCnt; i++){
			p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x+width-MARGIN_SIDE/2, y + (i-inputCnt+1)*MARGIN_INPUT-MARGIN_BOTTOM/2, 
					x + width, y + (i-inputCnt+1)*MARGIN_INPUT-MARGIN_BOTTOM/2);
			g.setColor(c);
			g.drawString(""+(i-inputCnt), x+ width - MARGIN_SIDE - fm.stringWidth(" "+(i-inputCnt)) + 5, y + (i-inputCnt+1)*MARGIN_INPUT-MARGIN_BOTTOM/2+5);
		}
		
	}
}

