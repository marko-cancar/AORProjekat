package simulator.test;

import java.util.*;

import javax.swing.*;

import simulator.*;
import simulator.gui.*;

public class TestD {

	public static void main(String[] args) {
		
		MultipleDigitalValue mdv = new MultipleDigitalValue(8);
		mdv.setIntValue(-33);
		int x = mdv.getIntValue();
		System.out.println(x);
		
		String componentsTest = "Resources/components.txt";
		String componentsMEM = "Resources/MEMcomponents.txt";
		String componentsBUS = "Resources/BUScomponents.txt";
		String componentsFETCH = "Resources/FETCHcomponents.txt";
		String componentsEXEC = "Resources/EXECcomponents.txt";
		String componentsADDR = "Resources/ADDRcomponents.txt";
		String componentsINTR = "Resources/INTRcomponents.txt";
		String componentsUPRAV = "Resources/UPRAVcomponents.txt";
		String componentsGUSOPER = "Resources/GUSOPERcomponents.txt";
		String componentsGUSUPRAV = "Resources/GUSUPRAVcomponents.txt";
		
		String signalsTest = "Resources/signals.txt";
		String signalsMEM = "Resources/MEMsignals.txt";
		String signalsBUS = "Resources/BUSsignals.txt";
		String signalsFETCH = "Resources/FETCHsignals.txt";
		String signalsEXEC = "Resources/EXECsignals.txt";
		String signalsADDR = "Resources/ADDRsignals.txt";
		String signalsINTR = "Resources/INTRsignals.txt";
		String signalsUPRAV = "Resources/UPRAVsignals.txt";
		String signalsGUSOPER = "Resources/GUSOPERsignals.txt";
		String signalsGUSUPRAV = "Resources/GUSUPRAVsignals.txt";
		
		String connectionsTest = "Resources/connections.txt";
		String connectionsMEM = "Resources/MEMconnections.txt";
		String connectionsBUS = "Resources/BUSconnections.txt";
		String connectionsFETCH = "Resources/FETCHconnections.txt";
		String connectionsEXEC = "Resources/EXECconnections.txt";
		String connectionsADDR = "Resources/ADDRconnections.txt";
		String connectionsINTR = "Resources/INTRconnections.txt";
		String connectionsUPRAV = "Resources/UPRAVconnections.txt";
		String connectionsGUSOPER = "Resources/GUSOPERconnections.txt";
		String connectionsGUSUPRAV = "Resources/GUSUPRAVconnections.txt";

		String guiTest = "Resources/lines.txt";
		String guiSystem = "Resources/linesSystem.txt";
		String guiCPU = "Resources/linesCPU.txt";
		String guiMem = "Resources/MEMlines.txt";
		String guiBUS = "Resources/BUSlines.txt";
		String guiFETCH = "Resources/FETCHlines.txt";
		String guiEXEC = "Resources/EXEClines.txt";
		String guiADDR = "Resources/ADDRlines.txt";
		String guiINTR = "Resources/INTRlines.txt";
		String guiUPRAV = "Resources/UPRAVlines.txt";
		String guiGUSOPER = "Resources/GUSOPERlines.txt";
		String guiGUSUPRAV = "Resources/GUSUPRAVlines.txt";

		Loader loader = new LoaderFile();

		Netlist netlist = new NetlistSimple();
		
		List<LogicComponent> components = loader.loadComponents(componentsTest);
		components.addAll(loader.loadComponents(componentsMEM)); 
		components.addAll(loader.loadComponents(componentsBUS));
		components.addAll(loader.loadComponents(componentsFETCH));
		components.addAll(loader.loadComponents(componentsEXEC));
		components.addAll(loader.loadComponents(componentsADDR));
		components.addAll(loader.loadComponents(componentsINTR));
		components.addAll(loader.loadComponents(componentsUPRAV));
		components.addAll(loader.loadComponents(componentsGUSOPER));
		components.addAll(loader.loadComponents(componentsGUSUPRAV));
		
		List<Connector> connections = loader.loadConnectors(connectionsTest);
		connections.addAll(loader.loadConnectors(connectionsMEM));
		connections.addAll(loader.loadConnectors(connectionsBUS));
		connections.addAll(loader.loadConnectors(connectionsFETCH));
		connections.addAll(loader.loadConnectors(connectionsEXEC));
		connections.addAll(loader.loadConnectors(connectionsADDR));
		connections.addAll(loader.loadConnectors(connectionsINTR));
		connections.addAll(loader.loadConnectors(connectionsUPRAV));
		connections.addAll(loader.loadConnectors(connectionsGUSOPER));
		connections.addAll(loader.loadConnectors(connectionsGUSUPRAV));
		
		List<Signal> signals = loader.loadSignals(signalsTest);
		signals.addAll(loader.loadSignals(signalsMEM));
		signals.addAll(loader.loadSignals(signalsBUS));
		signals.addAll(loader.loadSignals(signalsFETCH));
		signals.addAll(loader.loadSignals(signalsEXEC));
		signals.addAll(loader.loadSignals(signalsADDR));
		signals.addAll(loader.loadSignals(signalsINTR));
		signals.addAll(loader.loadSignals(signalsUPRAV));
		signals.addAll(loader.loadSignals(signalsGUSOPER));
		signals.addAll(loader.loadSignals(signalsGUSUPRAV));
		
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
		drawables.addAll(loader.loadDrawables(guiADDR));
		drawables.addAll(loader.loadDrawables(guiINTR));
		drawables.addAll(loader.loadDrawables(guiUPRAV));
		drawables.addAll(loader.loadDrawables(guiGUSOPER));
		drawables.addAll(loader.loadDrawables(guiGUSUPRAV));
		
		Util.connect(drawables, netlist);

		JFrame frame = new GuiFrame(simulator, drawables);
		frame.setVisible(true);

	}

}
