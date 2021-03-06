package org.johnnei.logging;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Used as a simple console logging format.<br>
 * Format: <code>[dd-MM-yyyy HH:mm:ss] [level] [name] Message</code> 
 * @author Johnnei
 *
 */
public class ConsoleFormatter extends Formatter {
	
	public ConsoleFormatter() {
	}
	
	/**
	 * Defines the format of the console output
	 */
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(generateDateTimeStamp());
		sb.append(" [");
		sb.append(record.getLevel());
		sb.append("] [");
		sb.append(record.getLoggerName());
		sb.append("] ");
		sb.append(record.getMessage());
		sb.append(System.lineSeparator());
		
		if (record.getThrown() != null) {
			sb.append("Stacktrace:" + System.lineSeparator());
			for (StackTraceElement stackTrace : record.getThrown().getStackTrace()) {
				sb.append(stackTrace.toString());
				sb.append(System.lineSeparator());
			}
		}
		
		return sb.toString();
	}

	/**
	 * Generates a datetime-stamp based on the current time
	 * @return
	 */
	private String generateDateTimeStamp() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date resultdate = new Date(System.currentTimeMillis());
		return dateFormat.format(resultdate);
	}
}
