package simulator;

public class Command {
	private String commandName;
	private int componentId;
	private int portIndex;
	
	public Command(String commandName, int componentId, int portIndex) {
		super();
		this.commandName = commandName;
		this.componentId = componentId;
		this.portIndex = portIndex;
	}

	public String getCommandName() {
		return commandName;
	}

	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public int getPortIndex() {
		return portIndex;
	}

	public void setPortIndex(int portId) {
		this.portIndex = portId;
	}
	
}
