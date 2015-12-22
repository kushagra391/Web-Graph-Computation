import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

public class PageRank_Demo_2 {

	final static double d = 0.82;
	static double N;

	public static <K> void main(String[] args) throws IOException {
		
		// intitialize mapper engines
		ParsePageRankDoc.initParser();
		// create extra edges for links that dont have outlinks
		ParsePageRankDoc.createExtraNodes();

		// 0. Count
//		N = 11;
		N = ParsePageRankDoc.getN();

		// 1. PageRank init for every node
		HashMap<String, Double> PR = new HashMap<String, Double>();
		PR = ParsePageRankDoc.getPR();
		printSum(PR);

		// 2. Outdegree for every node
		HashMap<String, Double> out = new HashMap<String, Double>();
		out = ParsePageRankDoc.getOut();

		// 3. Inlinks for every node
		HashMap<String, ArrayList<String>> inlinks_map = new HashMap<String, ArrayList<String>>();
		inlinks_map = ParsePageRankDoc.getInlinks_Map();

		int count = 0;
		// start convergence
		while (true) {
			// iteration begins
			HashMap<String, Double> PR_current = new HashMap<String, Double>();

			for (String key : PR.keySet()) { // term by term calculation 
				
				double newValue;
				ArrayList<String> inlinks = inlinks_map.get(key);
				
				// if no inlinks
				if (inlinks.size() == 0) {
					newValue = (1.0 - d) / N;
					PR_current.put(key, newValue);
				} 
				else { // if inlinks are present

					double sum = 0.0;
					for (String inlink : inlinks) {
						
						sum = sum + (PR.get(inlink) / out.get(inlink));
					}

					newValue = ((1.0 - d) / N) + (d * sum);
					PR_current.put(key, newValue);
				}

			} // end of one iteration and PR updates

			// check for convergence, break away if converged
			if (hasConverged(PR, PR_current)) {
				printSum(PR_current);
				printMap(PR_current);
				
				// Checks
				System.out.println(PR_current.get("WT25-B07-103"));
				System.out.println(PR_current.get("WT14-B20-245"));
				
				
				break;
			}

			// else, update PR with PR_current
			PR = new HashMap<String, Double>(PR_current);
			
			count++;
			System.out.print("Cycle: " + count + " --> ");
			printSum(PR_current);

		}
		
		
		

	}

	private static void printSum(HashMap<String, Double> map) {
		// TODO Auto-generated method stub
		double sum = 0.0;
		for (String key : map.keySet()) {
			double value = map.get(key);
			// System.out.println(key + ": " + value);

			sum = sum + value;
		}

		 System.out.println("Current Sum: " + sum);

	}

	private static boolean hasConverged(HashMap<String, Double> map1,
			HashMap<String, Double> map2) {

		for (String key : map1.keySet()) {

			double value1 = map1.get(key);
			double value2 = map2.get(key);

			// if (Math.abs(value1 - value2) > 0.0000000000000001) {
			if (Math.abs(value1 - value2) > 0.000000000000001) {
				return false;
			}

		}

		return true;
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

	

	

	

	

}
