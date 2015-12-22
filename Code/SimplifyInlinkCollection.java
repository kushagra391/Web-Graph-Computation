import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class SimplifyInlinkCollection {

	static String file_kv = "C://NEU//inlinks_collection.txt";
	static String file_new = "C://NEU//new_inlinks_collection.txt";
	static HashMap<String, String> stringSet = new HashMap<String, String>();

	public static void main(String[] args) throws IOException {

//		String docContents = new String(Files.readAllBytes(Paths.get(file_kv)));

//		int lineCount = 0;
//		for (String line : docContents.split("\n")) {
//
//			String tokens[] = line.split(" ");
//			for (String token : tokens) {
//
//				String hash = hash(token);
//				stringSet.put(token, hash);
//
//			}
//			lineCount++;
//			System.out.println("Reading: " + lineCount);
//
//		}

		int lineCount = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file_kv))) {
			for (String line; (line = br.readLine()) != null;) {
				// process the line.
				String tokens[] = line.split(" ");
				for (String token : tokens) {

					String hash = hash(token);
					stringSet.put(token, hash);

				}
				lineCount++;
				System.out.println("Reading: " + lineCount);
			}
			// line is not visible here.
		}

		// replace URLs
		String toWrite = new String(Files.readAllBytes(Paths.get(file_kv)));
		lineCount = 0;
		for (String key : stringSet.keySet()) {
			String replacement = stringSet.get(key);
			toWrite = toWrite.replaceAll(key, replacement);

			System.out.println("Replacing: " + lineCount);
		}

		FileWriter writer = new FileWriter(file_new);
		writer.write(toWrite);
		writer.close();

	}

	private static String hash(String token) {
		// TODO Auto-generated method stub
		int code = token.hashCode();
		String hash = "" + code;

		return hash;
	}

}
