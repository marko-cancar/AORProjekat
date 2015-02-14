package simulator;

import java.io.*;

public class Connector implements Serializable, Loadable {

	private static final long serialVersionUID = 1L;
	public int signalId;
	public int componentId;
	public int portId;
	public int portType;// redundant

	public Connector() {
		this(0, 0, 0, 0);
	}

	public Connector(int signalId, int componentId, int portId, int portType) {
		super();
		this.signalId = signalId;
		this.componentId = componentId;
		this.portId = portId;
		this.portType = portType;
	}

	public int getSignalId() {
		return signalId;
	}

	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}

	public int getPortType() {
		return portType;
	}

	public void setPortType(int portType) {
		this.portType = portType;
	}

	public String toString() {
		String result = "";
		result += signalId + " " + componentId + " " + portId + " " + portType;
		return result;

	}

	@Override
	public void init(String[] data) {
		this.signalId = Integer.parseInt(data[0]);
		this.componentId = Integer.parseInt(data[1]);
		this.portId = Integer.parseInt(data[2]);
		this.portType = Integer.parseInt(data[3]);
	}

	@Override
	public void init(Object... parameters) {

	}

	@Override
	public String[] state() {
		String[] result = new String[] { "" + signalId, "" + componentId,
				"" + portId, "" + portType };

		return result;
	}
}
