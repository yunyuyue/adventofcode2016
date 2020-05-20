package adventofcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// how to run: place the input into the "q10input.txt" file
public class BalanceBots {

	static class Bot {
		int num;
		Bot low;
		Bot high;
		List<Integer> values = new ArrayList<>();
	}

	static Map<Integer, Bot> botsMap;
	static Map<Integer, Bot> valMap;
	static Map<Integer, Bot> outPutMap;
	static int botNum = -1;

	public static void main(String[] args) throws IOException {
		botsMap = new HashMap<Integer, Bot>();
		valMap = new HashMap<Integer, Bot>();
		outPutMap = new HashMap<Integer, Bot>();
		readInput();

		// part one find the bot comparing value-61 with value-17,
		// change the two int here to find the bot that compare any two chips
		System.out.println("the bot is " + findBot(61, 17));
		// part two get the multiply from outputs 0, 1, and 2
		System.out.println("total multiply is " + findMultiplyValue());
	}

	public static int findBot(int v1, int v2) {
		Iterator<Entry<Integer, Bot>> iterator = valMap.entrySet().iterator();

		while (iterator.hasNext()) {
			Entry entry = iterator.next();
			int val = (int) entry.getKey();
			Bot b = (Bot) entry.getValue();
			b.values.add(val);
			findBotHelper(v1, v2, b);
		}
		return botNum;
	}

	public static int findMultiplyValue() {
		return outPutMap.get(0).values.get(0) * outPutMap.get(1).values.get(0) * outPutMap.get(2).values.get(0);
	}

	public static void findBotHelper(int v1, int v2, Bot b) {
		if (b.values.size() == 2) {
			if (b.values.get(0).equals(v1) && b.values.get(1).equals(v2)
					|| (b.values.get(1).equals(v1) && b.values.get(0).equals(v2)))
				botNum = b.num;

			int min, max;
			if (b.values.get(0) > b.values.get(1)) {
				min = b.values.get(1);
				max = b.values.get(0);
			} else {
				min = b.values.get(0);
				max = b.values.get(1);
			}
			if (b.low != null) {
				b.low.values.add(min);
				System.out.println("bot " + b.num + " send chip " + min + " to bot " + b.low.num);
				findBotHelper(v1, v2, b.low);

			}
			if (b.high != null) {
				b.high.values.add(max);
				System.out.println("bot " + b.num + " send chip " + max + " to bot " + b.high.num);
				findBotHelper(v1, v2, b.high);
			}

		}

	}

	public static void readInput() throws IOException {
		FileReader fr = new FileReader("q10input.txt");
		BufferedReader br = new BufferedReader(fr);
		String line = br.readLine();
		while (line != null) {
			if (line.charAt(0) == 'b') {
				String[] words = line.split(" ");
				int num = Integer.valueOf(words[1]);
				int low = Integer.valueOf(words[6]);
				int high = Integer.valueOf(words[11]);
				if (!botsMap.containsKey(num)) {
					Bot b = new Bot();
					b.num = num;
					botsMap.put(num, b);
				}
				if (!words[5].equals("output")) {
					if (!botsMap.containsKey(low)) {
						Bot b = new Bot();
						b.num = low;
						botsMap.put(low, b);
					}
					botsMap.get(num).low = botsMap.get(low);
				} else {
					if (!outPutMap.containsKey(low)) {
						Bot b = new Bot();
						b.num = low;
						outPutMap.put(low, b);
					}
					botsMap.get(num).low = outPutMap.get(low);
				}
				if (!words[10].equals("output")) {
					if (!botsMap.containsKey(high)) {
						Bot b = new Bot();
						b.num = high;
						botsMap.put(high, b);
					}
					botsMap.get(num).high = botsMap.get(high);
				} else {
					if (!outPutMap.containsKey(high)) {
						Bot b = new Bot();
						b.num = high;
						outPutMap.put(high, b);
					}
					botsMap.get(num).high = outPutMap.get(high);
				}
			} else if (line.charAt(0) == 'v') {
				String[] words = line.split(" ");
				int num = Integer.valueOf(words[5]);
				int val = Integer.valueOf(words[1]);
				if (!botsMap.containsKey(num)) {
					Bot b = new Bot();
					b.num = num;
					botsMap.put(num, b);
				}
				valMap.put(val, botsMap.get(num));
			}
			line = br.readLine();
		}
	}

}
