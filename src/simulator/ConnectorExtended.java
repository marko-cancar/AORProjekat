package simulator;

public class ConnectorExtended extends Connector {
	private static final long serialVersionUID = 1L;

	public int portStart;
	public int portEnd;
	public int signalStart;
	public int signalEnd;

	public int getPortStart() {
		return portStart;
	}

	public void setPortStart(int portStart) {
		this.portStart = portStart;
	}

	public int getPortEnd() {
		return portEnd;
	}

	public void setPortEnd(int portEnd) {
		this.portEnd = portEnd;
	}

	public int getSignalStart() {
		return signalStart;
	}

	public void setSignalStart(int signalStart) {
		this.signalStart = signalStart;
	}

	public int getSignalEnd() {
		return signalEnd;
	}

	public void setSignalEnd(int signalEnd) {
		this.signalEnd = signalEnd;
	}

}
