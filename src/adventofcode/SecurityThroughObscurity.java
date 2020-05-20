package adventofcode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// how to run place the input in to the "input.txt" file
public class SecurityThroughObscurity {

	public static void main(String[] args) {
		// part one calculate sum of the sector IDs
		try {
			System.out.println("sum of total real room id :" + totalId("input.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// part two sector ID of the room where North Pole objects
		try {
			System.out.print("the id for the room with north pole :" + findNorthStar("input.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int totalId(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		int result = 0;
		while (line != null) {
			result += roomId(line);
			line = br.readLine();
		}
		return result;
	}

	public static int roomId(String name) {
		Map<Character, Integer> map = new HashMap<>();
		int index = name.indexOf("[");
		int spliter = name.lastIndexOf("-");
		String code = name.substring(0, spliter);
		for (int i = 0; i < code.length(); i++) {
			char letter = code.charAt(i);
			if (Character.isAlphabetic(letter)) {
				if (map.containsKey(letter))
					map.put(letter, map.get(letter) + 1);
				else
					map.put(letter, 1);
			}
		}
		List<Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
		list.sort((x, y) -> x.getValue() != y.getValue() ? y.getValue() - x.getValue()
				: x.getKey().compareTo(y.getKey()));
		String checksum = name.substring(index + 1, name.length() - 2);
		for (int i = 0; i < checksum.length(); i++) {
			if (checksum.charAt(i) != list.get(i).getKey())
				return 0;
		}
		int id = Integer.valueOf(name.substring(spliter + 1, index));
		return id;
	}

	public static String deCode(String name) {
		int index = name.indexOf("[");
		int spliter = name.lastIndexOf("-");
		char[] code = name.substring(0, spliter).toCharArray();
		int id = Integer.valueOf(name.substring(spliter + 1, index));
		int shift = id % 26;
		for (int i = 0; i < code.length; i++) {
			if (Character.isAlphabetic(code[i])) {
				int pos = code[i] - 'a';
				pos += shift;
				pos = pos % 26;
				code[i] = (char) ('a' + pos);
			}
		}
		return new String(code);
	}

	public static int findNorthStar(String fileName) throws IOException {
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			String decoded = deCode(line);
			if (decoded.contains("northpole")) {
				int index = line.indexOf("[");
				int spliter = line.lastIndexOf("-");
				int id = Integer.valueOf(line.substring(spliter + 1, index));
				return id;
			}
			line = br.readLine();

		}
		return 0;
	}
}
