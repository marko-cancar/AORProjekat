package simulator.digitalcomponents;

import java.util.*;

import simulator.*;

public class InGate extends LogicComponentSimple {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();

		Port port = new PortSimple(Port.OUT, new DigitalValue(
				(Integer) parameters[0]), "out0", "OUT0", 0, this);
		ports.add(port);

	}

	int period;

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public void init(String[] data) {
		setName(data[1]);
		id = Integer.parseInt(data[2]);
		init(Integer.parseInt(data[3]));
		period = Integer.parseInt(data[4]);
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id,
				"" + ports.get(0).getDefaultValue().getIntValue(), "" + period };
		return state;
	}

	@Override
	public void preCalculateTime() {
		time = period;
	}

	@Override
	public void execute() {
		if(period == 0) return;
		if(time==period){
			Port out = ports.get(0);
			out.getValue().setBooleanValue(true);
		}
		if(time==period+1){
			Port out = ports.get(0);
			out.getValue().setBooleanValue(false);
			time = 0;
		}
		time++;
	}

	@Override
	public List<Event> preCalculateEvents() {
		List<Event> result = new LinkedList<Event>();
		return result;
	}

	@Override
	public List<Event> execute(Event event) {
		List<Event> result = events;
		result.clear();
		Port out = ports.get(0);
		Value value = new DigitalValue();
		value.setBooleanValue(!out.getValue().getBooleanValue());
		Event tempEvent = new Event(event.getTime() + 1, value, out, 0);
		result.add(tempEvent);
		return result;
	}

	public void calculate() {
		Port out = ports.get(0);
		out.getValue().setBooleanValue(!out.getValue().getBooleanValue());
	}

	protected transient int time;

}
