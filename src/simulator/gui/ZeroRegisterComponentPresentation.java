package simulator.gui;

import java.awt.Graphics;
import simulator.Port;
import simulator.digitalcomponents.*;

public class ZeroRegisterComponentPresentation extends ComponentPresentation{


	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		ZeroRegisterComponent zreg = (ZeroRegisterComponent) component;
		zreg.calculate();
		return this;
	}
	
	@Override
	public void init(String[] data) {
		super.init(data);
	}
	
	@Override
	public void draw(Graphics g) {		
		Port p = component.getPort(0);//out
		g.drawString(Integer.toHexString(p.getValue().getUIntValue()), x, y);	
	}
	
}
