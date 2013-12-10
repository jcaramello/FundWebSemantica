package common;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.List;

public class CommonHelper {

	
	public static String joinResults(List<String> s) {
	    if (s == null || s.isEmpty()) return "No se encontraron resultados";
	    Iterator<String> iter = s.iterator();
	    String formatResult = "%d) %s \n --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n\n";
	    
	    int i = 1;
	    StringBuilder builder = new StringBuilder(String.format(formatResult, i, iter.next()));
	    while( iter.hasNext() )
	    {
	    	i++;
	        builder.append(String.format(formatResult, i, iter.next()));
	    }
	    return builder.toString();
	}
	
	
	public static String join(List<String> s, String delimiter) {
		return join(s, delimiter, null);
	}
	
	public static String join(List<String> s, String delimiter, String surrounded) {
	    
		if(surrounded == null) surrounded = "";
		if (s == null || s.isEmpty()) return "";
	    Iterator<String> iter = s.iterator();
	    StringBuilder builder = new StringBuilder(surrounded + iter.next() + surrounded);
	    while( iter.hasNext() )
	    {
	    	
	        builder.append(delimiter).append(surrounded).append(iter.next()).append(surrounded);
	    }
	    return builder.toString();
	}
	
}
