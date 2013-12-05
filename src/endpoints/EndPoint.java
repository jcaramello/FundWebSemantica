package endpoints;

import com.hp.hpl.jena.query.QueryExecution; 
import com.hp.hpl.jena.query.QueryExecutionFactory; 
import com.hp.hpl.jena.query.QuerySolution; 
import com.hp.hpl.jena.query.ResultSet;
import common.Logger;

public class EndPoint {

	public static QueryExecution qe;
	
	public static void main(String[] args) {
	    String service = "http://dbpedia.org/sparql";
	    String query = "select count(*) where { ?s ?p ?o . FILTER regex(str(?o) , \"^%s\", \"i\"). FILTER langMatches(lang(?o), \"ES\" ). } LIMIT 100";
	    query =  String.format(query, "Obama");
	    
	    try {
	    	qe = QueryExecutionFactory.sparqlService(service, query);
	    	ResultSet results = qe.execSelect();

	    	while(results.hasNext()){

	            QuerySolution sol = (QuerySolution) results.next();
	            System.out.println(sol.get("?o"));

			}	
	      
	    }catch(Exception e){

	        e.printStackTrace();
	        Logger.log(e.getMessage());
	    }
	    finally {

	       qe.close();
	    }
	}
	
}
