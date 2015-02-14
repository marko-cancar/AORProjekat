package simulator;

import java.util.*;

public class SignalSimle implements Signal {

	private static final long serialVersionUID = 1L;
	protected int id;
	protected String name;

	protected transient Value value;
	protected transient List<Signal> connectedSignals;

	protected transient List<Event> events;

	public SignalSimle() {
		this("");
	}

	public SignalSimle(String name) {
		this.name = name;
		events = new LinkedList<Event>();
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name + "Signal" + id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setValue(Value value) {
		if (this.value == null) {
			this.value = value;
		} else {
			this.value.load(value);
		}
	}

	@Override
	public Value getValue() {
		return value;
	}

	@Override
	public void connect(Signal signal) {
		if (connectedSignals == null) {
			connectedSignals = new LinkedList<Signal>();
		}
		if (!connectedSignals.contains(signal)) {
			connectedSignals.add(signal);
		}
		if (signal.canGenerate()) {
			value = signal.getValue().copy();
		}
	}

	@Override
	public void dissconnect(Signal signal) {
		connectedSignals.remove(signal);
	}

	@Override
	public boolean isConnected() {
		return connectedSignals.size() > 0;
	}

	@Override
	public boolean canConduct() {
		return true;
	}

	@Override
	public boolean canGenerate() {
		return false;
	}

	@Override
	public void init(String[] data) {
		id = Integer.parseInt(data[1]);
		setName(data[2]);
	}

	@Override
	public void init(Object... parameters) {

	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(), "" + this.id };
		return state;
	}

	@Override
	public void preCalculateTime() {
		calculate();
		execute();
	}

	@Override
	public void execute() {
		calculate();
		for (Signal signal : connectedSignals) {
			if (signal.canConduct()) {
				if(value.isHighZ()) signal.getValue().setHighZ(true);
				else signal.getValue().load(value);
			}
		}

	}

	protected void calculate() {
		for (Signal signal : connectedSignals) {
			if (signal.canGenerate()) {
				if(signal.getValue().isHighZ()) continue;
				value.load(signal.getValue());
				return;
			}
		}
		
		value.setHighZ(true);
	}

	@Override
	public List<Event> preCalculateEvents() {
		List<Event> result = events;
		result.clear();
		return result;
	}

	@Override
	public List<Event> execute(Event event) {
		List<Event> result = events;
		result.clear();
		if (!value.equals(event.getValue())) {
			value.load(event.getValue());
			for (Signal signal : connectedSignals) {
				Event tempEvent = new Event(event.getTime(), value, signal, 0);
				result.add(tempEvent);
			}
		}
		return result;
	}

	public String toString() {
		String result = "";
		result += this.getClass().getSimpleName() + " " + name + " " + id
				+ " " + value;
		return result;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDescription(String description) {
		// TODO Auto-generated method stub

	}

}
