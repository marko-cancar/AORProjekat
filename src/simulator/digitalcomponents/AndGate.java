package simulator.digitalcomponents;

import simulator.*;

public class AndGate extends OneOutputGate {

	private static final long serialVersionUID = 1L;

	public boolean calculate() {
		boolean result = true;
		for (int i = 0; i < numInputs; i++) {
			Port port = ports.get(i);
			result = result && port.getValue().getBooleanValue();
			if(id == 663) System.out.println(port);
		}
		return result;
	}

}
