package simulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import simulator.digitalcomponents.MemModule;

public class Assembler {
	
	private LinkedList<Instruction> instructions;
	
	
	public Assembler(){
		instructions = new LinkedList<>();
	}
	
	public Assembler(String fname){
		this();
		loadFile(fname);
		fillMem();
	}
	
	public void loadFile(String fname){
		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(fname));
			String line;
			while ((line = in.readLine()) != null) {
				instructions.add(new Instruction(line));
			}
		} catch (Exception e) {

		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void fillMem(){
		if(instructions==null) return;
		Integer pos = Integer.parseInt("100",16);
		for(Instruction instruction : instructions){
			LinkedList<Integer> values = instruction.decodeToIntList();
			for(Integer value : values){
				MultipleDigitalValue mdv = MemModule.bytes.get(pos++);
				mdv.setUIntValue(value);
			}
		}
	}

}
