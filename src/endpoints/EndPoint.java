package endpoints;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hp.hpl.jena.query.ARQ;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution; 
import com.hp.hpl.jena.query.QueryExecutionFactory; 
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution; 
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;

import common.Application;
import common.CommonHelper;
import common.Logger;

public class EndPoint {

	public static QueryExecution qe;
	
	public static List<String> main(List<String> keywords) {
	    
		List<String> results = new ArrayList<String>();
		String service = "http://dbpedia.org/sparql";
	    /*
		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" + 
							 "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> " +
						 	 "SELECT * WHERE " +
							 "{ " +
							 "  ?r <http://dbpedia.org/ontology/abstract> ?o .  " +
							 "   FILTER(LANG(?o)='es' && regex(STR(?o) , \"%s\", \"i\"))" +
							 "}Limit %d";
	    
	    */
		// QUery para dbpedia, la otra es mas ineficiente pero es gral	 
		String queryString = 			
					"select  ?o1 " +
					"where "+ 
					"{ "+ 									
						"{ graph ?g "+
							"{ "+ 
								"?s1 ?s1textp ?o1 . ?o1 <bif:contains> ' ( %s ) '. "+ 
							"} "+ 
						"} "+ 
						"FILTER(LANG(?o1)='%s') "+
					"} Limit %d";
		 
					
		//STEFANO AND DI AND ALFREDO
		String formatedKeywords = CommonHelper.join(keywords, " AND ");
	    queryString =  String.format(queryString, formatedKeywords,Application.LANG, Application.LimitResults);
	    
	    try {
	    	Query query = QueryFactory.create(queryString);	    	
	    	qe = new QueryEngineHTTP(service, query);	    	
	    	ResultSet resultSet = qe.execSelect();

	    	while(resultSet.hasNext()){

	            QuerySolution sol = (QuerySolution) resultSet.next();	           
	            results.add(sol.get("?o1").toString().replaceAll("@"+Application.LANG, ""));	            	          	            	    
			}	
	      
	    }catch(Exception e){

	        e.printStackTrace();
	        Logger.log(e.getMessage());
	    }
	    finally {	       
	       qe.close();
	    }	   	   
	    
	    return results;
	}
	
}
