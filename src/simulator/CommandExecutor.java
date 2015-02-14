package simulator;

import java.util.LinkedList;
import java.util.List;



public class CommandExecutor {
	
	static LinkedList<Command> commands;
	static{
		commands = new LinkedList<>();
		commands.add(new Command("ldMAR",500,1));
		commands.add(new Command("ldMDR",501,1));
		commands.add(new Command("MDRout",505,0));
		commands.add(new Command("PCout",601,0));
		commands.add(new Command("incPC",600,2));
		commands.add(new Command("ldIR1",602,1));
	}
	Simulator simulator;
	
	public CommandExecutor(Simulator simulator){
		this.simulator = simulator;
	}
	
	public void undoAllCommands(){
		for(Command command : commands){
			execute(command.getCommandName(), false);
		}
	}
	
	public void execute(String commandName, boolean boolValue) throws RuntimeException{
		if(commandName == null || commandName.equals("")){
			return;
		}
		Command commandToExecute = null;
		for(Command command : commands){
			if(command.getCommandName().equals(commandName)){
				commandToExecute = command;
				break;
			}
		}
		if(commandToExecute == null) throw new RuntimeException("Command does NOT exist!");
		List<LogicComponent> components = simulator.getNetlist().getComponents();
		for(LogicComponent component : components){
			if(component.getId() == commandToExecute.getComponentId()){
				Port port = component.getPort(commandToExecute.getPortIndex());
				port.getValue().setBooleanValue(boolValue);
				System.out.println(component);
			}
		}
	}

}
