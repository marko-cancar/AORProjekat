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

public class MultiplexerComponentPresentation extends ComponentPresentation{
	
	static final int MARGIN_BOTTOM = 20;
	static final int MARGIN_SIDE = 30;

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		MultiplexerComponent mx = (MultiplexerComponent) component;
		mx.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
		width = 50;
		height = 100;
	}
	
	private static int MARGIN_INPUT = 40;
	private static int MARGIN_SELECT = 30;
	
	@Override
	public void draw(Graphics g) {
		Color c = g.getColor();
		width = (((MultiplexerComponent)component).getSelectionSignalCount()+1)*MARGIN_SELECT;
		height = (((MultiplexerComponent)component).getInputSignalCount()+1)*MARGIN_INPUT;
		
		Graphics2D graphics2d = (Graphics2D) g;
		graphics2d.setStroke(new BasicStroke());
		
		if(hq){
			graphics2d = (Graphics2D) g;
			graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
		}
		
		g.drawRect(x+MARGIN_SIDE/2, y, width, height - MARGIN_BOTTOM);
		
		FontMetrics fm = g.getFontMetrics();
		g.drawString(component.getName(), 
				x + width/2+MARGIN_SIDE/2-fm.stringWidth(component.getName())/2,
				y+height/2+5-MARGIN_BOTTOM/2);
		
		//selection signals
		for(int i=0; i<((MultiplexerComponent)component).getSelectionSignalCount(); i++){
			Port p = component.getPort(i);
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x+(i+1)*MARGIN_SELECT+MARGIN_SIDE/2, y + height, 
					x+(i+1)*MARGIN_SELECT+MARGIN_SIDE/2, y + height - MARGIN_BOTTOM);
			g.setColor(c);
			g.drawString(""+i, x+(i+1)*MARGIN_SELECT- fm.stringWidth(""+i)/2+MARGIN_SIDE/2,
					y + height - MARGIN_BOTTOM-3);
		}
	
		int dataSize = ((MultiplexerComponent)component).getDataSize();
		
		//input signals
		if(dataSize>1)graphics2d.setStroke(new BasicStroke(4));
		for(int i=0; i<((MultiplexerComponent)component).getInputSignalCount(); i++){
			Port p = component.getPort(i + ((MultiplexerComponent)component).getSelectionSignalCount());
			g.setColor(ColorSignalConverter.convert(p));
			g.drawLine(x, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2, 
					x + MARGIN_SIDE/2, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2);
			g.setColor(c);
			g.drawString(""+i, x+MARGIN_SIDE/2 + 5, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2+5);
			if(dataSize>1){
				g.setColor(c);
				graphics2d.setStroke(new BasicStroke(1));
				g.drawLine(x+MARGIN_SIDE/2-3, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2-6,
						x+MARGIN_SIDE/2-13,  y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2+6);
				g.drawString(((Integer)component.getPort(i+1).getValue().getSize()).toString(),
						x+MARGIN_SIDE/2-15, y + (i+1)*MARGIN_INPUT-MARGIN_BOTTOM/2-4);
				graphics2d.setStroke(new BasicStroke(4));
			}
		}
		
		
		//output signal
		Port p = component.getPort(((MultiplexerComponent)component).getSelectionSignalCount()+
				((MultiplexerComponent)component).getInputSignalCount());
		g.setColor(ColorSignalConverter.convert(p));
		g.drawLine(x + width + MARGIN_SIDE/2, y + (height-MARGIN_BOTTOM)/2,
				x + width + MARGIN_SIDE,  y + (height-MARGIN_BOTTOM)/2);
		if(dataSize>1){
			g.setColor(c);
			graphics2d.setStroke(new BasicStroke(1));
			g.drawLine(x + width + MARGIN_SIDE/2+13, y + (height-MARGIN_BOTTOM)/2-6,
					x + width + MARGIN_SIDE/2+3, y + (height-MARGIN_BOTTOM)/2+6);
			g.drawString(((Integer)component.getPort(((MultiplexerComponent)component).getAllSignalCount()).getValue().getSize()).toString(),
					x + width + MARGIN_SIDE/2+8, y+ (height-MARGIN_BOTTOM)/2-6);
			graphics2d.setStroke(new BasicStroke(4));
			g.drawString(Integer.toHexString(p.getValue().getUIntValue()),
				x + width + MARGIN_SIDE/2+6,y+ (height-MARGIN_BOTTOM)/2+16);
		}
		
		g.setColor(c);
		graphics2d.setStroke(new BasicStroke());
	}
	
}
