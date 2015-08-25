package org.johnnei.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A Formatter which is used for the {@link Logger} instances to log data to the console.
 * @author Johnnei
 *
 */
public class LoggerUtils {
	
	private static final LogFormatter formatter = new LogFormatter(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss"));
	
	/**
	 * The file handler instance which manages the log output
	 */
	private static FileHandler logHandler;

	/**
	 * Creates the log file with the given name
	 * @param productName
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void createLogFile(String productName) throws SecurityException, IOException {
		SimpleDateFormat logDateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		Date resultdate = new Date(System.currentTimeMillis());
		String dateString = logDateFormatter.format(resultdate);
		logHandler = new FileHandler(String.format("./%s-%s-%%u.log", productName, dateString));
		logHandler.setLevel(Level.FINEST);
		logHandler.setFormatter(formatter);
	}
	
	/**
	 * Creates a logger based on the name.<br/>
	 * The logger will only output if the logged message is at least the given minLevel.<br/>
	 * As second request of the same logger will NOT update the minLevel
	 * @param name The name of the logger
	 * @param minLevel The minimum level to log
	 * @return A logger (possibly re-used) with the given name
	 */
	public static Logger createLogger(String name, Level minLevel) {
		Logger logger = Logger.getLogger(name);
		
		if (logger.getHandlers().length == 0) {
			// This logger has no handlers/formatters, Create the handler
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.FINEST);
			
			// Add console output
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(formatter);
			consoleHandler.setLevel(minLevel);
			logger.addHandler(consoleHandler);
			
			// Add log file output
			if (logHandler != null) {
				logger.addHandler(logHandler);
			}
		}
		return logger;
	}
	
	/**
	 * Updates the minimum logging level of the console logger
	 * @param name
	 * @param newLevel
	 */
	public static void updateLoggerLevel(String name, Level newLevel) {
		Logger logger = Logger.getLogger(name);
		for (Handler handler : logger.getHandlers()) {
			if (!(handler instanceof ConsoleHandler)) {
				continue;
			}
			
			handler.setLevel(newLevel);
		}
	}
}

