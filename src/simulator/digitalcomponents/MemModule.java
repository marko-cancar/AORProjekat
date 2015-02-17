package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

//className componentName id
public class MemModule extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	
	public static List<MultipleDigitalValue> bytes;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		for(int i = 0; i < 2; i++){//rd,wr
			Port port = new PortSimple(Port.IN, new DigitalValue(DigitalValue.ZERO), 
					"IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		
		//ADDRESS IN
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(16), 
				"IN" + 2, "in" + 2, 2, this);
		ports.add(port);
		
		//DATA IN
		port = new PortSimple(Port.IN, new MultipleDigitalValue(8), 
				"IN" + 3, "in" + 3, 3, this);
		ports.add(port);
		
		//DATA OUT
		port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(8), "OUT" + 4, "out"
						+ 4, 4, this);
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
		if(bytes==null){
			bytes = new ArrayList<>();
		}
		MultipleDigitalValue mdv = new MultipleDigitalValue(8);

		// load R0
		mdv.setUIntValue(192);
		bytes.add(mdv);
		mdv = new MultipleDigitalValue(8);
		mdv.setUIntValue(0);
		bytes.add(mdv);

		// add R1
		mdv = new MultipleDigitalValue(8);
		mdv.setUIntValue(194);
		bytes.add(mdv);
		mdv = new MultipleDigitalValue(8);
		mdv.setUIntValue(1);
		bytes.add(mdv);

		// store R2
		mdv = new MultipleDigitalValue(8);
		mdv.setUIntValue(193);
		bytes.add(mdv);
		mdv = new MultipleDigitalValue(8);
		mdv.setUIntValue(2);
		bytes.add(mdv);	

		for(int i=6; i<65536; i++){
			mdv = new MultipleDigitalValue(8);
			mdv.setUIntValue(0);
			bytes.add(mdv);
		}
		
		
		Port out = ports.get(4);
		Port addr = ports.get(2);
		out.getValue().load(bytes.get(addr.getValue().getUIntValue()));
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		boolean rd = ports.get(0).getValue().getBooleanValue();
		boolean wr = ports.get(1).getValue().getBooleanValue();
		Port addr = ports.get(2);
		Port dataIn = ports.get(3);
		Port dataOut = ports.get(4);
		
		if(rd && !wr){
			dataOut.getValue().load(bytes.get(addr.getValue().getUIntValue()));
		}else if(wr && !rd){
			bytes.get(addr.getValue().getUIntValue()).load(dataIn.getValue());
		}
		
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
