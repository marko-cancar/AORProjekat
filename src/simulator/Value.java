package simulator;

import java.io.*;
import java.util.*;

public interface Value extends Serializable, Comparable<Value> {
	
	public boolean isHighZ();
	
	public void setHighZ(boolean highZ);

	public int getIntValue();
	
	public int getUIntValue();
	
	public void setUIntValue(int value);

	public void setIntValue(int value);

	public long getLongValue();

	public void setLongValue(long value);

	public boolean getBooleanValue();

	public void setBooleanValue(boolean val);

	public String getStringValue();

	public void setStringValue(String val);

	public int getSize();

	public List<Value> separateValues();

	public Value copy();

	public boolean load(Value value);
	
	public int incValue();
	
	public void decValue();

}