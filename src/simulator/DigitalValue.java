package simulator;

import java.util.*;

public class DigitalValue implements Value {

	private static final long serialVersionUID = 1L;

	public static final int ZERO = 0;
	public static final int ONE = 1;

	protected int value;

	private boolean highZ;

	public DigitalValue(int value) {
		this.value = value;
	}

	public DigitalValue() {
		this(ZERO);
	}

	public DigitalValue(DigitalValue d) {
		this(d.getIntValue());
	}

	public DigitalValue(String val) {
		this();
		this.setStringValue(val);
	}

	@Override
	public int getIntValue() {
		return value;
	}

	@Override
	public void setIntValue(int value) {
		valudate(value);
		setHighZ(false);
		this.value = value;
	}

	@Override
	public long getLongValue() {
		return value;
	}

	@Override
	public void setLongValue(long value) {
		setIntValue((int) value);
	}

	@Override
	public boolean getBooleanValue() {
		if (value == ZERO) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void setBooleanValue(boolean val) {
		if (val) {
			value = ONE;
		} else {
			value = ZERO;
		}
	}

	@Override
	public String getStringValue() {
		return Integer.toString(value);
	}

	@Override
	public void setStringValue(String val) {
		setIntValue(Integer.parseInt(val));
	}

	@Override
	public int getSize() {
		return 1;
	}

	@Override
	public List<Value> separateValues() {
		List<Value> result = new LinkedList<Value>();
		result.add(this);
		return result;
	}

	@Override
	public Value copy() {
		DigitalValue result = new DigitalValue(value);
		return result;
	}

	@Override
	public boolean load(Value value) {
		setIntValue(value.getIntValue());
		return true;
	}

	protected static void valudate(int value) {
		if (!values.contains(value)) {
			throw new ValidationException();
		}
	}

	static Set<Integer> values;
	static Map<Integer, String> names;
	static {
		values = new HashSet<Integer>();
		values.add(ZERO);
		values.add(ONE);
		names = new HashMap<Integer, String>();
		names.put(ZERO, "ZERO");
		names.put(ONE, "ONE");
	}

	@Override
	public int compareTo(Value val) {
		return getIntValue() - val.getIntValue();
	}

	public boolean equals(Value val) {
		return compareTo(val) == 0;
	}

	public String toString() {
		String temp = names.get(value);
		if (temp != null)
			return temp;
		return "";
	}

	@Override
	public int incValue() {
		if(value == ONE){
			value = ZERO;
			return 1;
		}
		value = ONE;
		return 0;
	}

	@Override
	public void decValue() {
		if(value == ONE){
			value = ZERO;
		}
	}

	@Override
	public boolean isHighZ() {
		return highZ;
	}

	@Override
	public void setHighZ(boolean highZ) {
		this.highZ = highZ;
	}

	@Override
	public int getUIntValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setUIntValue(int val) {
		// TODO Auto-generated method stub
		
	}
}
