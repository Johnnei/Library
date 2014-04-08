package org.johnnei.statemachine;

/**
 * A state which the {@link StateMachine} accepts
 * @author Johnnei
 *
 * @param <T>
 */
public interface IState<T> {
	
	public void init(T object);
	
	public void execute(T object);
	
	public void exit(T object);

}
