package simulator;

import java.beans.*;
import java.io.*;
import java.util.*;

import simulator.gui.Drawable;

public class LoaderXML implements Loader {

	@SuppressWarnings("unchecked")
	public <T> List<T> load(String fileName) {
		List<T> result = new LinkedList<T>();
		XMLDecoder in = null;
		try {
			in = new XMLDecoder(new FileInputStream(fileName));

			result = (List<T>) in.readObject();

			// Object data = null;
			// while ((data = in.readObject()) != null) {
			// result.add((T) data);
			// }

		} catch (Exception e) {
			e.printStackTrace();
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
		XMLEncoder out = null;
		try {
			out = new XMLEncoder(new FileOutputStream(fileName));

			out.setExceptionListener(new ExceptionListener() {
				@Override
				public void exceptionThrown(Exception arg0) {

				}
			});

			out.writeObject(args);

			// for (Object data : args) {
			// out.writeObject(data);
			// }

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

}
