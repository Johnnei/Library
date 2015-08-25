package org.johnnei.threading;

public class ThreadUtils {
	
	/**
	 * Sleeps for the given amount of time. When interrupted this call ends prematurely.
	 * @param ms The amount of time to sleep in milliseconds
	 */
	public static final void sleep(final long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
		}
	}
}
