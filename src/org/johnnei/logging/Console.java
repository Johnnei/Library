package org.johnnei.logging;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A Formatter which is used for the {@link Logger} instances to log data to the console.
 * @author Johnnei
 * @see {LoggerUtils} Replacement for this class
 */
@Deprecated
public class Console {
	
	/**
	 * Creates or re-uses an existing logger<br>
	 * Calls <code>obtainLogger(String, Level, Formatter)</code> with <code>name</code>, <code>Level.INFO</code>, <code>new ConsoleFormatter()</code>
	 * @param name the name of the logger
	 * @return the logger instance
	 * @see ConsoleFormatter
	 */
	public static Logger obtainLogger(String name) {
		return obtainLogger(name, Level.INFO, new ConsoleFormatter());
	}
	
	/**
	 * Creates or re-uses an existing logger<br>
	 * Calls <code>obtainLogger(String, Level, Formatter)</code> with <code>name</code>, <code>minLevel</code>, <code>new ConsoleFormatter()</code>
	 * @param name the name of the logger
	 * @param minLevel the minimum reporting level
	 * @return the logger instance
	 * @see ConsoleFormatter
	 */
	public static Logger obtainLogger(String name, Level minLevel) {
		return obtainLogger(name, minLevel, new ConsoleFormatter());
	}
	
	/**
	 * Creates or re-uses an existing logger
	 * @param name The name of the logger
	 * @param minLevel the minimum reporting level
	 * @param format the format of the logging output
	 * @return the logger instance
	 */
	public static Logger obtainLogger(String name, Level minLevel, Formatter format) {
		Logger log = Logger.getLogger(name);
		
		Handler[] logHandlers = log.getHandlers();
		log.setUseParentHandlers(false);
		log.setLevel(minLevel);
		if (logHandlers.length == 0) {
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(format);
			consoleHandler.setLevel(minLevel);
			log.addHandler(consoleHandler);
		} else {
			for (Handler handler : logHandlers) {
				if (!(handler instanceof ConsoleHandler)) {
					continue;
				}
				
				handler.setFormatter(format);
				handler.setLevel(minLevel);
			}
		}
		
		return log;
	}
	
	/**
	 * Creates a logger based on the name.<br>
	 * The logger will only output if the logged message is at least the given minLevel.<br>
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
			logger.setLevel(minLevel);
			ConsoleHandler consoleHandler = new ConsoleHandler();
			consoleHandler.setFormatter(new ConsoleFormatter());
			consoleHandler.setLevel(minLevel);
			logger.addHandler(consoleHandler);
		}
		return logger;
	}
	
	/**
	 * Updates the logging level to the given level
	 * @param logName The name of the logger to change
	 * @param minLevel The new reporting level
	 */
	public static void updateLogLevel(String logName, Level minLevel) {
		updateLogLevel(Logger.getLogger(logName), minLevel);
	}
	
	/**
	 * Updates the logging level to the given level
	 * @param log the logger to change
	 * @param minLevel the new logging level
	 */
	public static void updateLogLevel(Logger log, Level minLevel) {
		log.setLevel(minLevel);
		for (Handler handler : log.getHandlers()) {
			handler.setLevel(minLevel);
		}
	}

}

