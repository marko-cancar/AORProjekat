package simulator.gui;

import simulator.digitalcomponents.*;

public class OutComponentPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		OutGate outGate = (OutGate) component;
		outGate.calculate();
		return this;
	}
	
}
