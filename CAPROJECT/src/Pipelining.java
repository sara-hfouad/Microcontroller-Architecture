import java.util.ArrayList;
import java.util.Hashtable;
import java.util.*;

public class Pipelining {
	static ArrayList<String> fetchedInstructions = new ArrayList();
	static ArrayList<String> stages = new ArrayList();

	public static void nextStage() {
		
		System.out.println("Pipelining.nextStage()");
		
		for (int i = 0; i < fetchedInstructions.size() && i >= 0; i++) {
			
			
			
			
			String currStage = stages.get(i);
			switch (currStage) {
			case "Fetch":
				
				System.out.println("case fetch");
				
				String instruction = (String)Datapath.decode.inputs.get("instruction");
				String pc = (String) Datapath.decode.inputs.get("pc");
				InstructionFetch.callNext(instruction, pc);
				
				stages.add(i, "Decode");

	
				Datapath.execute.inputs = new Hashtable();
				Datapath.execute.inputs.put("readData1", InstructionDecode.readData1);
				Datapath.execute.inputs.put("readData2", InstructionDecode.readData2);
				Datapath.execute.inputs.put("opcode", InstructionDecode.opCode);
				Datapath.execute.inputs.put("signExtend", InstructionDecode.immediate);
				Datapath.execute.inputs.put("pc", InstructionDecode.pcounter);
				Datapath.execute.inputs.put("rt", InstructionDecode.rt);
				Datapath.execute.inputs.put("rd", InstructionDecode.rd);
				Datapath.execute.inputs.put("shamt", InstructionDecode.shamt);
				Datapath.execute.inputs.put("signals",InstructionDecode.signals);
				break;
			case "Decode":
				
				System.out.println("case decode");

				String readData1 = (String) Datapath.execute.inputs.get("readData1");
				String readData2 =(String) Datapath.execute.inputs.get("readData2");
				String opcode =(String) Datapath.execute.inputs.get("opcode");
				String signExtend = (String)Datapath.execute.inputs.get("signExtend");
				String pcou =(String) Datapath.execute.inputs.get("pc");
				String rt = (String)Datapath.execute.inputs.get("rt");
				String rd =(String) Datapath.execute.inputs.get("rd");
				String shamt =(String) Datapath.execute.inputs.get("shamt");
				Hashtable<String,String> signals = (Hashtable<String, String>) Datapath.execute.inputs.get("signals");

				InstructionDecode.callNext(readData1, readData2, opcode, signExtend, pcou, rt, rd,shamt,signals);
				stages.add(i, "Execute");
		
				
				
				Datapath.memoryAccess.inputs = new Hashtable();
				Datapath.memoryAccess.inputs.put("ALUResult", Execute.ALUResult);
				Datapath.memoryAccess.inputs.put("ReadData2", readData2);
				Datapath.memoryAccess.inputs.put("signExtend", signExtend);
				Datapath.memoryAccess.inputs.put("zeroFlag", Execute.zeroFlag);
				Datapath.memoryAccess.inputs.put("branchAddress", Execute.branchAddress);
				Datapath.memoryAccess.inputs.put("writeReg", Execute.writeReg);
				Datapath.memoryAccess.inputs.put("wbSig", Execute.wbSignals);
				Datapath.memoryAccess.inputs.put("memSig", Execute.memSignals);
				break;
			case "Execute":
				
				System.out.println("case exec");

				String aluRes = (String)Datapath.memoryAccess.inputs.get("ALUResult");
				String readDat =(String) Datapath.memoryAccess.inputs.get("ReadData2");
				String signEx = (String)Datapath.memoryAccess.inputs.get("signExtend");
				String zeroF = (String)Datapath.memoryAccess.inputs.get("zeroFlag");
				String branchAdd =(String) Datapath.memoryAccess.inputs.get("branchAddress");
				String writeReg =(String) Datapath.memoryAccess.inputs.get("writeReg");
				Hashtable<String,String> wbSig =(Hashtable<String,String>) Datapath.memoryAccess.inputs.get("wbSig");
				Hashtable<String,String> memSig =(Hashtable<String,String>) Datapath.memoryAccess.inputs.get("memSig");
				
			
				Execute.callNext(aluRes, readDat, signEx, zeroF, branchAdd, writeReg, wbSig, memSig);
				stages.add(i, "MemoryAccess");
				Datapath.writeBack.inputs = new Hashtable();
				Datapath.writeBack.inputs.put("ALUResult", aluRes);
				Datapath.writeBack.inputs.put("ReadData", MemoryAccess.readData);
				Datapath.writeBack.inputs.put("writeReg", MemoryAccess.writeRegister);
				Datapath.writeBack.inputs.put("wbSignals", MemoryAccess.wbSignals);
				

				break;
			case "MemoryAccess":
				String aluResult = (String)Datapath.writeBack.inputs.get("ALUResult");
				String ReadData = (String)Datapath.writeBack.inputs.get("ReadData");
				String writeRegister =(String) Datapath.writeBack.inputs.get("writeReg");
				Hashtable<String,String> wbSignals =(Hashtable<String,String>) Datapath.writeBack.inputs.get("wbSignals");
				MemoryAccess.callNext(aluResult, ReadData, writeRegister, wbSignals);
				stages.add(i, "WriteBack");
				fetchedInstructions.remove(i);
				stages.remove(i);
				i--;
				break;
			default:
				break;

			}
			
			System.out.println("stages");
			for (int j = 0; j < stages.size(); j++) {
				System.out.println(stages.get(j));
			}
		}
		
		
	}

	public static void fetchInstructions() {
		for (int i = 0; i < (Datapath.memory.instPosition) + 1; i++) {
			System.out.println("loop "+ i);
			
			String iInt = InstructionFetch.toBinary(i);
			
			
			
			if (fetchedInstructions.size() > 0) {
				nextStage();
			}
			
			
			
			String ins = InstructionFetch.instFetch(iInt);
			Datapath.decode.inputs = new Hashtable<String, Object>();
			Datapath.decode.inputs.put("instruction", ins);
			Datapath.decode.inputs.put("pc", InstructionFetch.pcFetch);
			fetchedInstructions.add(ins);
			stages.add("Fetch");

		}
		// we have to keep calling the nextStage method till all instructions are done
		// with the write back stage
		while (fetchedInstructions.size() > 0) {
			nextStage();
		}
	}
}
