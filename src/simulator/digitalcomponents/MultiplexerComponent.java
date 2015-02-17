package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class MultiplexerComponent extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	private int inputSignalCount;
	private int selectionSignalCount;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//selectionSignals
		for(int i = 0; i < getSelectionSignalCount(); i++){
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		

		Value value = new DigitalValue(DigitalValue.ZERO);
		//inputSignals
		for(int i = 0; i < getInputSignalCount(); i++){
			if(dataSize>1){
				value = new MultipleDigitalValue(dataSize);
			}
			Port port = new PortSimple(Port.IN, value, 
					"IN" + i+getSelectionSignalCount(), "in" + i+getSelectionSignalCount(),
					i+getSelectionSignalCount(), this);
			ports.add(port);
		}
		
		
		//output
		Port port = new PortSimple(Port.OUT,
				value, "OUT" + getSelectionSignalCount() + getInputSignalCount(), 
				"out" + getSelectionSignalCount() + getInputSignalCount(),
				getSelectionSignalCount() + getInputSignalCount(), this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {		
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		dataSize = Integer.parseInt(data[3]);
		setInputSignalCount(Integer.parseInt(data[4]));
		setSelectionSignalCount(Integer.parseInt(data[5]));
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
		Port out = ports.get(getSelectionSignalCount() + getInputSignalCount());
		int val = calculate();
		out.getValue().setIntValue(val);
	}

	public int calculate() {
		int index = 0;
		for(int i=0; i<getSelectionSignalCount(); i++){
			index += ports.get(i).getValue().getIntValue()*Math.pow(2.0, i);
		}
		return index;
	}
	
	@Override
	public void execute() {
		Port out = ports.get(getSelectionSignalCount() + getInputSignalCount());
		out.getValue().load(ports.get(selectionSignalCount + calculate()).getValue());
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

	public int getSelectionSignalCount() {
		return selectionSignalCount;
	}

	public void setSelectionSignalCount(int selectionSignalCount) {
		this.selectionSignalCount = selectionSignalCount;
	}

	public int getAllSignalCount(){
		return this.inputSignalCount + this.selectionSignalCount;
	}
	
	public int getDataSize() {
		return dataSize;
	}

	public void setDataSize(int dataSize) {
		this.dataSize = dataSize;
	}


}
