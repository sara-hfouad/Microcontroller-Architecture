import java.util.Hashtable;

public class WriteBack {

	public static void writeBack(String ALUResult, String ReadData, String writeReg, Hashtable<String, String> wbSig) {
		System.err.println("WRITE BACK STAGE");
		if (wbSig.get("RegWrite").equals("1")) {

			if (wbSig.get("MemToReg").equals("1")) {
				// load word
				Datapath.rf.setDATAReg(ReadData, writeReg);
				System.out.println("WriteData: " + ReadData);
			} else
			// RType
			{
				Datapath.rf.setDATAReg(ALUResult, writeReg);
				System.out.println("WriteData: " + ALUResult);
			}
		}

	}

}
