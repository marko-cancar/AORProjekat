package simulator;

public class Event implements Comparable<Event> {

	long time;

	Value value;

	EventExecutable dst; // destination component or signal
	int dstSignalId; // port or signal id on destination

	EventExecutable src; // optional //source component or signal
	int srcSignalId; // optional//port or signal id on source

	public Event(long time, Value value, EventExecutable dst, int dstSignalId) {
		this(time, value, dst, dstSignalId, null, 0);
	}

	public Event(long time, Value value, EventExecutable dst, int dstSignalId,
			EventExecutable src, int srcSignalId) {
		super();
		this.time = time;
		this.value = value;
		this.dst = dst;
		this.dstSignalId = dstSignalId;
		this.src = src;
		this.srcSignalId = srcSignalId;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public EventExecutable getDst() {
		return dst;
	}

	public void setDst(EventExecutable dst) {
		this.dst = dst;
	}

	public int getDstSignal() {
		return dstSignalId;
	}

	public void setDstSignal(int dstSignal) {
		this.dstSignalId = dstSignal;
	}

	public EventExecutable getSrc() {
		return src;
	}

	public void setSrc(EventExecutable src) {
		this.src = src;
	}

	public int getSrcSignal() {
		return srcSignalId;
	}

	public void setSrcSignal(int srcSignal) {
		this.srcSignalId = srcSignal;
	}

	public int compareTo(Event t) {
		return time > t.time ? 1 : time < t.time ? -1 : dst.hashCode()
				- t.dst.hashCode();
	}

}
