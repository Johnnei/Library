package org.johnnei.utils;

public class Stopwatch {
	
	private long startTime;
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public long getInterval() {
		return System.currentTimeMillis() - startTime;
	}

}
