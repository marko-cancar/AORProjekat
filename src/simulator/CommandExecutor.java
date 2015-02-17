package simulator;

import java.util.LinkedList;
import java.util.List;



public class CommandExecutor {
	
	static LinkedList<Command> commands;
	static{
		commands = new LinkedList<>();
		commands.add(new Command("ldMAR",500,1));
		commands.add(new Command("incMAR",500,2));
		commands.add(new Command("ldMDR",501,1));
		commands.add(new Command("MDRout",505,0));
		commands.add(new Command("PCout",601,0));
		commands.add(new Command("incPC",600,2));
		commands.add(new Command("ldIR1",602,1));
		commands.add(new Command("ldIR2",604,1));
		commands.add(new Command("ldIR3",606,1));
		commands.add(new Command("ldIR4",608,1));
		commands.add(new Command("IR1out",603,0));
		commands.add(new Command("IR2out",605,0));
		commands.add(new Command("IR3out",607,0));
		commands.add(new Command("IR4out",609,0));
		commands.add(new Command("IR23out",610,0));
		commands.add(new Command("IR34out",611,0));
		
		commands.add(new Command("ldGPRADR",900,1));
		commands.add(new Command("wrGPR",903,0));
		commands.add(new Command("GPRout",904,0));
		commands.add(new Command("SPout",906,0));
		commands.add(new Command("ldSP",905,1));
		commands.add(new Command("incSP",905,2));
		commands.add(new Command("decSP",905,3));
		commands.add(new Command("ldRW",907,1));
		commands.add(new Command("ldCW",908,1));
		commands.add(new Command("CWout",911,0));

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
