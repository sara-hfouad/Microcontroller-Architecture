
public class registerFile {
	static String[] registers = new String[32];

	public registerFile() {
		
		for (int i = 0; i < registers.length; i++) {
			registers[i] = InstructionFetch.toBinary(0);
			
		}
	}
	
	
	// boolean write = false;
	public static void setDATAReg(String data, String address) {
		int index = Integer.parseInt(address, 2);

		if (index > 31 || index < 0)
			System.out.println("Index is out of bound");
		else {
			registers[index] = data;
		}
	}

	public static String getValue(String address) {
		int decimal = Integer.parseInt(address, 2);
		for (int i = 0; i < registers.length; i++) {
			if (decimal == i)
				return registers[i];

		}
		return "no value";
	}
	// fill the array with registers (name,purpose and value)
//instructions divided into :
	/*
	 * 1)4 bits op code 2)5 bits,5 bits rs ,rt 4)if R type:rd-->5 bits,11 bits are
	 * 0s else:16 bits for offset(load and store)
	 */
//	public void decode(String instruction) {
//		String x = instruction.substring(0, 4);
//		String rs;
//		String rt;
//		String rd;
//		String immediate;
//		String offset;
//		switch (x) {
//		case "0000":// add rs+rt
//			rd = instruction.substring(4, 9);
//			rs = instruction.substring(9, 14);
//			rt = instruction.substring(14, 19);
//			add(rd, rs, rt);
//			break;
//		case "0001":// add immediate rs+ imm
//			rd = instruction.substring(4, 9);
//			rs = instruction.substring(9, 14);
//			immediate = instruction.substring(14, 32);
//			addImmediate(rd, rs, immediate);
//			break;
//		case "0010":// subtract rs-rt
//			rd = instruction.substring(4, 9);
//			rs = instruction.substring(9, 14);
//			rt = instruction.substring(14, 19);
//			sub(rd, rs, rt);
//			break;
//		case "0011":// multiply rs*rt
//			rd = instruction.substring(4, 9);
//			rs = instruction.substring(9, 14);
//			rt = instruction.substring(14, 19);
//			mul(rd, rs, rt);
//			break;
//		case "0100":// and
//			break;
//		case "0101":// or immediate
//			break;
//		case "0110":// shift left logic
//			break;
//		case "0111":// shift right logic
//			break;
//		case "1000":// load word
//			rs = instruction.substring(4, 9);
//			rt = instruction.substring(9, 14);
//			offset = instruction.substring(14, 30);
//			load(rs, rt, offset);
//
//			break;
//		case "1001":// store word
//			rs = instruction.substring(4, 9);
//			rt = instruction.substring(9, 14);
//			offset = instruction.substring(14, 30);
//			store(rs, rt, offset);
//			break;
//		case "1010":// Branch on not equal.
//			break;
//		case "1011":// Branch on greater than.
//			break;
//		case "1100":// Set on less than.
//			break;
//		case "1101":// jump
//			break;
//		default:
//			System.out.println("Invalid reneter please");
//			break;
//		}
//	}
//
//	public String getValue(String address) {
//		int decimal = Integer.parseInt(address, 2);
//		for (int i = 0; i < registers.length; i++) {
//			if (decimal == i)
//				return registers[i];
//
//		}
//		return "no value";
//	}
//
//	public void addValue(String address, String x) {
//		int decimal = Integer.parseInt(address, 2);
//		for (int i = 0; i < registers.length; i++) {
//			if (decimal == i)
//				registers[i] = x;
//
//		}
//
//	}
//
//	public void add(String rd, String rs, String rt) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int y = Integer.parseInt(getValue(rt), 2);
//		String z = Integer.toBinaryString(x + y);
//		addValue(rd, z);
//
//	}
//
//	public void addImmediate(String rd, String rs, String imm) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int y = Integer.parseInt(imm, 2);
//		String z = Integer.toBinaryString(x + y);
//		addValue(rd, z);
//
//	}
//
//	public void sub(String rd, String rs, String rt) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int y = Integer.parseInt(getValue(rt), 2);
//		String z = Integer.toBinaryString(x - y);
//		addValue(rd, z);
//
//	}
//
//	public void mul(String rd, String rs, String rt) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int y = Integer.parseInt(getValue(rt), 2);
//		String z = Integer.toBinaryString(x * y);
//		addValue(rd, z);
//
//	}
//
//	public void load(String rs, String rt, String offset) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int y = Integer.parseInt(getValue(rt), 2);
//		int z = Integer.parseInt(getValue(offset), 2);
//		int memoryAdd = x + z + 1024;
//		String toBeLoaded = memory.get(memoryAdd);
//		registers[y] = toBeLoaded;
//
//	}
//
//	public void store(String rs, String rt, String offset) {
//		int x = Integer.parseInt(getValue(rs), 2);
//		int z = Integer.parseInt(getValue(offset), 2);
//		int memoryAdd = x + z + 1024;
//		String toBeStored = getValue(rt);
//		memory.set(memoryAdd, toBeStored);
//
//	}

}
