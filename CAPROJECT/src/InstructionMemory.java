
public class InstructionMemory {

	public static void AddInstruction(String data) {
		Datapath.memory.addInstruction(data);
	}

	public static String getInstruction(int address) {
		return Datapath.memory.get(address);
	}

	public static void addInstructionAt(int add, String data) {
		Datapath.memory.addInstructionIn(add, data);
	}
}
