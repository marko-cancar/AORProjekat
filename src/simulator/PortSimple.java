package simulator;

import java.util.*;

public class PortSimple extends SignalSimle implements Port {

	private static final long serialVersionUID = 1L;

	protected int portType;
	protected Value defaultValue;
	protected String inName;
	protected int portNumber;
	protected LogicComponent component;

	public PortSimple() {

	}

	public PortSimple(int portType, Value defaultValue, String name,
			String outName, int portNumber, LogicComponent component) {
		super(outName);
		this.portType = portType;
		this.defaultValue = defaultValue;
		this.inName = name;
		this.portNumber = portNumber;
		this.component = component;
		this.value = defaultValue.copy();
	}

	@Override
	public void setType(int type) {
		this.portType = type;
	}

	@Override
	public int getType() {
		return portType;
	}

	@Override
	public void setDefaultValue(Value defaultValue) {
		if (this.defaultValue == null) {
			this.defaultValue = defaultValue;
		} else {
			this.defaultValue.load(defaultValue);
			setValue(defaultValue.copy());
		}
	}

	@Override
	public Value getDefaultValue() {
		return defaultValue;
	}

	@Override
	public Value getValue() {
		if (value == null) {
			value = defaultValue;
		}
		return value;
	}

	@Override
	public void setInName(String inName) {
		this.inName = inName;
	}

	@Override
	public String getInName() {
		return inName;
	}

	@Override
	public void setPortNumber(int number) {
		this.portNumber = number;
	}

	@Override
	public int getPortNumber() {
		return portNumber;
	}

	@Override
	public void setLogicComponent(LogicComponent component) {
		this.component = component;
	}

	@Override
	public LogicComponent getLogicComponent() {
		return component;
	}

	public boolean canConduct() {
		return portType == IN || portType == INOUT;
	}

	@Override
	public boolean canGenerate() {
		return portType == OUT || portType == INOUT;
	}

	@Override
	public void preCalculateTime() {
		// this.setValue(defaultValue.copy());
	}

	@Override
	public void execute() {

	}

	@Override
	public List<Event> preCalculateEvents() {
		List<Event> result = new LinkedList<Event>();
		for (Signal signal : connectedSignals) {
			Event event = new Event(0, defaultValue, signal, 0);
			result.add(event);
		}
		return result;
	}

	@Override
	public List<Event> execute(Event event) {
		List<Event> result = new LinkedList<Event>();
		if (!value.equals(event.getValue())) {
			value.load(event.getValue());
			if (canConduct()) {
				Event tempEvent = new Event(event.getTime(), value, component,
						portNumber);
				result.add(tempEvent);
			}
			for (Signal signal : connectedSignals) {
				Event tempEvent = new Event(event.getTime(), value, signal, 0);
				result.add(tempEvent);
			}
		}
		return result;
	}

}
