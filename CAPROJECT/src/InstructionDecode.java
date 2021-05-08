import java.util.Hashtable;

public class InstructionDecode {
	static String rt="";
	static String rd="";
	static String immediate="";
	static String shamt="";
	static String readData1="";
	static String readData2="";
	static String pcounter="";
	static String Joffset="";
	static String mem10Bits="";
	static String opCode="";
	static Hashtable<String,String> signals;
	// sw and lw :
	// 2 bits type 01
	// 4 bit opcode
	// 5 bits rs
	// 5 bits rt
	// 6 bits constant 0
	// 10 bits for address, 1024 should be added

	public static void InstDecode(String instruction, String pc) {
		pcounter = pc;
		System.err.println("DECODE STAGE");
		String Type = instruction.substring(0, 2);
		opCode = instruction.substring(2, 6);
		String rs = instruction.substring(6, 11);
		 readData1 = registerFile.getValue(rs);
		rt = instruction.substring(11, 16);
		 readData2 = registerFile.getValue(rt);
		signals=ContUnit(opCode);

		
		if (Type.equals("00")) {
			shamt = instruction.substring(20, 25);
		}

		if (ControlUnit.RegDst.equals("1")) {

			rd = instruction.substring(16, 21);

		} else {
			rd = "don't care";

		}

		Joffset = SignExtend(instruction.substring(22,32));
		immediate = SignExtend(instruction.substring(22, 32));

		// Print Statements
		System.out.println("read data 1: " + readData1);
		System.out.println("read data 2: " + readData2);
		System.out.println("sign-extend: " + immediate);
		System.out.println("Next Pc: " + pc);
		System.out.println("rt: " + rt);
		System.out.println("rd: " + rd);
		
		
		// Execute.exec(readData1, readData2, ControlUnit.ALUOp, ControlUnit.ALUSrc,
		// immediate, pc);
	}

	public static void callNext(String data1, String data2, String opcode, String imme, String pcCounter,
			String rT, String rD, String shamt,Hashtable<String,String> signals ) {
		Execute.exec(data1, data2, opcode, imme, pcCounter, rT, rD, shamt,signals);
	}

	public static String SignExtend(String immediate) {
		String first = immediate.substring(0, 1);
		while (immediate.length() < 32)
			immediate = first + immediate;
		return immediate;
	}

	public static Hashtable<String,String> ContUnit(String opCode) {
		ControlUnit.ALUOp = opCode;

		switch (opCode) {
		case "0000":// add rs+rt
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0001":// add immediate rs+ imm
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "1";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0010":// subtract rs-rt
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0011":// multiply rs*rt
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0100":// and
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0101":// or immediate
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0110":// shift left logic
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "0111":// shift right logic
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";
			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1000":// load word
			ControlUnit.RegDst = "0";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "1";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "1";
			ControlUnit.MemToReg = "1";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1001":// store word
			ControlUnit.RegDst = "don't care";
			ControlUnit.RegWrite = "0";
			ControlUnit.ALUSrc = "1";
			ControlUnit.MemWrite = "1";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "don't care";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1010":// Branch on not equal.
			ControlUnit.RegDst = "don't care";
			ControlUnit.RegWrite = "0";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "don't care";
			ControlUnit.branch = "1";
			ControlUnit.notFlag = "1";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1011":// Branch on greater than.
			ControlUnit.RegDst = "don't care";
			ControlUnit.RegWrite = "0";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "don't care";
			ControlUnit.branch = "1";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1100":// Set on less than.
			ControlUnit.RegDst = "1";
			ControlUnit.RegWrite = "1";
			ControlUnit.ALUSrc = "0";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "0";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		case "1101":// jump
			ControlUnit.RegDst = "don't care";
			ControlUnit.RegWrite = "0";
			ControlUnit.ALUSrc = "don't care";
			ControlUnit.MemWrite = "0";
			ControlUnit.MemRead = "0";
			ControlUnit.MemToReg = "don't care";
			ControlUnit.branch = "0";
			ControlUnit.jump = "1";

			System.out.println("WB controls: " + "MemToReg: " + ControlUnit.MemToReg + ", " + "MemWrite: "
					+ ControlUnit.MemWrite + "RegWrite: " + ControlUnit.RegWrite);
			System.out.println("MEM controls: " + "MemRead: " + ControlUnit.MemRead + ", MemWrite: "
					+ ControlUnit.MemWrite + "Branch: " + ControlUnit.branch + "Jump: " + ControlUnit.jump);
			System.out.println("EX controls: RegDest: " + ControlUnit.RegDst + ", ALUOp: " + ControlUnit.ALUOp
					+ ", ALUSrc: " + ControlUnit.ALUSrc);
			break;
		default:
			System.out.println("Not a legal opcode");

		}
		
		Hashtable<String,String> signal= new Hashtable<String,String>();
		signal.put("RegDst", ControlUnit.RegDst);
		signal.put("RegWrite", ControlUnit.RegWrite);
		signal.put("ALUSrc", ControlUnit.ALUSrc);
		signal.put("MemRead", ControlUnit.MemRead);
		signal.put("MemWrite", ControlUnit.MemWrite);
		signal.put("MemToReg", ControlUnit.MemToReg);
		signal.put("notFlag", ControlUnit.notFlag);
		signal.put("ALUOp", ControlUnit.ALUOp);
		signal.put("branch", ControlUnit.branch);
		signal.put("jump", ControlUnit.jump);

		


		return signal;
		

	}

	public static String jumpAdd(String pc, String offset) {
		String newPC = "";
		String pcupper = pc.substring(0, 5);
		newPC = pcupper + offset + "00";

		return newPC;
	}

}
