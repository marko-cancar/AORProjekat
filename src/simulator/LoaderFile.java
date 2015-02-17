package simulator;

import java.io.*;
import java.util.*;

import simulator.gui.Drawable;

public class LoaderFile implements Loader {

	public static Object create(String type) {
		Object result = null;
		try {
			Class<?> c = Class.forName(type);
			result = c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<LogicComponent> loadComponents(String fileName) {
		List<LogicComponent> result = load(fileName);

		return result;
	}

	public List<Signal> loadSignals(String fileName) {
		List<Signal> result = load(fileName);
		return result;
	}

	public List<Connector> loadConnectors(String fileName) {
		List<Connector> result = new LinkedList<Connector>();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				String[] s = line.split("\t");
				Connector component = new Connector();
				component.init(s);
				result.add(component);
			}
		} catch (Exception e) {

		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
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

	public void store(List<? extends Loadable> args, String fileName) {
		PrintWriter out = null;
		try {
			out = new PrintWriter(new File(fileName));

			for (Loadable data : args) {
				String result = "";
				for (String arg : data.state()) {
					result += arg + "\t";
				}
				out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}

	}

	@SuppressWarnings("unchecked")
	public <T extends Loadable> List<T> load(String fileName) {
		List<T> result = new LinkedList<T>();

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = in.readLine()) != null) {
				//System.out.println(line);
				String[] s = line.split("\t");
				T loadable = (T) create(s[0]);
				//System.out.print(s[1]);
				loadable.init(s);
				result.add(loadable);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public List<Drawable> loadDrawables(String fileName) {
		List<Drawable> result = load(fileName);
		return result;
	}

	@Override
	public void storeDrawables(List<Drawable> drawables, String fileName) {
		// TODO Auto-generated method stub

	}
}
