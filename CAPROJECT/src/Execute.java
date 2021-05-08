import java.util.Hashtable;

public class Execute {

	static String writeReg="";
	static String ALUResult="";
	static String zeroFlag="";
	static String branchAddress="";
	static String jumpAddress="";
	static Hashtable<String,String> wbSignals;
	static Hashtable<String,String> memSignals;



	public static void exec(String readData1, String readData2, String opcode, String signExtend,
			String pc, String rt, String rd, String shamt,Hashtable<String,String> signals) {
		// calculate branchaddress
		System.err.println("EXECUTE STAGE");
		int op1 = Integer.parseInt(readData1, 2);
		int op2 = Integer.parseInt(readData2, 2);
		int immediate = Integer.parseInt(signExtend, 2);

		int shiftInt = Integer.parseInt(signExtend, 2);
		int pcInt = Integer.parseInt(pc, 2);
		jumpAddress = InstructionDecode.Joffset;
		branchAddress = ALU.ALUEvaluator("0010", shiftInt, pcInt,shamt);
		while (branchAddress.length() < 32) {
			branchAddress = "0" + branchAddress;
		}
		
		
		wbSignals= new Hashtable<String, String>();
		wbSignals.put("MemToReg", signals.get("MemToReg"));	
		wbSignals.put("RegWrite", signals.get("RegWrite"));
	
		
		memSignals= new Hashtable<String, String>();
		memSignals.put("MemRead", signals.get("MemRead"));	
		memSignals.put("MemWrite", signals.get("MemWrite"));
		memSignals.put("branch", signals.get("branch"));


		// call alu evaluator method
	//	String ALUInput = "";

//		switch (ALUOp) {
//		case "000":// and
//			ALUInput = "0000";
//			break;
//		case "001":// or
//			ALUInput = "0001";
//			break;
//		case "010":// add
//			ALUInput = "0010";
//			break;
//		case "011":// bgt
//			ALUInput = "0011";
//
//		case "100":// sw or lw
//			ALUInput = "0100";
//			// op2=1024;
//		case "110":// sub
//			ALUInput = "0110";
//			break;
//		case "111":// slt
//			ALUInput = "0111";
//			break;
//		case "101":// // shift logic
//
//			if (ControlUnit.rightShift.equals("1")) {
//				ALUInput = "0101";
//
//			} else
//				ALUInput = "1101";
//			break;
//		default:
//			System.out.println("Cannot be evaluated");
//			break;
//		}

		switch (signals.get("ALUSrc")) {
		

		case "0":
			// second input is readdata2
			System.err.println(signals.get("ALUSrc"));

				ALUResult = ALU.ALUEvaluator(opcode, op1, op2,shamt);
			
			break;
		case "1":
			// int value1 = Integer.parseInt(ALU.ALUEvaluator(ALUInput, op1, op2), 2);
System.err.println("here");
			ALUResult = ALU.ALUEvaluator(opcode, op1, immediate,shamt);
			break;
		default: 		System.err.println(signals.get("ALUSrc"));
	
		}

		switch (signals.get("RegDst")) {
		case "0":
			writeReg = rt;
			break;
		case "1":
			writeReg = rd;
			break;

		}

		zeroFlag = ALU.zeroFlag;
		// in case of bne
		if (signals.get("notFlag").equals("1")) {
			if (zeroFlag.equals("1"))
				zeroFlag = "0";
			else {
				zeroFlag = "1";
			}
		}
//		.zero flag: 1
//		branch address: 0000 0000 0000 0001 1110 0000 1010 0100
//		ALU result/address: 0000 0000 0000 0000 0000 0000 0000 0000
//		register value to write to memory: 0000 0000 0000 0000 0000 0000 0000 0000
//		rt/rd register: 01111
//		WB controls: MemToReg: 1, RegWrite: 1
//		MEM controls: MemRead: 0, MemWrite: 0, Branch: 0
		System.out.println("zero flag: " + zeroFlag);
		System.out.println("branch address: " + branchAddress);
		System.out.println("ALU result/address: " + ALUResult);
		System.out.println("register value to write to memory: ");// ?
		System.out.println("rt/rd register: " + writeReg);
		System.out.println("WB controls: " + "MemToReg: " + signals.get("MemToReg") + ", " + "RegWrite: " + signals.get("RegWrite") );
		System.out.println("MEM controls: " + "MemRead: " + signals.get("MemRead") + ", MemWrite: " +  signals.get("MemWrite")
				+ "Branch: " + signals.get("branch") );

		// Call MemoryAccess
		// MemoryAccess.MemAccess(ALUResult, readData2, signExtend, zeroFlag,
		// branchAddress);
		// Check if we have to call writeBack

		// call writeBack
		// WriteBack.writeBack(ALUOp, MemoryAccess.readData, ControlUnit.MemToReg,
		// ControlUnit.RegDst);

	}

	public static void callNext(String aluRes, String readDat, String signEx, String zeroF, String branchAdd, String writeReg, Hashtable<String,String> wbSig
			,  Hashtable<String,String> memSig) {
		MemoryAccess.MemAccess(aluRes, readDat, signEx, zeroF, branchAdd,jumpAddress ,writeReg, wbSig, memSig);
	}

}
