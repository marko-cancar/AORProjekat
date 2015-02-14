package simulator;

import java.util.*;

public class NetlistSimple implements Netlist {
	Map<Integer, LogicComponent> components;
	Map<Integer, Signal> signals;
	List<Connector> connectors;

	@Override
	public LogicComponent getComponent(int id) {
		return components.get(id);
	}

	@Override
	public List<LogicComponent> getComponents() {
		return new LinkedList<LogicComponent>(components.values());
	}

	@Override
	public void setComponents(List<LogicComponent> components) {
		if (this.components == null) {
			this.components = new HashMap<Integer, LogicComponent>();
		} else {
			this.components.clear();
		}
		for (LogicComponent component : components) {
			this.components.put(component.getId(), component);
			//System.out.println(component);
		}
	}

	@Override
	public Signal getSignal(int id) {
		return signals.get(id);
	}

	@Override
	public List<Signal> getSignals() {
		return new LinkedList<Signal>(signals.values());
	}

	@Override
	public void setSignals(List<Signal> signals) {
		if (this.signals == null) {
			this.signals = new HashMap<Integer, Signal>();
		} else {
			this.signals.clear();
		}
		for (Signal signal : signals) {
			this.signals.put(signal.getId(), signal);
		}
	}

	@Override
	public List<Connector> getConnectors() {
		return connectors;
	}

	@Override
	public void setConnectors(List<Connector> connectors) {
		if (this.connectors == null) {
			this.connectors = new LinkedList<Connector>();
		} else {
			this.connectors.clear();
		}

		for (Connector connector : connectors) {
			this.connectors.add(connector);
			Signal signal = signals.get(connector.signalId);
			LogicComponent component = components.get(connector.componentId);
			//System.out.println(component + " " + connector.componentId);
			Port port = component.getPort(connector.portId);
			signal.connect(port);
			port.connect(signal);
		}
	}

	@Override
	public void addComponent(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addSignal(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addConnection(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Signal> findConnectedSignals(int componentId, int portId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Signal> findConnectedSignals(ConnectionPoint point) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnectionPoint> findConnectionPoints(int signalId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConnectionPoint> findConnectionPoints(int signalId, int portType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addComponents(List<LogicComponent> components) {
		for (LogicComponent component : components) {
			this.components.put(component.getId(), component);
		}
	}

	@Override
	public void addSignals(List<Signal> signals) {
		for (Signal signal : signals) {
			this.signals.put(signal.getId(), signal);
		}
	}

	@Override
	public void addConnectors(List<Connector> connectors) {
		//not safe to use :P
	}

}
