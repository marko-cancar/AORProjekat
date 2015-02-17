package simulator.digitalcomponents;

import java.io.*;
import java.util.*;
import simulator.*;

public class MicroMem extends LogicComponentSimple {
	private static final long serialVersionUID = 1L;
	
	public static final int CAPACITY = 256;
	private int wordSize;
	private List<MultipleDigitalValue> words;
	
	private static ArrayList<String[]> programStrings;
	private static HashMap<String, Integer> conditionsMap1007;
	private static HashMap<String, String> signalsMap;
	public static ArrayList<String> viewerOutputStrings;
	
	static{
		loadConditions1007Map();
		loadSignalsMap();
		loadProgram();
	}
	
	@Override
	public void init(Object... parameters) {
		ports = new ArrayList<Port>();
		
		//ADDRESS IN
		Port port = new PortSimple(Port.IN, new MultipleDigitalValue(8),
				"IN" + 0, "in" + 0, 0, this);
		ports.add(port);
		
		//DATA OUT
		port = new PortSimple(Port.OUT,
				new MultipleDigitalValue(wordSize), "OUT" + 1, "out"
						+ 1, 1, this);
		ports.add(port);
	}

	@Override
	public void init(String[] data) {
		name = data[1];
		id = Integer.parseInt(data[2]);
		wordSize = Integer.parseInt(data[3]);
		init();
		
	}

	@Override
	public String[] state() {
		String[] state = new String[] { this.getClass().getName(),
				"" + this.id };
		return state;
	}

	@Override
	public void preCalculateTime() {
		if(words==null){
			words = new ArrayList<>();
		}
		MultipleDigitalValue mdv;
		int len = programStrings.size();
		for(int i=0; i<len; i++){
			String[] codeSignals = programStrings.get(i);
			if(id==1006){
				int value = 0;
				for(int j=0; j<codeSignals.length; j++){
					if(codeSignals[j].startsWith("M1")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<24;
					}
					else if(codeSignals[j].startsWith("M2")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<20;
					}
					else if(codeSignals[j].startsWith("M3")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<16;
					}
					else if(codeSignals[j].startsWith("M4")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<12;
					}
					else if(codeSignals[j].startsWith("M5")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<8;
					}
					else if(codeSignals[j].startsWith("M6")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp<<4;
					}
					else if(codeSignals[j].startsWith("M7")){
						int tmp = Integer.parseInt(codeSignals[j].substring(2),16);
						value+= tmp;
					}
				}
				mdv = new MultipleDigitalValue(wordSize);
				mdv.setUIntValue(value);
				words.add(mdv);
			}
			else if(id==1007){
				int value = 0;
				for(int j=0; j<codeSignals.length; j++){
					if(conditionsMap1007.containsKey(codeSignals[j])){
						value = conditionsMap1007.get(codeSignals[j]);
					}
				}
				mdv = new MultipleDigitalValue(wordSize);
				mdv.setUIntValue(value);
				words.add(mdv);
			}
			else if(id==1008){
				int value = 0;
				for(int j=0; j<codeSignals.length; j++){
					if(codeSignals[j].startsWith("madr")){
						value = Integer.parseInt(codeSignals[j].substring(4),16);
					}
				}
				mdv = new MultipleDigitalValue(wordSize);
				mdv.setUIntValue(value);
				words.add(mdv);
			}
		}
		execute();
	}

	public int calculate() {
		return 0;
	}
	
	@Override
	public void execute() {
		Port addr = ports.get(0);
		Port dataOut = ports.get(1);
		dataOut.getValue().load(words.get(addr.getValue().getUIntValue()));
	}

	@Override
	public List<Event> preCalculateEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> execute(Event event) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static void loadConditions1007Map(){
		conditionsMap1007 = new HashMap<>();
		try(BufferedReader br = new BufferedReader(new FileReader("Resources/Upravljacka.hash1"))) {
			String line;
		    while((line = br.readLine()) != null){
		    	String[] s = line.split("\t");
		    	String key = s[1];
		    	Integer value = Integer.parseInt(s[0],16);
		    	conditionsMap1007.put(key, value);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadSignalsMap(){
		signalsMap = new HashMap<>();
		try(BufferedReader br = new BufferedReader(new FileReader("Resources/Upravljacka.signals"))) {
			String line;
		    while((line = br.readLine()) != null){
		    	String[] s = line.split("\t");
		    	String key = s[0];
		    	String val = s[1];
		    	signalsMap.put(key, val);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static void loadProgram(){
		programStrings = new ArrayList<>();
		viewerOutputStrings = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader("Resources/Upravljacka.code"))) {
			String line;
		    while((line = br.readLine()) != null){
		    	String[] s = line.split("\t");
		    	StringBuilder sb = new StringBuilder();
		    	sb.append(s[0]).append(": ");
		    	String[] codedSignals = s[1].split(",");
		    	programStrings.add(codedSignals);
		    	for(int i=0; i<codedSignals.length; i++){
		    		if(signalsMap.containsKey(codedSignals[i])){
		    			sb.append(signalsMap.get(codedSignals[i]));
		    		}
		    		else{
		    			sb.append(codedSignals[i]);
		    		}
		    		if(i!=codedSignals.length-1) sb.append(", ");
		    	}
		    	viewerOutputStrings.add(sb.toString());
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
