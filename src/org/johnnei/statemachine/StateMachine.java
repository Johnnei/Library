package org.johnnei.statemachine;

public class StateMachine<T> {
	
	private T object;
	private IState<T> currentState;
	/**
	 * No idea why we would store this but oh well.
	 */
	@SuppressWarnings("unused")
	private IState<T> previousState;
	
	public StateMachine(T object) {
		this.object = object;
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
		previousState = currentState;
		state.init(object);
		currentState = state;
	}

}
