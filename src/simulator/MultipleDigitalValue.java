package simulator;

import java.util.*;

public class MultipleDigitalValue implements Value {

	private static final long serialVersionUID = 1L;

	public static final int ZERO = 0;
	public static final int ONE = 1;

	private List<DigitalValue> value;
	protected boolean highZ;
	
	public MultipleDigitalValue(int size) {
		this.value = new LinkedList<>();
		for (int i = 0; i < size; i++)
			value.add(new DigitalValue(ZERO));
	}

	public MultipleDigitalValue() {
		this(8);
	}

	public MultipleDigitalValue(MultipleDigitalValue d) {
		this.value = d.value;
	}

	public MultipleDigitalValue(String val) {
		this();
		this.setStringValue(val);
	}

	public MultipleDigitalValue(List<DigitalValue> value) {
		this.value = value;
	}

	@Override
	public int getIntValue() {
		int res=0;
		int exp = value.size()-1;
		boolean isPositive = !value.get(0).getBooleanValue();
		if(isPositive){
			res += value.get(0).getIntValue() * Math.pow(2, exp);
		}
		else{
			res -= value.get(0).getIntValue() * Math.pow(2, exp);
		}
		exp--;
		for(int i=1; i<value.size(); i++){
			res += value.get(i).getIntValue() * Math.pow(2, exp);
			exp--;
		}
		return res;
	}

	@Override
	public void setIntValue(int val) {
		valudate(val, value.size());
		setHighZ(false);
		if (val < 0) {
			val = -val;
			for (int i = value.size() - 1; i >= 0; i--) {
				value.get(i).setIntValue(val % 2);
				val = val / 2;
			}
			boolean complementing = false;
			for(int i=value.size()-1; i>=0; i--){
				if(complementing){
					value.get(i).setBooleanValue(!value.get(i).getBooleanValue());
				}else{
					value.get(i).setBooleanValue(value.get(i).getBooleanValue());
				}
				if(value.get(i).getBooleanValue()){
					complementing = true;
				}
			}
		} else {
			for (int i = value.size() - 1; i >= 0; i--) {
				value.get(i).setIntValue(val % 2);
				val = val / 2;
			}

		}
	}
	
	@Override
	public int getUIntValue() {
		int ret = 0;
		for (int i = 0; i < value.size(); i++) {
			ret += value.get(i).getIntValue()*Math.pow(2.0, value.size() - 1 - i);
		}
		return ret;
	}

	protected static void valudate(int value, int size) {
		if(value < -1*Math.pow(2.0, size - 1) || value > Math.pow(2.0,size-1)) {
			String msg = "Vrednost kod koje puca validation je "+value+", broj bita je "+size+".";
			System.out.println(msg);
			throw new ValidationException();
		}
	}

	@Override
	public long getLongValue() {
		return getIntValue();
	}

	@Override
	public void setLongValue(long value) {
		setIntValue((int) value);
	}

	@Override
	public boolean getBooleanValue() {
		boolean ret = true;
		for (int i = 0; i < value.size(); i++) {
			ret = ret && value.get(i).getBooleanValue();
		}
		return ret;
	}

	@Override
	public void setBooleanValue(boolean val) {
		for (int i = 0; i < value.size(); i++) {
			value.get(i).setBooleanValue(val);
		}

	}

	@Override
	public String getStringValue() {
		String s = "";
		for (int i = 0; i < value.size(); i++) {
			s += value.get(i).getStringValue();
		}
		return s;
	}

	@Override
	public void setStringValue(String val) {
		setIntValue(Integer.parseInt(val));
	}

	@Override
	public int getSize() {
		return value.size();
	}

	@Override
	public List<Value> separateValues() {
		List<Value> result = new LinkedList<Value>();
		for (DigitalValue dig : value) {
			result.add(dig);
		}
		return result;
	}

	@Override
	public Value copy() {
		MultipleDigitalValue result = new MultipleDigitalValue(value);
		return result;
	}

	@Override
	public boolean load(Value value) {
		setHighZ(false);
		List<Value> values = value.separateValues();
		if(value.getSize() == this.value.size()){
			for(int i=0; i<value.getSize(); i++){
				this.value.get(i).setBooleanValue(values.get(i).getBooleanValue());
			}
		}else{
			throw new DistinctSizeException();
		}
		return true;
	}

	@Override
	public int compareTo(Value val) {
		return getIntValue() - val.getIntValue();
	}

	public boolean equals(Value val) {
		return compareTo(val) == 0;
	}

	public String toString() {
		return value.toString();
	}

	@Override
	public int incValue() {
		for(int i=value.size()-1; i>=0; i--){
			if(!value.get(i).getBooleanValue()){
				value.get(i).setBooleanValue(true);
				return 0;
			}else{
				value.get(i).setBooleanValue(false);
			}
		}
		return 1;
	}

	@Override
	public void decValue() {
		for(int i=value.size()-1; i>=0; i--){
			if(value.get(i).getBooleanValue()){
				value.get(i).setBooleanValue(false);
				return;
			}else{
				value.get(i).setBooleanValue(true);
			}
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
	public void setUIntValue(int val) {
		setHighZ(false);	
		for (int i = value.size() - 1; i >= 0; i--) {
			value.get(i).setIntValue(val % 2);
			val = val / 2;
		}
	}
}
