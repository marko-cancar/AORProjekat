package simulator;

public class Operation {
	
	public static MultipleDigitalValue add(MultipleDigitalValue a, MultipleDigitalValue b){
		MultipleDigitalValue c = new MultipleDigitalValue(a.getSize());
		boolean carry = false;
		for(int i=c.getSize()-1; i>=0; i--){
			boolean ai = a.separateValues().get(i).getBooleanValue();
			boolean bi = b.separateValues().get(i).getBooleanValue();
			c.separateValues().get(i).setBooleanValue(ai^bi^carry);
			carry = ai&bi||bi&carry||ai&carry;
		}
		return c;
	}
	
	public static MultipleDigitalValue sub(MultipleDigitalValue a, MultipleDigitalValue b){
		
		MultipleDigitalValue bcom = new MultipleDigitalValue(b.getSize());
		boolean complementing = false;
		for(int i=bcom.getSize()-1; i>=0; i--){
			if(complementing){
				bcom.separateValues().get(i).setBooleanValue(!b.separateValues().get(i).getBooleanValue());
			}else{
				bcom.separateValues().get(i).setBooleanValue(b.separateValues().get(i).getBooleanValue());
			}
			if(b.separateValues().get(i).getBooleanValue()){
				complementing = true;
			}
		}
		return add(a,bcom);
	}
	
	public static MultipleDigitalValue and(MultipleDigitalValue a, MultipleDigitalValue b){
		MultipleDigitalValue c = new MultipleDigitalValue(a.getSize());
		for(int i=c.getSize()-1; i>=0; i--){
			boolean ai = a.separateValues().get(i).getBooleanValue();
			boolean bi = b.separateValues().get(i).getBooleanValue();
			c.separateValues().get(i).setBooleanValue(ai&&bi);
		}
		return c;
	}
	
	public static MultipleDigitalValue or(MultipleDigitalValue a, MultipleDigitalValue b){
		MultipleDigitalValue c = new MultipleDigitalValue(a.getSize());
		for(int i=c.getSize()-1; i>=0; i--){
			boolean ai = a.separateValues().get(i).getBooleanValue();
			boolean bi = b.separateValues().get(i).getBooleanValue();
			c.separateValues().get(i).setBooleanValue(ai||bi);
		}
		return c;

	}
	
	public static MultipleDigitalValue xor(MultipleDigitalValue a, MultipleDigitalValue b){
		MultipleDigitalValue c = new MultipleDigitalValue(a.getSize());
		for(int i=c.getSize()-1; i>=0; i--){
			boolean ai = a.separateValues().get(i).getBooleanValue();
			boolean bi = b.separateValues().get(i).getBooleanValue();
			c.separateValues().get(i).setBooleanValue(ai^bi);
		}
		return c;

	}
	
	public static MultipleDigitalValue not(MultipleDigitalValue a){
		MultipleDigitalValue c = new MultipleDigitalValue(a.getSize());
		for(int i=c.getSize()-1; i>=0; i--){
			boolean ai = a.separateValues().get(i).getBooleanValue();
			c.separateValues().get(i).setBooleanValue(!ai);
		}
		return c;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MultipleDigitalValue a = new MultipleDigitalValue(3);
		MultipleDigitalValue b = new MultipleDigitalValue(3);
		a.setIntValue(3);
		b.setIntValue(-1);
		System.out.println(a + " + " + b + " = " + add(a,b));
		System.out.println(sub(b,a));

	}

}
