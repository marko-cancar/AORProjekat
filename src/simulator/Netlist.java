package simulator;

import java.util.*;

public interface Netlist {

	LogicComponent getComponent(int id);
	
	List<LogicComponent> getComponents();

	void setComponents(List<LogicComponent> components);
	
	void addComponents(List<LogicComponent> components);

	Signal getSignal(int id);
	
	List<Signal> getSignals();

	void setSignals(List<Signal> signals);
	
	void addSignals(List<Signal> signals);

	List<Connector> getConnectors();

	void setConnectors(List<Connector> connectors);
	
	void addConnectors(List<Connector> connectors);

	void addComponent(String[] args);

	void addSignal(String[] args);

	void addConnection(String[] args);

	List<Signal> findConnectedSignals(int componentId, int portId);

	List<Signal> findConnectedSignals(ConnectionPoint point);

	List<ConnectionPoint> findConnectionPoints(int signalId);

	List<ConnectionPoint> findConnectionPoints(int signalId, int portType);

}
