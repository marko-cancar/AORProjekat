package simulator;

import java.util.*;

public class EventDrivenSimulator extends Simulator {
	Queue<Event> queue;

	public EventDrivenSimulator() {
		super();
		queue = new PriorityQueue<Event>();
	}

	public EventDrivenSimulator(long currentTime, long endTime) {
		super(currentTime, endTime);
		queue = new PriorityQueue<Event>();
	}

	public void simulate(long time) {
		while (currentTime < time) {
			Event event = queue.remove();
			currentTime = event.getTime();
			List<Event> events = event.dst.execute(event);
			queue.addAll(events);
		}
	}

	public void init() {
		List<LogicComponent> components = netlist.getComponents();
		for (LogicComponent component : components) {
			queue.addAll(component.preCalculateEvents());
		}

		List<Signal> signals = netlist.getSignals();
		for (Signal signal : signals) {
			queue.addAll(signal.preCalculateEvents());
		}
	}

}
