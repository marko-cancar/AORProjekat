package simulator;

import java.io.*;
import java.util.*;

import simulator.gui.Drawable;

public class LoaderObject implements Loader {

	@SuppressWarnings("unchecked")
	public <T> List<T> load(String fileName) {
		List<T> result = new LinkedList<T>();
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			Object data = null;
			while ((data = in.readObject()) != null) {
				result.add((T) data);
			}

		} catch (Exception e) {
			// e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public List<LogicComponent> loadComponents(String fileName) {
		return load(fileName);
	}

	@Override
	public List<Signal> loadSignals(String fileName) {
		return load(fileName);
	}

	@Override
	public List<Connector> loadConnectors(String fileName) {
		return load(fileName);
	}

	@Override
	public List<Drawable> loadDrawables(String fileName) {
		return load(fileName);
	}

	@Override
	public void storeComponents(List<LogicComponent> components, String fileName) {
		store(components, fileName);
	}

	@Override
	public void storeSignals(List<Signal> signals, String fileName) {
		store(signals, fileName);
	}

	@Override
	public void storeConnectors(List<Connector> connectors, String fileName) {
		store(connectors, fileName);
	}

	@Override
	public void storeDrawables(List<Drawable> drawables, String fileName) {
		store(drawables, fileName);
	}

	public void store(List<?> args, String fileName) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));

			for (Object data : args) {
				out.writeObject(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
