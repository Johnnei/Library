package org.johnnei.statemachine;

public class Message {
	
	private long timestamp;
	private Object extraInfo;
	private Enum<?> type;
	
	public Message(Object extraInfo, Enum<?> type) {
		this.extraInfo = extraInfo;
		this.type = type;
		this.timestamp = System.currentTimeMillis();
	}
	
	public Message(Enum<?> type) {
		this(null, type);
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public Object getExtraInfo() {
		return extraInfo;
	}
	
	public Enum<?> getType() {
		return type;
	}

}
