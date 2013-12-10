package common;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Application
 * @author jcaramello, nechegoyen
 *
 */
public class Application {
	
	/**
	 * Parameter used for indicate a testing mode operation
	 */
	public static final String TESTING_PARAMETER = "-t";
	
	/**
	 * Parameter used for indicate a verbose mode operation
	 */
	public static final String VERBOSE_PARAMETER = "-v";
	
	/**
	 * Application Name
	 */
	public static String Name;
	
	public static int LimitResults = 100;
	
	/**
	 * False: se lee desde un archivo (operatoria normal); true: se lee desde un String, para testear más cómodamente.	
	 */
	public static boolean isTesting = false;  
				
	/**
	 * 	"True" activa los mensajes de información.
	 */
	public static boolean isVerbose = false; 
	
	/**
	 * Log Type
	 */
	public static LogType logType;	
	
	/**
	 * OutputFile
	 */
	public static String OutputFile;
	
	/**
	 * OutputFile
	 */
	public static String InputFile;
	
	private static FileWriter fstream;
	private static BufferedWriter bufferedWriter;
	private static BufferedReader bufferedReader;
	private static InputStream inputStream; 
    private static InputStreamReader inputStreamReader;
	
	
	/**
	 * Compiled file Extension 
	 */
	public static String CompiledFileExtension = "ceivm";
	
	/**
	 * Initialize global application's variables
	 * @param args
	 */
	public static void Initialize(String args[]){				
		Application.Name = "FundWebSema";			
		Application.logType = LogType.File;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();	
		Logger.log(String.format("%s :: Iniciando Applicacion", dateFormat.format(cal.getTime())));	
	}
	
	public static String[] getEndPoints() throws IOException{
		List<String> endpoints = new ArrayList<String>();		
		String currentDir = System.getProperty("user.dir").replace("\\", System.getProperty("file.separator"));
		File endpointsFile = new File(currentDir + "\\endpoints");	
		if(endpointsFile.exists())
		{
			inputStream = new FileInputStream(endpointsFile);         
			inputStreamReader = new InputStreamReader(inputStream);        
			bufferedReader = new BufferedReader(inputStreamReader);
			
			String currentLine = bufferedReader.readLine();
			while(currentLine != null)
			{
				endpoints.add(currentLine);
				currentLine = bufferedReader.readLine();
			}
			bufferedReader.close();			
		}
		else {
			fstream = new FileWriter(endpointsFile, false);
			bufferedWriter = new BufferedWriter(fstream);
			
			endpoints.add("http://dbpedia.org/sparql");
			endpoints.add("https://www.google.com.ar/");
			for (String endpoint : endpoints) {
				bufferedWriter.write(endpoint);	
				bufferedWriter.newLine();	
			}
			bufferedWriter.close();	
		}		
					
		return endpoints.toArray(new String[endpoints.size()]);
	}
	
	public static void SaveEndpoint(String newEndpoint) throws IOException{
		String currentDir = System.getProperty("user.dir").replace("\\", System.getProperty("file.separator"));
		File endpointsFile = new File(currentDir + "\\endpoints");	
		List<String> endpoints = new ArrayList<String>();		
		
		inputStream = new FileInputStream(endpointsFile);         
		inputStreamReader = new InputStreamReader(inputStream);        
		bufferedReader = new BufferedReader(inputStreamReader);
		
		String currentLine = bufferedReader.readLine();
		while(currentLine != null)
		{			
			
			endpoints.add(currentLine);
			currentLine = bufferedReader.readLine();
		}	
		
		bufferedReader.close();
		
		endpoints.add(newEndpoint);	
		
		fstream = new FileWriter(endpointsFile, false);
		bufferedWriter = new BufferedWriter(fstream);	
		
		for (String endpoint : endpoints) {
			bufferedWriter.write(endpoint);	
			bufferedWriter.newLine();
		}						
		bufferedWriter.close();		
	}
	
	private static boolean isTestingEnabled(String args[]){
		boolean isTesting = false;
		
		for (int i = 1; i < args.length; i++) {
			if(args[i].equals(Application.TESTING_PARAMETER)){
				isTesting = true;				
				break;
			}						
		}
		
		return isTesting;		
	}
	
	private static boolean isVerboseEnabled(String args[]){
		boolean isVerbose = false;
		
		for (int i = 1; i < args.length; i++) {
			if(args[i].equals(Application.VERBOSE_PARAMETER)){
				isVerbose = true;
				break;
			}						
		}
		
		return isVerbose;		
	}
}
