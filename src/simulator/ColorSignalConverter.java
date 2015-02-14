package simulator;

import java.awt.*;

public class ColorSignalConverter {

	public static Color convert(Signal signal) {
		try {
			if(signal.getValue().getSize()==1){
				if(signal.getValue().isHighZ()){
					return Color.LIGHT_GRAY;
				}
				if (signal.getValue().getBooleanValue()) {
					return Color.RED;
				} else {
					return Color.BLUE;
				}
			}else{
				if(signal.getValue().isHighZ()){
					return Color.LIGHT_GRAY;
				}else{
					return new Color(128,128,0);
				}
			}
		} catch (Exception e) {
			return Color.BLACK;
		}
	}
}
