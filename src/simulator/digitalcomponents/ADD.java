package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class ADD extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i=0; i<2; i++){//A, B
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(16),
				"IN" + i, "in" + i,	i, this);
		ports.add(port);
		}
		
		
		//output
		Port port = new PortSimple(Port.OUT, new MultipleDigitalValue(16), 
				"OUT" + 2, "out" + 2, 2, this);
		ports.add(port);
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
		Port A = ports.get(0);
		Port B = ports.get(1);
		Port out = ports.get(2);
		out.getValue().setIntValue(A.getValue().getIntValue()+B.getValue().getIntValue());
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port A = ports.get(0);
		Port B = ports.get(1);
		Port out = ports.get(2);
		int valA = A.getValue().getIntValue();
		int valB = B.getValue().getIntValue();
		int rez = valA+valB;
		out.getValue().setIntValue(rez);
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
