package endpoints;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution; 
import com.hp.hpl.jena.query.QueryExecutionFactory; 
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution; 
import com.hp.hpl.jena.query.ResultSet;

import common.Logger;

public class EndPoint {

	public static QueryExecution qe;
	
	public static void main(String[] args) {
	    String service = "http://dbpedia.org/sparql";
	    String queryString = "select distinct ?Concept where {[] a ?Concept FILTER regex(?Concept , \"Obama\", \"i\")} LIMIT 100";
	    //queryString =  String.format(queryString, "Obama");
	    
	    try {
	    	Query query = QueryFactory.create(queryString);
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
