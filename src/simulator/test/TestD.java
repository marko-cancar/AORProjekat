package simulator.test;

import java.util.*;

import javax.swing.*;

import simulator.*;
import simulator.gui.*;

public class TestD {

	public static void main(String[] args) {
		String componentsTest = "Resources/components.txt";
		String componentsMEM = "Resources/MEMcomponents.txt";
		String componentsBUS = "Resources/BUScomponents.txt";
		String componentsFETCH = "Resources/FETCHcomponents.txt";
		String componentsEXEC = "Resources/EXECcomponents.txt";
		
		String signalsTest = "Resources/signals.txt";
		String signalsMEM = "Resources/MEMsignals.txt";
		String signalsBUS = "Resources/BUSsignals.txt";
		String signalsFETCH = "Resources/FETCHsignals.txt";
		String signalsEXEC = "Resources/EXECsignals.txt";
		
		String connectionsTest = "Resources/connections.txt";
		String connectionsMEM = "Resources/MEMconnections.txt";
		String connectionsBUS = "Resources/BUSconnections.txt";
		String connectionsFETCH = "Resources/FETCHconnections.txt";
		String connectionsEXEC = "Resources/EXECconnections.txt";


		String guiTest = "Resources/lines.txt";
		String guiSystem = "Resources/linesSystem.txt";
		String guiCPU = "Resources/linesCPU.txt";
		String guiMem = "Resources/MEMlines.txt";
		String guiBUS = "Resources/BUSlines.txt";
		String guiFETCH = "Resources/FETCHlines.txt";
		String guiEXEC = "Resources/EXEClines.txt";

		Loader loader = new LoaderFile();

		Netlist netlist = new NetlistSimple();
		
		List<LogicComponent> components = loader.loadComponents(componentsTest);
		components.addAll(loader.loadComponents(componentsMEM)); 
		components.addAll(loader.loadComponents(componentsBUS));
		components.addAll(loader.loadComponents(componentsFETCH));
		components.addAll(loader.loadComponents(componentsEXEC));
		
		List<Connector> connections = loader.loadConnectors(connectionsTest);
		connections.addAll(loader.loadConnectors(connectionsMEM));
		connections.addAll(loader.loadConnectors(connectionsBUS));
		connections.addAll(loader.loadConnectors(connectionsFETCH));		
		connections.addAll(loader.loadConnectors(connectionsEXEC));
		
		
		List<Signal> signals = loader.loadSignals(signalsTest);
		signals.addAll(loader.loadSignals(signalsMEM));
		signals.addAll(loader.loadSignals(signalsBUS));
		signals.addAll(loader.loadSignals(signalsFETCH));
		signals.addAll(loader.loadSignals(signalsEXEC));
		
		netlist.setComponents(components);
		netlist.setSignals(signals);
		netlist.setConnectors(connections);

		Simulator simulator = new TimeDrivenSimulator();
		simulator.setNetlist(netlist);
		simulator.init();

		List<Drawable> drawables = loader.loadDrawables(guiTest);
		drawables.addAll(loader.loadDrawables(guiSystem));
		drawables.addAll(loader.loadDrawables(guiCPU));
		drawables.addAll(loader.loadDrawables(guiMem));
		drawables.addAll(loader.loadDrawables(guiBUS));
		drawables.addAll(loader.loadDrawables(guiFETCH));
		drawables.addAll(loader.loadDrawables(guiEXEC));
		
		Util.connect(drawables, netlist);

		JFrame frame = new GuiFrame(simulator, drawables);
		frame.setVisible(true);

	}

}
