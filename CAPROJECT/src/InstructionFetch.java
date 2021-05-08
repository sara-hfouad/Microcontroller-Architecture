
public class InstructionFetch {
	public static int ClockCycle=0;
	public static String pcFetch;

	public static String instFetch(String address) {
		// increment pc
		ClockCycle++;
		System.out.println("After clock-cycle: "+ClockCycle);
		System.err.println("FETCH STAGE");
		int x = Integer.parseInt(address, 2);
		int ad = Integer.parseInt(address);
		int pc = ad + 1;
		PC.programCount = toBinary(pc);
		pcFetch = toBinary(pc);
		
		String fetchedInstruction = InstructionMemory.getInstruction(ad);
		System.out.println("Next PC: " + PC.getProgramCount());
		System.out.println("Instruction: " + fetchedInstruction);
		// add to fetched instructions and set its stage
		Pipelining.fetchedInstructions.add(fetchedInstruction);
		Pipelining.stages.add("Fetch");
		// return instruction
		return fetchedInstruction;

	}

	public String ProgCount() {

		return PC.programCount;
	}

	public static String toBinary(int x) {
		return String.format("%32s", Integer.toBinaryString(x)).replaceAll(" ", "0");
	}

	public static void callNext(String instruction, String pc) {
		InstructionDecode.InstDecode(instruction, pc);
	}

}
