package simulator.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import simulator.*;
import simulator.dialogs.*;
import simulator.digitalcomponents.MicroMem;

public class CommandPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	JFrame parent;

	Simulator simulator;

	private CommandExecutor commandExecutor;
	private ControlUnit controlUnit;
	private JLabel clkLabel;
	private int clk;
	private JLabel upravCommand;
	
	public CommandPanel(JFrame guiFrame, Simulator simulator) {
		super();
		this.parent = guiFrame;
		this.setSize(490, 390);
		this.setLayout(new GridLayout(10,1));
		setBackground(Color.WHITE);
		JButton button = new JButton("Next");
		button.addActionListener(new MyActionListener());
		add(button);
		JButton mem = new JButton("Mem");
		mem.addActionListener(new MemActionListener());
		add(new JLabel());
		add(mem);
		this.simulator = simulator;
		commandExecutor = new CommandExecutor(simulator);
		controlUnit = new ControlUnit(simulator);
		clkLabel = new JLabel("CLK: 0",JLabel.CENTER);
		clk = -1;
		add(clkLabel);
		upravCommand = new JLabel("",JLabel.CENTER);
		add(upravCommand);
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
			   
			   clk++;
			   clkLabel.setText("CLK: "+clk);
			   int mPcVal = controlUnit.portIntValue(1000, 5);
			   String text = MicroMem.viewerOutputStrings.get(mPcVal);
			   String labelText = String.format("<html><div WIDTH=%d>%s</div><html>", 170, text);
			   upravCommand.setText(labelText);
			   
			   parent.repaint();
			   
			   
			   System.out.println(MicroMem.viewerOutputStrings.get(10));
			   System.out.println(MicroMem.viewerOutputStrings.get(88));
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
