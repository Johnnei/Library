package org.johnnei.logging;

import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Formats a log line and timestamps it with the given date formatter
 * @author Johnnei
 *
 */
public class LogFormatter extends Formatter {
	
	/**
	 * The formatter which formats the timestamps for the message
	 */
	private DateFormat dateFormatter; 
	
	public LogFormatter(DateFormat dateFormatter) {
		this.dateFormatter = dateFormatter;
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
		Date resultdate = new Date(System.currentTimeMillis());
		return dateFormatter.format(resultdate);
	}
}
