package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class Composer extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	private int inputSignalCount;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//input
		for(int i = 0; i < inputSignalCount; i++){
			Port port = new PortSimple(Port.IN, new MultipleDigitalValue(dataSize), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}		
		
		//output
		Port port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(dataSize*inputSignalCount), "OUT" + inputSignalCount, 
				"out" + inputSignalCount, inputSignalCount, this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {	
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		dataSize = Integer.parseInt(data[3]);
		setInputSignalCount(Integer.parseInt(data[4]));
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
		Port out = ports.get(inputSignalCount);
		MultipleDigitalValue mdv = new MultipleDigitalValue(inputSignalCount*dataSize);
		/*List<Value> digits = new LinkedList<>();
		for(int i=0; i<inputSignalCount; i++){
			Port port = ports.get(i);
			digits.addAll(port.getValue().separateValues());
		}
		for(int i=0; i<inputSignalCount*dataSize; i++){
			mdv.separateValues().get(i).setBooleanValue(digits.get(i).getBooleanValue());
		}*/
		out.getValue().load(mdv);
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port out = ports.get(inputSignalCount);
		MultipleDigitalValue mdv = new MultipleDigitalValue(inputSignalCount*dataSize);
		List<Value> digits = new LinkedList<>();
		for(int i=0; i<inputSignalCount; i++){
			Port port = ports.get(i);
			digits.addAll(port.getValue().separateValues());
		}
		for(int i=0; i<inputSignalCount*dataSize; i++){
			mdv.separateValues().get(i).setBooleanValue(digits.get(i).getBooleanValue());
		}
		out.getValue().load(mdv);
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


	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


}
