package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize
public class Comparator extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		Value value = new DigitalValue(DigitalValue.ZERO);
		//2 inputs
		for(int i = 0; i < 2; i++){
			if(dataSize>1){
				value = new MultipleDigitalValue(dataSize);
			}
			Port port = new PortSimple(Port.IN, value, 
					"IN" + i, "in" + i,i, this);
			ports.add(port);
		}
		
		
		//output (0,1)
		Port port = new PortSimple(Port.OUT,
				new DigitalValue(DigitalValue.ZERO), "OUT" + 2, 
				"out" + 2, 2, this);
		ports.add(port);

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
		Port out = ports.get(2);
		int val = calculate();
		out.getValue().setIntValue(val);
	}

	public int calculate() {
		Port a = ports.get(0);
		Port b = ports.get(1);
		if(a.getValue().getUIntValue()==b.getValue().getUIntValue()){
			return 1;
		}
		return 0;
	}
	
	@Override
	public void execute() {
		Port out = ports.get(2);
		out.getValue().setIntValue(calculate());
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
