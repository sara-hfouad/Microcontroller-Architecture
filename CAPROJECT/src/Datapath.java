
public class Datapath {

	static PC pc = new PC();
	static ControlUnit cu = new ControlUnit();
	static ALU alu = new ALU();
	static registerFile rf = new registerFile();
	static memory memory = new memory();
	static DataMemory dataMemory = new DataMemory();
	static InstructionMemory instructiionMemory = new InstructionMemory();
	static PipelineRegisters decode = new PipelineRegisters();
	static PipelineRegisters execute = new PipelineRegisters();
	static PipelineRegisters memoryAccess = new PipelineRegisters();
	static PipelineRegisters writeBack = new PipelineRegisters();
	// fill data in this class
	
	
	public static void main(String[] args) {
		dataMemory.addDataTo( 0,"11111111111111110000000000000000");
		
		//1-Addi $t0, $0,5
		// 	0010 0000 0000 1000 0000 0000 0000 0101
		
		instructiionMemory.AddInstruction("00"+"0001"+ "00000"+ "01000" + "000000"+"0000000101" );
		
		Pipelining.fetchInstructions();
		
	}
}
