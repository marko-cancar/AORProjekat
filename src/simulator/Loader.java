package simulator;

import java.util.*;

import simulator.gui.Drawable;

public interface Loader {

	public List<LogicComponent> loadComponents(String fileName);

	public List<Signal> loadSignals(String fileName);

	public List<Connector> loadConnectors(String fileName);

	public void storeComponents(List<LogicComponent> components, String fileName);

	public void storeSignals(List<Signal> signals, String fileName);

	public void storeConnectors(List<Connector> connectors, String fileName);

	public List<Drawable> loadDrawables(String fileName);

	public void storeDrawables(List<Drawable> drawables, String fileName);

}
