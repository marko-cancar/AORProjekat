package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class Decoder extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int inputSignalCount;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//inputSignals and ENABLE
		for(int i = 0; i < inputSignalCount+1; i++){
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		

		//outputSignals
		for(int i = 0; i < Math.pow(2,inputSignalCount); i++){
			Port port = new PortSimple(Port.OUT, new DigitalValue(DigitalValue.ZERO), 
					"OUT" + i+inputSignalCount+1, "out" + i+inputSignalCount+1,
					i+inputSignalCount+1, this);
			ports.add(port);
		}
	}

	@Override
	public void init(String[] data) {		
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		inputSignalCount = Integer.parseInt(data[3]);
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
		Port enable = ports.get(0);
		for(int i=1; i<inputSignalCount+1; i++){
			Port port = ports.get(i);
			index += port.getValue().getIntValue()*Math.pow(2, i-1);
		}
		for(int i=inputSignalCount+1; i<inputSignalCount+1+Math.pow(2, inputSignalCount);i++){
			Port out = ports.get(i);
			out.getValue().setBooleanValue(false);
		}
		if(enable.getValue().getBooleanValue()){
			Port out = ports.get(inputSignalCount+index);
			out.getValue().setBooleanValue(true);
		}
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		int index = 0;
		Port enable = ports.get(0);
		for(int i=1; i<inputSignalCount+1; i++){
			Port port = ports.get(i);
			index += port.getValue().getIntValue()*Math.pow(2, i-1);
		}
		for(int i=inputSignalCount+1; i<inputSignalCount+1+Math.pow(2, inputSignalCount);i++){
			Port out = ports.get(i);
			out.getValue().setBooleanValue(false);
		}
		if(enable.getValue().getBooleanValue()){
			Port out = ports.get(inputSignalCount+1+index);
			out.getValue().setBooleanValue(true);
		}
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
