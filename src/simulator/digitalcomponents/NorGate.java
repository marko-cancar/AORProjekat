package simulator.digitalcomponents;

import simulator.*;

public class NorGate extends OneOutputGate {

	private static final long serialVersionUID = 1L;

	protected boolean calculate() {
		boolean result = false;
		for (int i = 0; i < numInputs; i++) {
			Port port = ports.get(i);
			result = result || port.getValue().getBooleanValue();
		}
		return !result;
	}

}
