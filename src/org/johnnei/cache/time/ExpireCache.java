package org.johnnei.cache.time;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ExpireCache {
	
	private Map<String, ExpireInfo> expireCache;
	
	public ExpireCache() {
		expireCache = new HashMap<>();
	}
	
	/**
	 * Sets an expire time.
	 * @param key The handle to recall this expire
	 * @param duration The amount of milliseconds before this handle has expired
	 */
	public void setExpireTime(String key, long duration) {
		expireCache.put(key, new ExpireInfo(duration));
	}
	
	public boolean containsHandle(String key) {
		return expireCache.containsKey(key);
	}
	
	/**
	 * Checks if the expire time has occurred
	 * @param key the handle to find
	 * @return true if the expire date has passed. false if not.
	 * @throws NoSuchElementException if the key is undefined
	 */
	public boolean didExpire(String key) {
		ExpireInfo info = expireCache.get(key);
		
		if (info == null)
			throw new NoSuchElementException(String.format("Key \"%s\" is undefined in ExpireCache", key));
		
		final long timeDiff = System.currentTimeMillis() - info.cacheTime;
		return timeDiff > info.duration;
	}
	
	private class ExpireInfo {
		public final long cacheTime;
		public final long duration;
		
		public ExpireInfo(long duration) {
			this.cacheTime = System.currentTimeMillis();
			this.duration = duration;
		}
	}

}
