package org.johnnei.threading;

public class ThreadUtils {
	
	public static final void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}

}
