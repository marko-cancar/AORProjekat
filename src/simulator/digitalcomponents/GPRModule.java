package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id
public class GPRModule extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	
	private List<MultipleDigitalValue> registers;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//wrGPR
		Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
				"IN" + 0, "in" + 0, 0, this);
		ports.add(port);
		
		//ADDRESS
		port = new PortSimple(Port.IN, new MultipleDigitalValue(6), 
				"IN" + 1, "in" + 1, 1, this);
		ports.add(port);
		
		//DATA IN
		port = new PortSimple(Port.IN, new MultipleDigitalValue(16), 
				"IN" + 2, "in" + 2, 2, this);
		ports.add(port);
		
		//DATA OUT
		port = new PortSimple(Port.OUT, new MultipleDigitalValue(16), 
				"OUT" + 3, "out" + 3, 3, this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
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
		if(registers==null){
			registers = new ArrayList<>();
		}
		for(int i=0; i<64; i++){
			MultipleDigitalValue mdv = new MultipleDigitalValue(16);
			mdv.setIntValue(i*10+1);
			registers.add(mdv);
		}
		Port out = ports.get(3);
		Port addr = ports.get(1);
		out.getValue().load(registers.get(addr.getValue().getUIntValue()));
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		boolean wr = ports.get(0).getValue().getBooleanValue();
		Port addr = ports.get(1);
		Port dataIn = ports.get(2);
		Port dataOut = ports.get(3);
		if(wr){
			registers.get(addr.getValue().getUIntValue()).load(dataIn.getValue());
		}
		dataOut.getValue().load(registers.get(addr.getValue().getUIntValue()));
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
