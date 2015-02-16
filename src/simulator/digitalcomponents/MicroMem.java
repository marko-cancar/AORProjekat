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
		MultipleDigitalValue mdv;
		if(id==1006){
			//0
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//1
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x1021112);
			words.add(mdv);
			//2
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//3
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//4
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0210000);
			words.add(mdv);
			//5
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x3000200);
			words.add(mdv);
		}
		else if(id==1007){
			//0
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x02);
			words.add(mdv);
			//1
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//2
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x03);
			words.add(mdv);
			//3
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x04);
			words.add(mdv);
			//4
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//5
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
		}
		else if(id==1008){
			//0
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//1
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//2
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x90);
			words.add(mdv);
			//3
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x02);
			words.add(mdv);
			//4
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
			//5
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setUIntValue(0x0);
			words.add(mdv);
		}
		
		for(int i=6; i<CAPACITY; i++){
			mdv = new MultipleDigitalValue(wordSize);
			mdv.setIntValue(0x3b);
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
