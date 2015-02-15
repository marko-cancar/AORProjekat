package simulator.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simulator.CommandExecutor;
import simulator.ControlUnit;
import simulator.Simulator;
import simulator.dialogs.Mem;

public class CommandPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JFrame parent;

	Simulator simulator;

	private CommandExecutor commandExecutor;
	private ControlUnit controlUnit;

	public CommandPanel(JFrame guiFrame, Simulator simulator) {
		super();
		this.parent = guiFrame;
		this.setSize(490, 390);
		setBackground(Color.YELLOW);

		JButton button = new JButton("Next");
		button.addActionListener(new MyActionListener());
		add(button);
		
		JButton mem = new JButton("Mem");
		mem.addActionListener(new MemActionListener());
		add(mem);

		this.simulator = simulator;
		commandExecutor = new CommandExecutor(simulator);
		controlUnit = new ControlUnit(simulator);
	}
	
	private int interval = 1;

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			simulator.simulate(simulator.getCurrentTime() + 1);
			   commandExecutor.undoAllCommands();
			   controlUnit.execute();
			   for(String commandName : controlUnit.getCommands()){
			    commandExecutor.execute(commandName, true);
			    //System.out.println("executed " + commandName);
			   }
			   simulator.simulate(simulator.getCurrentTime() + interval - 1);
			   //((TimeDrivenSimulator)simulator).updateSignals();
			   interval = 20;
			   parent.repaint();
		}
	}
	
	class MemActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JDialog dialog = new Mem();
			dialog.setVisible(true);
		}
	}

}
