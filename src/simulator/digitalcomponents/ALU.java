package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id dataSize inputSignalCnt selectSignalCnt
public class ALU extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i=0; i<2; i++){//A, B
			Port port = new PortSimple(Port.IN, new MultipleDigitalValue(16),
					"IN" + i, "in" + i,	i, this);
			ports.add(port);
		}
		
		for(int i=2; i<8; i++){//not,xor,or,and,add,sub
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO),
					"IN" + i, "in" + i,	i, this);
			ports.add(port);
		}
		
		
		//ALUOUT
		Port port = new PortSimple(Port.OUT, new MultipleDigitalValue(16), 
				"OUT" + 8, "out" + 8, 8, this);
		ports.add(port);
		
		//C15
		port = new PortSimple(Port.OUT, new DigitalValue(DigitalValue.ZERO), 
				"OUT" + 9, "out" + 9, 9, this);
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
		Port out = ports.get(8);
		out.getValue().setIntValue(0);
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		MultipleDigitalValue a = (MultipleDigitalValue) ports.get(0).getValue();
		MultipleDigitalValue b = (MultipleDigitalValue) ports.get(1).getValue();
		boolean add = ports.get(2).getValue().getBooleanValue();
		boolean sub = ports.get(3).getValue().getBooleanValue();
		boolean and = ports.get(4).getValue().getBooleanValue();
		boolean or = ports.get(5).getValue().getBooleanValue();
		boolean xor = ports.get(6).getValue().getBooleanValue();
		boolean not = ports.get(7).getValue().getBooleanValue();
		Port out = ports.get(8);
		Port c15 = ports.get(9);
		MultipleDigitalValue result = new MultipleDigitalValue(16);
		if(add){
			result = Operation.add(a,b);
		}else if(sub){
			result = Operation.sub(a, b);
		}else if(and){
			result = Operation.and(a, b);
		}else if(or){
			result = Operation.or(a, b);
		}else if(xor){
			result = Operation.xor(a, b);
		}else if(not){
			result = Operation.not(b);
		}
		out.getValue().load(result);
		c15.getValue().setBooleanValue(result.separateValues().get(15).getBooleanValue());
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
