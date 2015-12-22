import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class qrel_check {
	
	
//	static String file_kv = "C://NEU//qrel_bk.txt";
//	static String file_sagar = "C://NEU//qrel_kv.txt";
	static String file_kv = "D://A.txt";
	static String file_sagar = "D://B.txt";
//	static String file_kv = "D://inlinks_collection.txt";
//	static String file_sagar = "D://inlinks_collection.txt";
	
	public static void main(String[] args) throws IOException {
		
		String kv = new String(Files.readAllBytes(Paths.get(file_kv)));;
		String sagar = new String(Files.readAllBytes(Paths.get(file_sagar)));; 
		
		String[] K = kv.split("\n");
		String[] S = sagar.split("\n");
		
		System.out.println(K.length);
		System.out.println(S.length);
		System.out.println(S[2].split(" ")[2]);
		
		for (int i=0; i<K.length; i++) {
			
			String line1 = K[i]; 
			String line2 = S[i];
			
			String web1 = line1.split(" ")[2];
			String web2 = line2.split(" ")[2];
			
			if (!web1.equals(web2)) {
				System.out.println((i+1) + "-->" + web1 + " / " + web2);
				
			}
			
			
			
			
		}
		
		
		
	}

}
