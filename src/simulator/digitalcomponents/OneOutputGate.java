package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

public class OneOutputGate extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;
	protected int numInputs;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();

		for (int i = 0; i < numInputs; i++) {
			Port port = new PortSimple(Port.IN, new DigitalValue(
					DigitalValue.ZERO), "IN" + i, "in" + i, i, this);
			ports.add(port);
		}
		Port port = new PortSimple(Port.OUT,
				new DigitalValue(DigitalValue.ZERO), "OUT" + numInputs, "out"
						+ numInputs, numInputs, this);
		ports.add(port);

	}

	public int getNumInputs() {
		return numInputs;
	}

	public void setNumInputs(int numInputs) {
		this.numInputs = numInputs;
	}

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
		numInputs = Integer.parseInt(data[2]);
		init();
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id, "" + numInputs };
		return state;
	}

	@Override
	public void preCalculateTime() {
		boolean newVal = calculate();
		Port out = ports.get(numInputs);
		out.getValue().setBooleanValue(newVal);
	}

	@Override
	public void execute() {
		boolean newVal = calculate();
		Port out = ports.get(numInputs);
		if(id==663) {
			System.out.println(out);
		}
		out.getValue().setBooleanValue(newVal);
	}

	protected boolean calculate() {
		return false;
	}

	@Override
	public List<Event> preCalculateEvents() {
		boolean newVal = calculate();

		List<Event> result = events;
		result.clear();
		Port out = ports.get(numInputs);
		if (out.getValue().getBooleanValue() != newVal) {
			Value value = new DigitalValue();
			value.setBooleanValue(newVal);
			Event tempEvent = new Event(0, value, out, 0);
			result.add(tempEvent);
		}
		return result;
	}

	@Override
	public List<Event> execute(Event event) {
		boolean newVal = calculate();

		List<Event> result = events;
		result.clear();
		Port out = ports.get(numInputs);
		if (out.getValue().getBooleanValue() != newVal) {
			Value value = new DigitalValue();
			value.setBooleanValue(newVal);
			Event tempEvent = new Event(event.getTime() + 1, value, out, 0);
			result.add(tempEvent);
		}
		return result;
	}

}
