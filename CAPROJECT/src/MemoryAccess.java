import java.util.Hashtable;

public class MemoryAccess {
	static String readData = "don't care";
	static String writeRegister = "";
	static Hashtable<String,String> wbSignals;
	
	

	public static void MemAccess(String aluResult, String readData2, String signExtend, String zeroFlag, String branchAddressRes,  String jumpAddressRes,String writeReg, Hashtable<String,String> wbSig
			,  Hashtable<String,String> memSig) {
		System.err.println("MEMORY ACCESS STAGE");
		if (memSig.get("MemRead").equals("1")) {
			readData = DataMemory.getData(Integer.parseInt(aluResult, 2));
		}

		if (memSig.get("MemWrite").equals("1")) {
			DataMemory.addDataTo(Integer.parseInt(signExtend, 2), readData2);
			DataMemory.missOrhit(Integer.parseInt(signExtend, 2));
		}

		if (memSig.get("branch").equals("1") && zeroFlag.equals("1")) {
			PC.setProgramCount(branchAddressRes);
		}
		
		if(memSig.get("jump").equals("1")){
			PC.setProgramCount(jumpAddressRes);

		}
		wbSignals= wbSig;
		
		writeRegister=writeReg;

		System.out.println("In Memory Stage");
		System.out.println("memory word read: " + readData);
		System.out.println("rt/rd field: " + readData);
		System.out.println("WB controls: MemToReg:" + wbSig.get("MemToReg") + "RegWrite " +  wbSig.get("RegWrite"));

	}

	public static void callNext(String ALUResult, String ReadData,String writeReg, Hashtable<String,String> wbSig ) {
		WriteBack.writeBack(ALUResult, ReadData,writeReg, wbSig);

	}
}
