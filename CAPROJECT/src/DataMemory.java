import java.util.ArrayList;

public class DataMemory {
	public static ArrayList<block> cache = new ArrayList();
	public static int hits = 0;
	public static int misses = 0;

	public DataMemory() {
		for (int i = 0; i < 256; i++) {
			block b = new block();
			cache.add(b);
		}
	}

	public static String missOrhit(int x) {
		int temp = x - 1024;
		if (x > 2047 || x < 1024) {
			return "Address out of bound";
		}
		int index = temp % 256;
		int tag = temp / 256;

		if (cache.get(index).isValid() == false) {
			misses++;
			cache.get(index).setValid(true);
			cache.get(index).setData(memory.Memory[x]);
			cache.get(index).setTag(tag);
			return "it is a miss";

		}

		else if (cache.get(index).tag != tag) {
			misses++;
			cache.get(index).setValid(true);
			cache.get(index).setData(memory.Memory[x]);
			cache.get(index).setTag(tag);
			return "it is a miss";
		}

		else {
			hits++;
			return "it is a hit , Data is  " + cache.get(index).getData();
		}
	}

	public static void addData(String data) {
		Datapath.memory.addData(data);
	}

	public static String getData(int add) {
		return Datapath.memory.get(add);
	}

	public static void addDataTo(int add, String data) {
		Datapath.memory.addDataIn(add, data);
	}

}
