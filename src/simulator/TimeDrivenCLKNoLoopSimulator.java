package simulator;

import java.util.*;

public class TimeDrivenCLKNoLoopSimulator extends TimeDrivenCLKSimulator {

	List<TimeExecutable> executables;

	public TimeDrivenCLKNoLoopSimulator() {
		super();

	}

	public TimeDrivenCLKNoLoopSimulator(long currentTime, long endTime,
			long clkInterval) {
		super(currentTime, endTime, clkInterval);
	}

	public void simulate(long time) {
		while (currentTime < time) {

			for (LogicComponent component : sequentialComponents) {
				component.execute();
			}

			for (Signal signal : sequentialSignals) {
				signal.execute();
			}

			for (TimeExecutable component : executables) {
				component.execute();
			}
			currentTime += clkInterval;
		}
	}

	public void init() {

		super.init();

		executables = topologicallySort(combinationalComponents,
				combinationalSignals, netlist);
	}

	private List<TimeExecutable> topologicallySort(
			List<LogicComponent> combinationalComponents,
			List<Signal> combinationalSignals, Netlist netlist) {
		// TODO Auto-generated method stub

		return null;
	}

}
