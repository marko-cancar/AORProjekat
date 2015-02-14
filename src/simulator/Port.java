package simulator;

public interface Port extends Signal {
	public static final int IN = 1;
	public static final int OUT = 0;
	public static final int INOUT = 10;

	public static final int PORT = 6;

	public void setType(int type);

	public int getType();

	public void setDefaultValue(Value defaultValue);

	public Value getDefaultValue();

	public void setInName(String inName);

	public String getInName();

	public void setPortNumber(int number);

	public int getPortNumber();

	public void setLogicComponent(LogicComponent component);

	public LogicComponent getLogicComponent();

}
