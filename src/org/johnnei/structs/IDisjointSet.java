package org.johnnei.structs;

public interface IDisjointSet<E> {
	
	/**
	 * Returns the name of the set in which given item is present
	 * @param item The item to look for
	 * @return The name of the set in which the item has been found
	 * @throws IndexOutOfBoundsException if the item is smaller than 0 or larger or equal to the size of the disjointset total length
	 */
	public E find(E item);
	
	/**
	 * Unions the two given sets
	 * @param setNameA
	 * @param setNameB
	 * @throws IndexOutOfBoundsException if either <code>setNameA</code> or <code>setNameB</code> is smaller than 0 or larger or equal to the size of the disjointset total length
	 */
	public void union(E setNameA, E setNameB);
	
	/**
	 * The amount of sets in this disjointset
	 * @return
	 */
	public int setCount();

}
