import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.elasticsearch.search.SearchHit;


public class InlinksParser {

	
	public static final String TEAM_INDEX = "crawled_team";
	public static Client client;

	static String inlinksFilePath = "D://inlinks_collection.txt";
	
	
	
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		
		Node node = NodeBuilder.nodeBuilder().clusterName("informationcluster").node();
		client = node.client();
		
		System.out.println("start");
		searchAndMergeIndex();
		System.out.println("complete");
		client.close();
		node.close();
		
	}
	
	
	
	
	public static void searchAndMergeIndex() throws IOException, InterruptedException, ExecutionException{ 
		// Helpers.INDEX_NAME is your own local index ( not the team one )
		// Scroll size has been set to 100, since setting to 1000 or more leads to 
		// java heap out of memory error 
		SearchResponse searchResp = client.prepareSearch(TEAM_INDEX).setTypes("document")
				.setScroll(new TimeValue(2000000))
				.setQuery(QueryBuilders.matchAllQuery()).setExplain(true)
				.setSize(100).execute().actionGet();
		String inlinksFromTeamIndex = "";
		int i=1;
		
		int count = 1;
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(inlinksFilePath, true)));
		while (true) {
			for (SearchHit hit : searchResp.getHits().getHits()) {
				
				String _id;
				String _inlinks;
				
				_id = hit.getSource().get("docno").toString();
				_inlinks = hit.getSource().get("inlinks").toString();
				
				System.out.println(count + ":" + _id);
				count++;
			
				// FILE I/O
				String insertString = _id + " " + _inlinks;
				out.println(insertString);
				
			}

			searchResp = client.prepareSearchScroll(searchResp.getScrollId())
					.setScroll(new TimeValue(2000000)).execute().actionGet();
			
			if (searchResp.getHits().getHits().length == 0) {
				break;
			} 
//			break;
		}
		
		out.close();
	}
	
}
