package simulator;

import java.io.Serializable;
import java.util.*;

public interface LogicComponent extends TimeExecutable, EventExecutable,
		Serializable, Loadable {

	public int getId();

	public void setId(int id);

	public String getName();

	public void setName(String name);

	public List<Port> getPorts();

	public void setPorts(List<Port> ports);

	public Port getPort(int index);

	public void setPort(int index, Port port);

	public Port findPort(String name);

	public List<Port> findPorts(int portType);

	public List<Port> findUnconnectedPorts(int type);

	public List<Signal> getSignals();

	public void setSignals(List<Signal> signals);

	public Signal findSignal(String inName);

	public List<Signal> findUnconnectedSignal();

	public List<LogicComponent> getSubComponents();

	public void setSubComponents(List<LogicComponent> subComponents);

	public int getComponentCount();

	public Netlist getNetlist();

	public void setNetlist(Netlist netlist);

	public String getDescription();

	public void setDescription(String description);
}
