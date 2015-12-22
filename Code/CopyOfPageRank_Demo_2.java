import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CopyOfPageRank_Demo_2 {

	final static double d = 0.82;
	static int N;

	public static <K> void main(String[] args) throws IOException {
		
//		ParsePageRankDoc.initParser();
		
		// 0. Count
		N = 11;
//		N = ParsePageRankDoc.getN();
		
		// 1. PageRank init for every node
		HashMap<String, Double> PR = new HashMap<String, Double>();
		pi_map_init(PR);
//		PR = ParsePageRankDoc.getPR();

		// 2. Outdegree for every node
		HashMap<String, Double> out = new HashMap<String, Double>();
		outdegreeMap_init(out);
//		printMap(out);
//		out = ParsePageRankDoc.getOut();

		// 3. Inlinks for every node
		HashMap<String, ArrayList<String>> inlinks_map = new HashMap<String, ArrayList<String>>();
		inlinks_map_init(inlinks_map);
//		inlinks_map = ParsePageRankDoc.getInlinks_Map();
		
		
		int count = 0;
		// start convergence
		while (true) {
			HashMap<String, Double> PR_current = new HashMap<String, Double>();
			
			double total_sum = 0.0;
			for (String key : PR.keySet()) {
				
				double newValue;
				ArrayList<String> inlinks = inlinks_map.get(key);
				if (inlinks.size() == 0) {
					newValue = (1 - d) / N;
					total_sum += newValue;
					
					PR_current.put(key, newValue);
				} else {
					// calculating PR sum of all inlinks
					double sum = 0.0;
					for (String inlink : inlinks) {
						sum = sum + (PR.get(inlink) / out.get(inlink));
					}

					newValue = (1 - d) / N + (d*sum);
					total_sum += newValue;
					PR_current.put(key, newValue);
				}

			}  // end of one iteration and PR updates
			System.out.println("Total: " + total_sum);
			
			// check for convergence, break away if converged
			if (hasConverged(PR, PR_current)) {
				printMap(PR_current);
				break;
			}

			// else, update PR with PR_current
			PR = new HashMap<String, Double>(PR_current);
			count++;
			System.out.println("Cycle: " + count);
			
			

		}

	}

	private static boolean hasConverged(HashMap<String, Double> map1,
			HashMap<String, Double> map2) {
		
		for (String key : map1.keySet()) {
			
			double value1 = map1.get(key);
			double value2 = map2.get(key);
			
//			if (Math.abs(value1 - value2) > 0.0000000000000001) {
			if (Math.abs(value1 - value2) > 0.0000000001) {
				return false;
			}
			
		}
		
		return true;
	}

	private static void inlinks_map_init(
			HashMap<String, ArrayList<String>> inlinks_map) {

		// A
		ArrayList<String> inlinks_a = new ArrayList<String>();
		inlinks_a.add("D");
		inlinks_map.put("A", inlinks_a);

		// B
		ArrayList<String> inlinks_b = new ArrayList<String>();
		inlinks_b.add("D");
		inlinks_b.add("G");
		inlinks_b.add("H");
		inlinks_b.add("I");
		inlinks_b.add("C");
		inlinks_map.put("B", inlinks_b);

		// C
		ArrayList<String> inlinks_c = new ArrayList<String>();
		inlinks_c.add("B");
		inlinks_map.put("C", inlinks_c);

		// D
		ArrayList<String> inlinks_d = new ArrayList<String>();
		inlinks_map.put("D", inlinks_d);

		// E
		ArrayList<String> inlinks_e = new ArrayList<String>();
		inlinks_e.add("G");
		inlinks_e.add("H");
		inlinks_e.add("I");
		inlinks_e.add("J");
		inlinks_e.add("K");
		inlinks_e.add("F");
		inlinks_map.put("E", inlinks_e);

		// F
		ArrayList<String> inlinks_f = new ArrayList<String>();
		inlinks_f.add("E");
		inlinks_map.put("F", inlinks_f);

		// G
		ArrayList<String> inlinks_g = new ArrayList<String>();
		inlinks_map.put("G", inlinks_g);

		// H
		ArrayList<String> inlinks_h = new ArrayList<String>();
		inlinks_map.put("H", inlinks_h);

		// I
		ArrayList<String> inlinks_i = new ArrayList<String>();
		inlinks_map.put("I", inlinks_i);

		// J
		ArrayList<String> inlinks_j = new ArrayList<String>();
		inlinks_map.put("J", inlinks_j);

		// K
		ArrayList<String> inlinks_k = new ArrayList<String>();
		inlinks_map.put("K", inlinks_k);

	}

	private static void printMap(HashMap<String, Double> map) {
		
		double sum = 0.0;
		for (String key : map.keySet()) {
			double value = map.get(key);
			System.out.println(key + ": " + value);
			
			sum = sum + value;
		}
		
		System.out.println("Sum: " + sum);

	}

	private static void pi_map_init(HashMap<String, Double> map) {

		double value = 1.0 / N;

		map.put("A", value);
		map.put("B", value);
		map.put("C", value);
		map.put("D", value);
		map.put("E", value);
		map.put("F", value);
		map.put("G", value);
		map.put("H", value);
		map.put("I", value);
		map.put("J", value);
		map.put("K", value);

	}

	private static void outdegreeMap_init(HashMap<String, Double> map) {

		map.put("A", 0.0);
		map.put("B", 1.0);
		map.put("C", 1.0);
		map.put("D", 2.0);
		map.put("E", 1.0);
		map.put("F", 1.0);
		map.put("G", 2.0);
		map.put("H", 2.0);
		map.put("I", 2.0);
		map.put("J", 1.0);
		map.put("K", 1.0);

	}

	private static void pie_init(double[] P) {

		for (int i = 0; i < N; i++) {
			P[i] = 1.0 / N;
		}

	}

	private static void printVector(double[] V) {
		// TODO Auto-generated method stub

//		int n = V.length;
//		for (int i = 0; i < n; i++) {
//			System.out.print(V[i] + " ");
//		}
//
//		System.out.println();

	}

}
