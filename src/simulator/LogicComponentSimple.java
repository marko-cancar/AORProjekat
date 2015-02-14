package simulator;

import java.util.*;

public abstract class LogicComponentSimple implements LogicComponent {
	private static final long serialVersionUID = 1L;
	protected int id;
	protected String name;
	protected List<Port> ports;
	protected List<Signal> signals;
	
	protected transient Netlist netlist;

	protected transient List<Event> events;

	public LogicComponentSimple() {
		events = new LinkedList<Event>();
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public List<Port> getPorts() {
		return ports;
	}

	@Override
	public void setPorts(List<Port> ports) {
		this.ports = new LinkedList<Port>(ports);
	}

	@Override
	public Port getPort(int index) {
		return ports.get(index);
	}

	@Override
	public void setPort(int index, Port port) {
		ports.set(index, port);
	}

	@Override
	public Port findPort(String name) {
		for (Port port : ports) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
	}

	@Override
	public List<Port> findPorts(int portType) {
		List<Port> result = new LinkedList<Port>();
		for (Port port : ports) {
			if (port.getType() == portType) {
				result.add(port);
			}
		}
		return result;
	}

	@Override
	public List<Port> findUnconnectedPorts(int type) {
		List<Port> result = new LinkedList<Port>();
		for (Port port : ports) {
			if ((port.getType() == type) && (!port.isConnected())) {
				result.add(port);
			}
		}
		return result;
	}

	@Override
	public List<Signal> getSignals() {
		return signals;
	}

	@Override
	public void setSignals(List<Signal> signals) {
		this.signals = new LinkedList<Signal>(signals);
	}

	@Override
	public Signal findSignal(String inName) {
		for (Signal signal : signals) {
			if (signal.getName().equals(name)) {
				return signal;
			}
		}
		return null;
	}

	@Override
	public List<Signal> findUnconnectedSignal() {
		List<Signal> result = new LinkedList<Signal>();
		for (Signal signal : signals) {
			if (!signal.isConnected()) {
				result.add(signal);
			}
		}
		return result;
	}

	@Override
	public List<LogicComponent> getSubComponents() {
		return netlist.getComponents();
	}

	@Override
	public void setSubComponents(List<LogicComponent> subComponents) {

	}

	@Override
	public int getComponentCount() {
		if (netlist == null) {
			return 0;
		} else {
			return netlist.getComponents().size();
		}
	}

	@Override
	public Netlist getNetlist() {
		return netlist;
	}

	@Override
	public void setNetlist(Netlist netlist) {
		this.netlist = netlist;
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

	public String toString() {
		String result = "";
		result += this.getClass().getName() + " " + name + " " + id;
		return result;
	}

}
