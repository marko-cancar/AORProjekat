package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class Decomposer extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//input
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(dataSize), 
				"IN" + 0, "in" + 0, 0, this);
		ports.add(port);
		
		for(int i = 1; i <= dataSize; i++){
			port = new PortSimple(Port.OUT, new DigitalValue(DigitalValue.ZERO), 
					"OUT" + i, "out" + i, i, this);
			ports.add(port);
		}		
	}

	@Override
	public void init(String[] data) {	
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		dataSize = Integer.parseInt(data[3]);
		init();
		
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id, "" + dataSize };
		return state;
	}

	@Override
	public void preCalculateTime() {
		Port in = ports.get(0);
		for(int i=1; i<=dataSize; i++){
			Port outI = ports.get(i);
			outI.getValue().setBooleanValue(in.getValue().separateValues().get(i-1).getBooleanValue());
		}
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port in = ports.get(0);
		for(int i=1; i<=dataSize; i++){
			Port outI = ports.get(i);
			outI.getValue().setBooleanValue(in.getValue().separateValues().get(i-1).getBooleanValue());
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

	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


}
