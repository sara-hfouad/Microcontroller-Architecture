public class ALU {
	static String zeroFlag = "0";
	static int res = 0;

	public static String ALUEvaluator(String Op, int Operand1, int Operand2, String shamt) {

		switch (Op) {
		case "0100":
			// System.out.println("Operation Name: and");
			res = Operand1 & Operand2;

			break;

		case "0101":
			// System.out.println("Operation: or");
			res = Operand1 | Operand2;

			break;

		case "0000":
		case "0001":
			//add or add immediate
			// System.out.println("Operation: add");

			res = Operand1 + Operand2;
			break;

		case "1000":
		case "1001":
			// lw or sw
			res = 1024 + Operand2;

			break;
		case "0010":
		case "1010":
			// sub or bne
			res = Operand1 - Operand2;

			break;
		case "1011":
			// greater than
			res = Operand1 > Operand2 ? 0 : 1;
			break;

		case "1100": //slt
			// System.out.println("Operation: slt");
			res = Operand1 < Operand2 ? 1 : 0;

			break;

		case "0011":
			// multiply
			res = Operand1 * Operand2;

		case "0110":// shift left logic
			System.out.println("Operation: shift left logic");

			int shamtmul = Integer.parseInt(shamt);
			res = (int) (Operand2 * Math.pow(2, shamtmul));

			break;

		case "0111":// shift right logic
			System.out.println("Operation: shift right logic");
			int shamtdiv =  Integer.parseInt(shamt);
			res = (int) (Operand2 / Math.pow(2, shamtdiv));
			break;

		}

		if (res == 0)
			zeroFlag = "1";

		String resString = Integer.toBinaryString(res);
		while (resString.length() < 32) {
			resString = "0" + resString;
		}
		return resString;

	}

}
