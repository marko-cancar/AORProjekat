package simulator;

public abstract class Simulator {
	long currentTime;

	long endTime;

	Netlist netlist;

	public Simulator() {
		this(0, Long.MAX_VALUE);
	}

	public Simulator(long currentTime, long endTime) {
		super();
		this.currentTime = currentTime;
		this.endTime = endTime;
	}

	public long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(long currentTime) {
		this.currentTime = currentTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public Netlist getNetlist() {
		return netlist;
	}

	public void setNetlist(Netlist netlist) {
		this.netlist = netlist;
	}

	public abstract void init();

	public abstract void simulate(long time);

}
