package adventofcode;

import java.util.HashSet;
import java.util.Set;

// how to run 
// paste input sequence as a string replace input;
public class Taxicab {

	public static void main(String[] Args) {
		//replace this string to change input 
		String input = "L5, R1, R3, L4, R3, R1, L3, L2, R3, L5, L1, L2, R5, L1, R5, R1, L4, R1, R3, L4, L1, R2, R5, R3, R1, R1, L1, R1, L1, L2, L1, R2, L5, L188, L4, R1, R4, L3, R47, R1, L1, R77, R5, L2, R1, L2, R4, L5, L1, R3, R187, L4, L3, L3, R2, L3, L5, L4, L4, R1, R5, L4, L3, L3, L3, L2, L5, R1, L2, R5, L3, L4, R4, L5, R3, R4, L2, L1, L4, R1, L3, R1, R3, L2, R1, R4, R5, L3, R5, R3, L3, R4, L2, L5, L1, L1, R3, R1, L4, R3, R3, L2, R5, R4, R1, R3, L4, R3, R3, L2, L4, L5, R1, L4, L5, R4, L2, L1, L3, L3, L5, R3, L4, L3, R5, R4, R2, L4, R2, R3, L3, R4, L1, L3, R2, R1, R5, L4, L5, L5, R4, L5, L2, L4, R4, R4, R1, L3, L2, L4, R3";		
		//part one find distance
		System.out.println(getDis(input));
		//part two find first overlap point
		System.out.println(getFirst(input));
	}

	static Integer getDis(String input) {
		String[] inputs = input.split(", ");
		int x = 0;
		int y = 0;
		int direct = 0;

		for (int i = 0; i < inputs.length; i++) {
			char s = inputs[i].charAt(0);
			if (s == 'L')
				direct--;
			else
				direct++;
			if (direct < 0)
				direct = 3;
			if (direct > 3)
				direct = 0;
			int block = Integer.valueOf(inputs[i].substring(1));
			switch (direct) {
			case 0:
				x += block;
				break;
			case 1:
				y += block;
				break;
			case 2:
				x -= block;
				break;
			case 3:
				y -= block;
				break;
			default:
			}
		}

		return Math.abs(x) + Math.abs(y);
	}

	static Integer getFirst(String input) {
		String[] inputs = input.split(", ");
		int x = 0;
		int y = 0;
		int direct = 0;
		Set<String> map = new HashSet<>();
		for (int i = 0; i < inputs.length; i++) {
			char s = inputs[i].charAt(0);
			if (s == 'L')
				direct--;
			else
				direct++;
			if (direct < 0)
				direct = 3;
			if (direct > 3)
				direct = 0;
			int block = Integer.valueOf(inputs[i].substring(1));
			for (int j = 0; j < block; j++) {
				switch (direct) {

				case 0:
					x++;
					break;
				case 1:
					y++;
					break;
				case 2:
					x--;
					break;
				case 3:
					y--;
					break;
				default:
					break;
				}
				String cord = x + "," + y;
				if (map.contains(cord))
					return Math.abs(x) + Math.abs(y);
				else
					map.add(cord);
			}

		}

		return null;
	}
}
