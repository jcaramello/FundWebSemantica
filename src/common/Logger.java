package common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Logger
 * @author jcaramello, nechegoyen
 *
 */
public class Logger {
	
	
	private static final String NEW_LINE = "\n"; 
	private FileWriter fstream;
	public BufferedWriter bufferedWriter;
	
	/**
	 * Singleton Instance
	 */
	private static Logger current;
	
	/**
	 * Private default constructor
	 */
	private Logger() throws IOException
	{
		if(Application.logType == LogType.File)
		{
			String currentDir = System.getProperty("user.dir").replace("\\", System.getProperty("file.separator"));
			String filePath = Application.Name + ".log";			
			File logFile = new File(filePath);			
			fstream = new FileWriter(logFile, false);
			bufferedWriter = new BufferedWriter(fstream);

		}
	}	
	
	/**
	 * Get a Singleton Instance
	 */
	private static Logger getCurrentLogger(){
		try {
			if(current == null)
				current = new Logger();				
		} catch (IOException e) {
			if(Application.isVerbose){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return current;
	}

	/**
	 * Log a simple message
	 * @param msg
	 */
	public static void log(String msg)
	{
		try {
			if(Application.logType == LogType.Console)
				System.out.println(msg);
			else {								
				getCurrentLogger().bufferedWriter.write(msg);
				getCurrentLogger().bufferedWriter.write(NEW_LINE);
			}
		} catch (IOException e) {
			if(Application.isVerbose){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Log a format message
	 * @param msg
	 */
	public static void log(String format, String... args)
	{
		String msg = String.format(format, args);
		try {
			if(Application.logType == LogType.Console)
				System.out.println(msg);
			else {
				getCurrentLogger().bufferedWriter.write(msg);
				getCurrentLogger().bufferedWriter.write(NEW_LINE);
			}
		} catch (IOException e) {
			if(Application.isVerbose){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Log a simple message only in verbose mode
	 * @param msg
	 */
	public static void verbose(String msg)
	{
		if(Application.isVerbose){
			System.out.println(msg);
		}
	}
	
	/**
	 * Log a format message only in verbose mode
	 * @param msg
	 */
	public static void verbose(String format, String... args)
	{		
		if(Application.isVerbose){
			String msg = String.format(format, args);						
			System.out.println(msg);										
		}
	}
	
	/**
	 * Log a format message only in verbose mode
	 * @param msg
	 */
	public static void verbose(Exception e)
	{		
		Logger.log("Ups!, algo anda mal!");
		if(Application.isVerbose){			
			Logger.verbose(e.getMessage());
			e.printStackTrace();									
		}
	}
	
	/**
	 * Close the buffer writer
	 */
	public static void close(){
		
		if(Application.logType == LogType.File)
			try {
				getCurrentLogger().bufferedWriter.close();
			} catch (IOException e) {
				if(Application.isVerbose){
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
	}
	
}
