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
		
			
		String queryString = "select ?s1 as ?c1, ( bif:search_excerpt ( bif:vector ( %s ) , ?o1 ) ) as ?c2, ?sc, ?rank, ?g where " + 
							  "{ "+ 
							    "{ "+ 
							    "{ "+ 
							        "select ?s1, ( ?sc * 3e-1 ) as ?sc, ?o1, ( sql:rnk_scale ( <LONG::IRI_RANK> ( ?s1 ) ) ) as ?rank, ?g where "+ 
							        "{ "+
							          "quad map virtrdf:DefaultQuadMap "+ 
							          "{ "+
							            "graph ?g "+ 
							            "{ "+ 
							              "?s1 ?s1textp ?o1 . "+
							              "?o1 bif:contains ' ( %s ) ' option ( score ?sc ) . "+				              
							            "} "+
							           "} "+
							         "} "+
							       "order by desc ( ?sc * 3e-1 + sql:rnk_scale ( <LONG::IRI_RANK> ( ?s1 ) ) ) limit %d offset 0 "+ 
							      "} "+
							    "} "+
							  "} ";
	    
		

		//'STEFANO', 'DI', 'ALFREDO'		
		String kw_1 = CommonHelper.join(keywords, ",", "\'");
		//STEFANO AND DI AND ALFREDO
		String kw_2 = CommonHelper.join(keywords, " AND ");
	    queryString =  String.format(queryString, kw_1, kw_2, Application.LimitResults);
	    
	    try {
	    	Query query = QueryFactory.create(queryString);
	    	ARQ.getContext().setTrue(ARQ.useSAX);
	    	qe = QueryExecutionFactory.sparqlService(service, query);
	    	ResultSet resultSet = qe.execSelect();

	    	while(resultSet.hasNext()){

	            QuerySolution sol = (QuerySolution) resultSet.next();	           
	            results.add(sol.get("?c2").toString());	            	          	            	    
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
