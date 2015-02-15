package simulator.digitalcomponents;

import java.util.*;
import simulator.*;

public class Kmopr extends LogicComponentSimple{
private static final long serialVersionUID = 1L;
	
	public static final int PORTS_CNT = 28;
	public static final int[] OUTPUT = {0x39, 0x3b, 0x3d, 0x3f, 0x5c, 0x5e, 0x43, 0x41,
		0x6d, 0x6a, 0x70, 0x73, 0x76, 0x79, 0x45, 0x45, 0x45, 0x45, 0x48, 0x48, 0x48, 0x48,
		0x7c, 0x81, 0x83, 0x85, 0x48, 0x52};
	
	@Override
	public void init(String[] data) {
		name = "KMOPR";
		id = Integer.parseInt(data[1]);
		init();
	}

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		//input signals
		for(int i = 0; i < PORTS_CNT; i++){
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		
		//output signal
		Port port = new PortSimple(Port.OUT, new MultipleDigitalValue(8), 
				"OUT" + PORTS_CNT, "out" + PORTS_CNT,
				PORTS_CNT, this);
		ports.add(port);
		
		// ulazni: 0 1 2 3 ... 27
		// izlazni: 28
	}

	@Override
	public void preCalculateTime() {
		int index = 0;
		for(int i=0; i<PORTS_CNT; i++){
			Port port = ports.get(i);
			if(port.getValue().getBooleanValue()) {
				index = i;
				break;
			}
		}
		Port out = ports.get(PORTS_CNT);
		out.getValue().setIntValue(OUTPUT[index]);
	}
	

	public int calculate(){
		return 0;
	}
	
	@Override
	public void execute() {
		preCalculateTime();
	}
	@Override
	public String[] state() {
		// TODO Auto-generated method stub
		return null;
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
