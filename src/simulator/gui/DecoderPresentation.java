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

public class DecoderPresentation extends ComponentPresentation{
	
	static final int MARGIN_BOTTOM = 20;
	static final int MARGIN_SIDE = 20;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		Decoder dc = (Decoder) component;
		dc.calculate();
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
		int outCnt = (int) Math.pow(2, ((Decoder)component).getInputSignalCount());
		int inputCnt = ((Decoder)component).getInputSignalCount();
		height = (outCnt+1)*MARGIN_INPUT;
		
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
		Port p = component.getPort(0);
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x+ width/2, y + height, 
				x+width/2, y + height - MARGIN_BOTTOM);
		g.setColor(c);
		g.drawString("E", x+width/2- fm.stringWidth("E"),y + height - MARGIN_BOTTOM-3);
	
		//input signals
		for(int i=1; i<inputCnt+1; i++){
			p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x, y + (i)*MARGIN_INPUT-MARGIN_BOTTOM/2, 
					x + MARGIN_SIDE/2, y + (i)*MARGIN_INPUT-MARGIN_BOTTOM/2);
			g.setColor(c);
			g.drawString(""+(i-1), x+MARGIN_SIDE/2 + 5, y + (i)*MARGIN_INPUT-MARGIN_BOTTOM/2+5);
		}
		
		//output signals
		for(int i=inputCnt+1; i<inputCnt+1+outCnt; i++){
			p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x+width-MARGIN_SIDE/2, y + (i-inputCnt)*MARGIN_INPUT-MARGIN_BOTTOM/2, 
					x + width, y + (i-inputCnt)*MARGIN_INPUT-MARGIN_BOTTOM/2);
			g.setColor(c);
			g.drawString(""+(i-inputCnt-1), x+ width - MARGIN_SIDE - fm.stringWidth(" "+(i-inputCnt)) + 5, y + (i-inputCnt)*MARGIN_INPUT-MARGIN_BOTTOM/2+5);
		}
		
	}
}
