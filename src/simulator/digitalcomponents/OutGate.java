package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

public class OutGate extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
		init(Integer.parseInt(data[2]));
	}

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();

		Port port = new PortSimple(Port.IN, new DigitalValue(
				(Integer) parameters[0]), "in0", "IN0", 0, this);
		ports.add(port);

	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id, "" + ports.get(0).getDefaultValue().getIntValue() };
		return state;
	}

	@Override
	public void preCalculateTime() {
		time = 0;
	}

	@Override
	public void execute() {
		calculate();
	}

	@Override
	public List<Event> preCalculateEvents() {
		List<Event> result = new LinkedList<Event>();
		return result;
	}

	@Override
	public List<Event> execute(Event event) {
		List<Event> result = events;
		return result;
	}

	private transient int time;

	public void calculate() {
		Port out = ports.get(0);
		System.out.println("" + time + "\t" + out.getValue().getBooleanValue());
		time++;
	}

}
