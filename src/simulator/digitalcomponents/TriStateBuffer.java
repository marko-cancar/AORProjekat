package simulator.digitalcomponents;

import java.util.ArrayList;

import simulator.*;
//0 enable, 1 input, 2 output
public class TriStateBuffer extends OneOutputGate {

	private static final long serialVersionUID = 1L;
	private int size;
	
	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();

		Port port = new PortSimple(Port.IN, new DigitalValue(
					DigitalValue.ZERO), "IN" + 0, "in" + 0, 0, this);
		ports.add(port);
		
		Value value = new MultipleDigitalValue(size);
		if(size == 1){
			value = new DigitalValue(DigitalValue.ZERO);
		}
		port = new PortSimple(Port.IN,
				value, "IN" + 1, "in"
						+ 1, 1, this);
		ports.add(port);
		
		port = new PortSimple(Port.INOUT,
				value, "OUT" + 2, "out"
						+ 2, 2, this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
		size = Integer.parseInt(data[2]);
		init();
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(), "" + this.id };
		return state;
	}

	public boolean calculate() {
		return false;
	}
	
	@Override
	public void execute() {
		Port enable = ports.get(0);
		if(enable.getValue().getBooleanValue()){
			Port in = ports.get(1);
			Port out = ports.get(2);
			out.getValue().load(in.getValue());
		}else{
			Port out = ports.get(2);
			out.getValue().setHighZ(true);
		}
	}
	
	@Override
	public void preCalculateTime() {
		Port out = ports.get(2);
		out.getValue().setHighZ(true);
	}

	public int getDataSize() {
		return size;
	}

}
