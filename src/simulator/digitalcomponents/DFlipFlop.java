package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize
public class DFlipFlop extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	
	private boolean prevClk;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i = 0; i < 2; i++){//D, clk
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		
		for(int i = 2; i < 4; i++){//q, !q
			Port port = new PortSimple(Port.OUT, new DigitalValue(DigitalValue.ZERO), 
					"OUT" + i, "out" + i, i, this);
			ports.add(port);
		}

	}

	@Override
	public void init(String[] data) {
		setName(data[1]);
		id = Integer.parseInt(data[2]);
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
		Port out = ports.get(2);
		Port outC = ports.get(3);
		prevClk = false;
		out.getValue().setBooleanValue(false);
		outC.getValue().setBooleanValue(true);
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		boolean clk = ports.get(0).getValue().getBooleanValue();
		boolean d = ports.get(1).getValue().getBooleanValue();
		Port q = ports.get(2);
		Port notQ = ports.get(3);
		
		boolean value = q.getValue().getBooleanValue();
		
		if(!prevClk && clk){
			value = d;
			prevClk = true;
		}
		if(!clk){
			prevClk = false;
		}
		q.getValue().setBooleanValue(value);
		notQ.getValue().setBooleanValue(!value);
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
