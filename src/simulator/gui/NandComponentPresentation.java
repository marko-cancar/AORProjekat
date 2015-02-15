package simulator.gui;

import simulator.digitalcomponents.*;

public class NandComponentPresentation extends ComponentPresentation{

	@SuppressWarnings("unchecked")
	@Override
	public <T> Object execute(T... data) {
		NandGate nandGate = (NandGate) component;
		nandGate.calculate();
		return this;
	}
	
}
