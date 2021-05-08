import java.util.ArrayList;

public class memory {
	static String[] Memory = new String[2048];
	static int instPosition = 0;
	static boolean instFull = false;
	static int dataPosition = 1024;
	static boolean dataFull = false;
	public static ArrayList<block> cache = new ArrayList();
	public static int hits = 0;
	public static int misses = 0;

	public static void addInstruction(String instruction) {
		// when loading instructions to memory

		if (instruction.length() < 32) {
			System.out.println("Instruction should be 32 bits");
		} else {

			if (instFull == false)

			{
				Memory[instPosition] = instruction;
				instPosition++;

				if (instPosition == 1024) {
					instFull = true;
				}
			} else {
				System.out.println("Memory is full cannot insert in it");
			}
		}
	}

	public static void addData(String data) {
		// when loading data to memory
		String first = data.substring(0, 1);
		// if(data.length()<32)?
//		while (data.length() < 32) {
//			// if data is less than 32 I will add the first bit to the string?
//			data = first + data;
//		}
		if (instFull == false) {
			Memory[dataPosition] = data;
			dataPosition++;

			if (dataPosition == 1024) {
				dataFull = true;
			}
		} else {
			System.out.println("Memory is full cannot insert in it");
		}

	}

	public static void addDataIn(int address, String s) {
		if (instFull == false) {
			Memory[address] = s;
			dataPosition++;

			if (dataPosition == 1024) {
				dataFull = true;
			}
		} else {
			System.out.println("Memory is full cannot insert in it");
		}
	}

	public static void addInstructionIn(int address, String s) {
		if (instFull == false) {
			Memory[address] = s;
			instPosition++;

			if (instPosition == 1023) {
				instFull = true;
			}
		} else {
			System.out.println("Memory is full cannot insert in it");
		}
	}

	public static String missOrhit(int x) {
		if (x > Memory.length || x < 0) {
			return "Address out of bound";
		}
		int index = x % 8;
		int tag = x / 8;

		if (cache.get(index).isValid() == false) {
			misses++;
			cache.get(index).setValid(true);
			cache.get(index).setData(Memory[x]);
			cache.get(index).setTag(tag);
			return "it is a miss";

		}

		else if (cache.get(index).tag != tag) {
			misses++;
			cache.get(index).setValid(true);
			cache.get(index).setData(Memory[x]);
			cache.get(index).setTag(tag);
			return "it is a miss";
		}

		else {
			hits++;
			return "it is a hit , Data is  " + cache.get(index).getData();
		}
	}

	public static String get(int address) {
		return Memory[address];
	}

	public static void set(int address, String x) {
		Memory[address] = x;
	}
}
