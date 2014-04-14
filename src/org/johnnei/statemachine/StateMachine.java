package org.johnnei.statemachine;

public class StateMachine<T> {
	
	private T object;
	private IState<T> currentState;
	private Class<? extends Enum<?>> messageType;
	
	public StateMachine(T object, Class<? extends Enum<?>> acceptedMessageType) {
		this.object = object;
		messageType = acceptedMessageType;
	}
	
	public StateMachine(T object) {
		this(object, null);
	}
	public void update() {
		if (currentState == null)
			return;
		
		currentState.execute(object);
	}
	
	public void changeState(IState<T> state) {
		if (currentState != null) {
			currentState.exit(object);
		}
		
		state.init(object);
		currentState = state;
	}
	
	public void dispatchMessage(Message message) {
		if (messageType == null) {
			// This statemachine doesn't accept messages
			return;
		}
		if (!message.getType().getClass().getName().equals(messageType.getName())) {
			// Invalid message type
			return;
		}
	
		currentState.onReceiveMessage(object, message);
	}

}
