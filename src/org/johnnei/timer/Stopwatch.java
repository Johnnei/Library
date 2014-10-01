package org.johnnei.timer;

public class Stopwatch {
	
	private long startTime;
	
	public void start() {
		startTime = System.currentTimeMillis();
	}
	
	public long getInterval() {
		return System.currentTimeMillis() - startTime;
	}

}
