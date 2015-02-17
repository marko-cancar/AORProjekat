package simulator.digitalcomponents;
import java.util.*;

import simulator.*;

public class Coder extends LogicComponentSimple {
	private static final long serialVersionUID = 1L;
	private int inputSignalCount;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//inputSignals
		for(int i = 0; i < inputSignalCount; i++){
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		

		//outputSignals
		for(int i = inputSignalCount; i < Math.sqrt(inputSignalCount) + inputSignalCount; i++){
			Port port = new PortSimple(Port.OUT, new DigitalValue(DigitalValue.ZERO), 
					"OUT" + i, "out" + i, i, this);
			ports.add(port);
		}
	}

	@Override
	public void init(String[] data) {		
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		inputSignalCount = 4;
		//nail it.
		init();
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id, "" + inputSignalCount };
		return state;
	}

	@Override
	public void preCalculateTime() {
		int index = 0;
		for (int i = 0; i < inputSignalCount; i++) {
			Port port = ports.get(i);
			if (port.getValue().getBooleanValue()) {
				index = i;
				break;
			}
		}

		switch (index) {
		case 0:
			ports.get(4).getValue().setBooleanValue(false);
			ports.get(5).getValue().setBooleanValue(false);
			break;
		case 1:
			ports.get(4).getValue().setBooleanValue(true);
			ports.get(5).getValue().setBooleanValue(false);
			break;
		case 2:
			ports.get(4).getValue().setBooleanValue(false);
			ports.get(5).getValue().setBooleanValue(true);
			break;
		case 3:
			ports.get(4).getValue().setBooleanValue(true);
			ports.get(5).getValue().setBooleanValue(true);
			break;
		}
		// :D
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		int index = 0;
		for (int i = 0; i < inputSignalCount; i++) {
			Port port = ports.get(i);
			if (port.getValue().getBooleanValue()) {
				index = i;
				break;
			}
		}

		switch (index) {
		case 0:
			ports.get(4).getValue().setBooleanValue(false);
			ports.get(5).getValue().setBooleanValue(false);
			break;
		case 1:
			ports.get(4).getValue().setBooleanValue(true);
			ports.get(5).getValue().setBooleanValue(false);
			break;
		case 2:
			ports.get(4).getValue().setBooleanValue(false);
			ports.get(5).getValue().setBooleanValue(true);
			break;
		case 3:
			ports.get(4).getValue().setBooleanValue(true);
			ports.get(5).getValue().setBooleanValue(true);
			break;
		}
		// :D
	}

	@Override
	public List<Event> preCalculateEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> execute(Event event) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getInputSignalCount() {
		return inputSignalCount;
	}

	public void setInputSignalCount(int inputSignalCount) {
		this.inputSignalCount = inputSignalCount;
	}

}

