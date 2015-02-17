package simulator.digitalcomponents;

import java.util.*;
import simulator.*;

public class Decomposer16to8 extends LogicComponentSimple {
	private static final long serialVersionUID = 1L;
	
	private static final int DATA_SIZE_IN = 16;
	private static final int DATA_SIZE_OUT = 8;
	
	@Override
	public void init(String[] data){
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		init();
	}
	
	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//input
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(DATA_SIZE_IN),
				"IN"+0, "in"+0, 0, this);
		ports.add(port);
		
		port = new PortSimple(Port.OUT, new MultipleDigitalValue(DATA_SIZE_OUT), 
				"OUT1", "out1", 1, this);
		ports.add(port);	
	}
	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id };
		return state;
	}
	
	@Override
	public void preCalculateTime() {
		Port in = ports.get(0);
		Port out = ports.get(1);
		List<Value> inValList = in.getValue().separateValues();
		List<DigitalValue> outValList = new LinkedList<>();
		for(int i=DATA_SIZE_OUT; i<DATA_SIZE_IN; i++){
			DigitalValue dv = (DigitalValue)inValList.get(i);
			outValList.add(dv);
		}
		MultipleDigitalValue mdv = new MultipleDigitalValue(outValList);
		out.setValue(mdv);
	}

	@Override
	public void execute() {
		Port in = ports.get(0);
		Port out = ports.get(1);
		List<Value> inValList = in.getValue().separateValues();
		List<DigitalValue> outValList = new LinkedList<>();
		for(int i=DATA_SIZE_OUT; i<DATA_SIZE_IN; i++){
			DigitalValue dv = (DigitalValue)inValList.get(i);
			outValList.add(dv);
		}
		MultipleDigitalValue mdv = new MultipleDigitalValue(outValList);
		out.setValue(mdv);
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
	

}
