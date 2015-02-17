package simulator;

import java.util.LinkedList;

public class Instruction {
	
	static LinkedList<InstructionCode> bezadr;
	static{
		bezadr = new LinkedList<>();
		bezadr.add(new InstructionCode("RTS",	"00000000"));
		bezadr.add(new InstructionCode("RTI",	"00000001"));
		bezadr.add(new InstructionCode("ASR",	"00000010"));
		bezadr.add(new InstructionCode("LSR",	"00000011"));
		bezadr.add(new InstructionCode("ROR",	"00000100"));
		bezadr.add(new InstructionCode("RORC",	"00000101"));
		bezadr.add(new InstructionCode("ASL",	"00000110"));
		bezadr.add(new InstructionCode("LSL",	"00000111"));
		bezadr.add(new InstructionCode("ROL",	"00001000"));
		bezadr.add(new InstructionCode("ROLC",	"00001001"));
		bezadr.add(new InstructionCode("INTE",	"00001010"));
		bezadr.add(new InstructionCode("INTD",	"00001011"));
		bezadr.add(new InstructionCode("TRPE",	"00001100"));
		bezadr.add(new InstructionCode("TRPD",	"00001101"));
		bezadr.add(new InstructionCode("STIVTP","00001110"));
		bezadr.add(new InstructionCode("STSP",	"00001111"));
	}
	
	static LinkedList<InstructionCode> jump1b;
	static{
		jump1b = new LinkedList<>();
		jump1b.add(new InstructionCode("BEQL",	"01000000"));
	}
	
	static LinkedList<InstructionCode> jump2b;
	static{
		jump2b = new LinkedList<>();
		jump2b.add(new InstructionCode("JMP",	"01100000"));
	}
	
	static LinkedList<InstructionCode> adresne;
	static{
		adresne = new LinkedList<>();
		adresne.add(new InstructionCode("LD",	"10000"));
	}
	
	static LinkedList<InstructionCode> adresiranja2;
	static{
		adresiranja2 = new LinkedList<>();
		adresiranja2.add(new InstructionCode("R0",	"000"));
		adresiranja2.add(new InstructionCode("R1",	"001"));
		adresiranja2.add(new InstructionCode("R2",	"010"));
		adresiranja2.add(new InstructionCode("R3",	"011"));
	}
	
	static LinkedList<InstructionCode> adresiranja3_1b;
	static{
		adresiranja3_1b = new LinkedList<>();
		adresiranja3_1b.add(new InstructionCode("BRXR",	"101"));
		adresiranja3_1b.add(new InstructionCode("XR",	"111"));
	}
	
	static LinkedList<InstructionCode> adresiranja3_2b;
	static{
		adresiranja3_2b = new LinkedList<>();
		adresiranja3_2b.add(new InstructionCode("BR",	"110"));
	}
	
	
	private String type;
	private String content;
	
	public Instruction(String content) {
		super();
		this.content = content;
		type = "unknown";
	}
	
	private boolean contains(LinkedList<InstructionCode> list, String s){
		for(InstructionCode ic : list){
			if(ic.getName().equals(s)){
				return true;
			}
		}
		return false;
	}
	
	private String getCode(LinkedList<InstructionCode> list, String name){
		for(InstructionCode ic : list){
			if(ic.getName().equals(name)){
				return ic.getCode();
			}
		}
		return "";
	}
	
	private boolean isHex(String s){
		try{
			Integer.parseInt(s,16);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	private String hexToBin(String hexString, int length){
		int x = Integer.parseInt(hexString,16);
		String ret = "";
		for(int i=0; i<length; i++){
			if(x%2==1) ret = "1" + ret;
			else ret = "0" + ret;
			x=x/2;
		}
		return ret;
	}
	

	private boolean check(){
		boolean found = false;
		String[] data = content.split(" ");
		switch(data.length){
		case 1: found = contains(bezadr,data[0]);
				type = "bezadr";
				break;
		case 2: found = contains(jump1b,data[0]) && isHex(data[1]);
				type = "jump1b";
				if(!found){
					found = contains(jump2b,data[0]) && isHex(data[1]);
					type = "jump2b";
				}
				if(!found){
					found = contains(adresne,data[0]) && isHex(data[1]);
					type = "adresne2i";
				}
				if(!found){
					found = contains(adresne,data[0]) && contains(adresiranja2,data[1]);
					type = "adresne2";
				}
				break;
		case 3: found = contains(adresne,data[0]) && contains(adresiranja3_1b,data[1]) && isHex(data[2]);
				type = "adresne3_1b";
				if(!found){
					found = contains(adresne,data[0]) && contains(adresiranja3_2b,data[1]) && isHex(data[2]);
					type = "adresne3_2b";
				}
				break;		
		}
		return found;
	}
	
	public String decode(){
		if(check()){
			String[] data = content.split(" ");
			String code = "";
			switch(type){
			case "bezadr":	code = getCode(bezadr,content);
							break;
			case "jump1b": code = getCode(jump1b, data[0]) + hexToBin(data[1], 8);
							break;
			case "jump2b": 	code = getCode(jump2b, data[0]) + hexToBin(data[1], 16);
								break;
			case "adresne2i":	code = getCode(adresne, data[0]) + hexToBin(data[1],16);
								break;
			case "adresne2":	code = getCode(adresne, data[0]) + getCode(adresiranja2,data[1]);
								break;
			case "adresne3_1b":	code = getCode(adresne,data[0]) + getCode(adresiranja3_1b,data[1]) +
										hexToBin(data[2], 8);
			case "adresne3_2b":	code = getCode(adresne,data[0]) + getCode(adresiranja3_2b,data[1]) +
										hexToBin(data[2], 16);
			}
			return code;
		}
		return null;
	}
	
	public LinkedList<Integer> decodeToIntList(){
		LinkedList<Integer>	ret = new LinkedList<>();
		String decode = decode();
		for(int i=0; i<decode.length()/8; i++){
			ret.add(Integer.parseInt(decode.substring(0+8*i, 8+8*i)));
		}
		return ret;
	}
	
	public static void main(String[] args) {
		Instruction i = new Instruction("RTS");
		System.out.println(i.content + " " + i.decode());
		i.content = "LD R0";
		System.out.println(i.content + " " + i.decode());
		i.content = "LD R3";
		System.out.println(i.content + " " + i.decode());
		i.content = "LD BR F";
		System.out.println(i.content + " " + i.decode() + " " + i.decodeToIntList());
		i.content = "BEQL 8";
		System.out.println(i.content + " " + i.decode() + " " + i.decodeToIntList());
	}

}
