import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.print.attribute.HashAttributeSet;

public class ParsePageRankDoc {

	static String file_kv = "C://NEU//wt2g_inlinks.txt";
//	static String file_kv = "C://NEU//inlinks_collection.txt";

	static HashSet<String> nodes = new HashSet<String>();
	static HashMap<String, ArrayList<String>> inlinks_map = new HashMap<String, ArrayList<String>>();
	static HashMap<String, ArrayList<String>> outlinks_map = new HashMap<String, ArrayList<String>>();

	static double N;
	static HashMap<String, Double> PR = new HashMap<String, Double>();
	static HashMap<String, Double> out = new HashMap<String, Double>();

	public static void main(String[] args) throws IOException {

		initParser();
		createExtraNodes();
		
		
//		
//		N = getN();
//		PR = getPR();
//		out = getOut();
//		
//		
//		System.out.println("Checking for zero outlink nodes.");
//		// update out
//		for (String key : out.keySet()) {
//			double count = out.get(key);
//			if (count == 0.0) {
//				System.out.println(key);
//				double newCount = inlinks_map.get(key).size();
//				out.put(key, newCount);
//			}
//		}
//		System.out.println("Checking for zero outlink nodes.");
			
		
		
		
		
		
		
		// Checks
//		int total_inlinks = 0;
//		int total_outlinks = 0;
//		
//		for (String key : inlinks_map.keySet()) {
//			total_inlinks = total_inlinks + (inlinks_map.get(key).size());
//		}
//		for (String key : outlinks_map.keySet()) {
//			total_outlinks = total_outlinks + (outlinks_map.get(key).size());
//		}
//		
//		System.out.println("Total IN:" + total_inlinks);
//		System.out.println("Total OUT:" + total_outlinks);
//		
//		System.out.println(inlinks_map.get("WT15-B19-115").size());
//		System.out.println(outlinks_map.get("WT15-B19-115").size());
		
		
		// 3. as it is.
	}

	public static void createExtraNodes() {
		System.out.println("Creating extra links");
		
		// add outlinks
		// let. zero outlink = X
		// => update inlinks_map for inlinks of X
		// => update outlinks_map of X
		for (String key : nodes) {
		
			
			double outlink_count;
			outlink_count = outlinks_map.get(key).size();
			
			if (outlink_count == 0.0) {
				// get the list of inlinks for X
				ArrayList<String> inlinks = inlinks_map.get(key);
				ArrayList<String> outlinks = outlinks_map.get(key);
				
				// for each inlink Z of X, create a link from X -> Z
				// => add Z to X's outlink
				// => add X to X's inlink
				for (String Z : inlinks) {
					
					// 1. add "Z" to outlinks of X.
					outlinks.add(Z);
					
					// 2. update inlinks of Z. 
					ArrayList<String> inlinks_of_Z = inlinks_map.get(Z);
					inlinks_of_Z.add(key);
					
				}
			}
		}
			
			
		System.out.println("Created extra links");
		
	}

	public static void initParser() throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Start..");
		String docContents = new String(Files.readAllBytes(Paths.get(file_kv)));

		// 1. Prepare HashSet of nodes
		for (String line : docContents.split("\n")) {
			String tokens[] = line.split(" ");
			String node = tokens[0];
			nodes.add(node);

			ArrayList<String> outlinks = new ArrayList<String>();
			outlinks_map.put(node, outlinks);
		}
		System.out.println("1. nodes hashset prepared. ");

		// 2. Prepare Inlinks
		for (String line : docContents.split("\n")) {
			String tokens[] = line.split(" ");
			String node = tokens[0];

			ArrayList<String> inlinks = new ArrayList<String>();
			// if no inlinks
			if (tokens.length == 1) {
				inlinks_map.put(node, inlinks);

			} else { // if inlinks exist
				for (int j = 1; j < tokens.length; j++) {
					String inlink = tokens[j];
					inlinks.add(inlink);

					// update outlink as well
					String outlink = node;
					String vertex = inlink;
					update_outlinks(vertex, outlink);

				}
				inlinks_map.put(node, inlinks);
			}
		}
		System.out.println("2. inlinks hashmap prepared. ");
		System.out.println("3. outlinks hashmap prepared. ");

		System.out.println("Done !");
	}

	private static void update_outlinks(String vertex, String outlink) {

		ArrayList<String> outlinks = outlinks_map.get(vertex);
		outlinks.add(outlink);

		outlinks_map.put(vertex, outlinks);

	}

	public static double getN() {

		// 0.
		N = nodes.size();
		System.out.println(N);

		return (double)N;
	}

	public static HashMap<String, Double> getPR() {

		// 1.
		HashMap<String, Double> PR = new HashMap<String, Double>();
		for (String node : nodes) {
			double value = 1.0 / N;
			PR.put(node, value);
		}

		return PR;
	}

	public static HashMap<String, Double> getOut() {
		// 2.
		HashMap<String, Double> out = new HashMap<String, Double>();
		for (String key : outlinks_map.keySet()) {
			double outDegree = outlinks_map.get(key).size();
			out.put(key, outDegree);
		}

		return out;
	}

	public static HashMap<String, ArrayList<String>> getInlinks_Map() {
		
		return inlinks_map;
	}

}
