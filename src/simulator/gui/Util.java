package simulator.gui;

import java.util.List;

import simulator.*;

public class Util {
	public static void connect(List<Drawable> drawables, Netlist netlist) {
		for (Drawable d : drawables) {
			d.init(netlist);
		}
	}

}
