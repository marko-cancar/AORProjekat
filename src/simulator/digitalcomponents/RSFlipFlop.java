package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize
public class RSFlipFlop extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	
	private boolean prevClk;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i = 0; i < 3; i++){//R, S, clk
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		
		for(int i = 3; i < 5; i++){//q, !q
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
		Port out = ports.get(3);
		Port outC = ports.get(4);
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
		boolean set = ports.get(1).getValue().getBooleanValue();
		boolean reset = ports.get(2).getValue().getBooleanValue();
		Port q = ports.get(3);
		Port notQ = ports.get(4);
		
		boolean value = q.getValue().getBooleanValue();
		
		if(!prevClk && clk){
			if(set && !reset ){
				value = true;
			}else if(!set && reset){
				value = false;
			}else if(set && reset){
				System.out.println("SET I RESET = 1");
			}
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
