package simulator.digitalcomponents;

import java.util.ArrayList;
import java.util.List;

import simulator.*;

public class MicroMem extends LogicComponentSimple {
	private static final long serialVersionUID = 1L;
	
	public static final int CAPACITY = 256;
	private int wordSize;
	private List<MultipleDigitalValue> words;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//ADDRESS IN
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(8), 
				"IN" + 0, "in" + 0, 0, this);
		ports.add(port);
		
		//DATA OUT
		port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(wordSize), "OUT" + 1, "out"
						+ 1, 1, this);
		ports.add(port);
	}

	@Override
	public void init(String[] data) {
		name = data[1];
		id = Integer.parseInt(data[2]);
		wordSize = Integer.parseInt(data[3]);
		init();
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id };
		return state;
	}

	@Override
	public void preCalculateTime() {
		if(words==null){
			words = new ArrayList<>();
		}
		for(int i=0; i<CAPACITY; i++){
			MultipleDigitalValue mdv = new MultipleDigitalValue(wordSize);
			// kurac for petlja, puni rucno!!!
			if(id==1006){
				mdv.setUIntValue(0x5eab3fc+i);
			}
			else if(id==1007){
				mdv.setUIntValue(i);
			}
			else if(id==1008){
				mdv.setUIntValue(i);
			}
			words.add(mdv);
		}
		execute();
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port addr = ports.get(0);
		Port dataOut = ports.get(1);
		dataOut.getValue().load(words.get(addr.getValue().getUIntValue()));
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
