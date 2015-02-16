package simulator;

import java.util.LinkedList;
import java.util.List;

public class ControlUnit {

	enum State {state0, state1, state2, state3, state4, state5, state6};
	State state = State.state0;
	private Simulator simulator;
	
	public ControlUnit(Simulator simulator){
		this.simulator = simulator;
	}
	
	public boolean portBoolValue(int componentID, int portIndex) throws RuntimeException{
		List<LogicComponent> components = simulator.getNetlist().getComponents();
		for(LogicComponent component : components){
			if(component.getId() == componentID){
				Port port = component.getPort(portIndex);
				return port.getValue().getBooleanValue();
			}
		}
		throw new RuntimeException("Component with ID " + componentID + " not found.");
	}
	
	public void execute(){
		//boolean condition1 = portBoolValue(300, 1);
		switch(state){
		case state0:
			state = State.state1;
			break;
		case state1:
			state = State.state2;
			break;
		case state2:
			state = State.state3;
			break;
		case state3:
			state = State.state4;
			break;
		case state4:
			state = State.state5;
			break;
		case state5:
			state = State.state6;
			break;
		case state6:
			state = State.state0;
			break;
		default:
			break;
		}
		System.out.println(state);
	}
	
	public java.util.List<String> getCommands(){
		java.util.List<String> commands = new LinkedList<>();
		if(state == State.state0){
		} 
		if(state == State.state1){
		}
		if(state == State.state2){
		}
		if(state == State.state3){
		}
		if(state == State.state4){
		}
		if(state == State.state5){
		}
		return commands;
	}

}
