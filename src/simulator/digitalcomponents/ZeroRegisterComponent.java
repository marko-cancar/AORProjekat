package simulator.digitalcomponents;

import java.util.*;
import simulator.*;

//className componentName id dataSize
public class ZeroRegisterComponent extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	
	private MultipleDigitalValue value;
	private int initValue = 0;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//out
		Port port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(dataSize), "OUT" + 0, "out"
						+ 0, 0, this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
		dataSize = Integer.parseInt(data[2]);
		initValue = Integer.parseInt(data[3]);
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
		Port out = ports.get(0);
		value = new MultipleDigitalValue(dataSize);
		value.setIntValue(initValue);
		out.getValue().load(value);
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port out = ports.get(0);
		value = new MultipleDigitalValue(dataSize);
		value.setIntValue(initValue);
		out.getValue().load(value);
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
