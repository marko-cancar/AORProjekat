package simulator.gui;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

import simulator.*;

public class GuiFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	Simulator simulator;

	CommandPanel commandPanel;

	ValueTablePanel valueTablePanel;

	GraphicsPanel graphicsPanel;

	List<WorkingPanel> workingPanels;
	
	List<String> titles;

	private JTabbedPane tabbedPane;

	public GuiFrame(Simulator simulator, List<Drawable> drawables) {
		super("AOR2 Simulator Mozda Nesto I Radi");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		titles = new LinkedList<>();
		titles.add("Test");//0
		titles.add("System");//1
		titles.add("CPU");//2
		titles.add("Memorija");//3
		titles.add("Sta ovdje?");//4
		titles.add("Bus");//5
		titles.add("Fetch");//6
		titles.add("Exec");//7
		titles.add("Intr");//8
		titles.add("Addr");//9
		titles.add("Uprav");//10
		titles.add("Gen. uprav. sig. oper.");//11
		titles.add("Gen. uprav. sig. uprav.");//12

		this.simulator = simulator;

		setDefaultLookAndFeelDecorated(true);

		this.setSize(500, 400);
		this.setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		this.setLayout(new BorderLayout());

		JPanel centerPanel = new JPanel(); // centerPanel (appearance)
		centerPanel.setLayout(new GridLayout(1, 1));
		this.add(centerPanel, BorderLayout.CENTER);
		tabbedPane = new JTabbedPane();
		workingPanels = new LinkedList<>();
		for (int i = 0; i < titles.size(); i++) {
			List<Drawable> drawablesTemp = new LinkedList<>();
			for (Drawable d : drawables) {
				if (d.getPanelIndex() == i) {
					drawablesTemp.add(d);
				}
			}
			WorkingPanel wp = new WorkingPanel(this,drawablesTemp);
			wp.setTitle(titles.get(i));
			workingPanels.add(wp);
			wp.setPreferredSize(new Dimension(2000, 5000));
		}

		
		WorkingPanel wp = workingPanels.get(0);
		JScrollPane scrollPane = new JScrollPane(wp);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		tabbedPane.addTab(wp.getTitle(), scrollPane);
		centerPanel.add(tabbedPane);
		
		wp = workingPanels.get(1);
		scrollPane = new JScrollPane(wp);
		tabbedPane.addTab(wp.getTitle(), scrollPane);
		centerPanel.add(tabbedPane);
//		
//		wp = workingPanels.get(3);
//		scrollPane = new JScrollPane(wp);
//		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//		tabbedPane.addTab(wp.getTitle(), scrollPane);
//		centerPanel.add(tabbedPane);
//		
//		wp = workingPanels.get(5);
//		scrollPane = new JScrollPane(wp);
//		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//		tabbedPane.addTab(wp.getTitle(), scrollPane);
//		centerPanel.add(tabbedPane);
//		
//		wp = workingPanels.get(6);
//		scrollPane = new JScrollPane(wp);
//		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
//		tabbedPane.addTab(wp.getTitle(), scrollPane);
//		centerPanel.add(tabbedPane);
		
		tabbedPane.setSelectedIndex(1);

		// adds all signals test only
		graphicsPanel = new GraphicsPanel(this, simulator.getNetlist()
				.getSignals(), simulator);
		// centerPanel.add(graphicsPanel);

		JPanel eastPanel = new JPanel(); // centerPanel (appearance)
		this.add(eastPanel, BorderLayout.EAST);
		eastPanel.setLayout(new GridLayout(2, 1));
		eastPanel.setPreferredSize(new Dimension(200, 500));

		valueTablePanel = new ValueTablePanel(this);
		// adds all signals test only
		valueTablePanel.addSignals(simulator.getNetlist().getSignals());
		eastPanel.add(valueTablePanel);

		commandPanel = new CommandPanel(this, simulator);
		eastPanel.add(commandPanel);

	}
	
	public void addPanel(int index){
		boolean opened = false;
		int indexInPane = 0;
		for(int i=0; i<tabbedPane.getTabCount(); i++){
			if(tabbedPane.getTitleAt(i).equals(workingPanels.get(index).getTitle())){
				opened = true;
				indexInPane = i;
			}
		}
		if(!opened){
			WorkingPanel wp = workingPanels.get(index);
			JScrollPane sp = new JScrollPane(wp);
			sp.getVerticalScrollBar().setUnitIncrement(16);
			tabbedPane.addTab(wp.getTitle(), sp);
			tabbedPane.setSelectedIndex(tabbedPane.getTabCount()-1);
			tabbedPane.setTabComponentAt(tabbedPane.getTabCount()-1, 
					new CloseButtonTabComponent(tabbedPane));
		}else{
			tabbedPane.setSelectedIndex(indexInPane);
		}
	}

}