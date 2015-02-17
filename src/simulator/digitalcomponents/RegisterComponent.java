package simulator.digitalcomponents;

import java.util.*;
import simulator.*;

//className componentName id dataSize
public class RegisterComponent extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	private int dataSize;
	
	private MultipleDigitalValue value;
	private boolean prevClk;
	private int initValue = 0;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i = 0; i < 4; i++){//inc, ld, clk, dec
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		
		//in
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(dataSize), 
				"IN" + 4, "in" + 4, 4, this);
		ports.add(port);
		
		//out
		port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(dataSize), "OUT" + 5, "out"
						+ 5, 5, this);
		ports.add(port);

	}

	@Override
	public void init(String[] data) {
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		dataSize = Integer.parseInt(data[3]);
		initValue = Integer.parseInt(data[4]);
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
		Port out = ports.get(5);
		prevClk = false;
		value = new MultipleDigitalValue(dataSize);
		value.setIntValue(initValue);
		out.getValue().load(value);
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		boolean clk = ports.get(0).getValue().getBooleanValue();
		boolean ld = ports.get(1).getValue().getBooleanValue();
		boolean inc = ports.get(2).getValue().getBooleanValue();
		boolean dec = ports.get(3).getValue().getBooleanValue();
		Port in = ports.get(4);
		Port out = ports.get(5);
		
		if(!prevClk && clk){
			if(ld && !inc && !dec){
				value.load(in.getValue());
			}else if(!ld && inc && !dec){
				value.incValue();
			}else if(!ld && !inc && dec){
				value.decValue();
			}
			prevClk = true;
		}
		if(!clk){
			prevClk = false;
		}
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
