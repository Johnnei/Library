package org.johnnei.structs;

public class SimpleDisjointSets implements IDisjointSet {
	
	/**
	 * The up tree containing all the elements which are in all sets
	 */
	private int[] upTree;
	
	/**
	 * Creates a new DisjointSets with the size given size.<br/>
	 * This will be able to store the values 0 - (n - 1)
	 * @param size
	 */
	public SimpleDisjointSets(int n) {
		upTree = new int[n];
		for (int i = 0; i < n; i++) {
			upTree[i] = -1;
		}
	}
	
	/**
	 * Returns the name of the set in which given item is present
	 * @param item The item to look for
	 * @return The name of the set in which the item has been found
	 * @throws IndexOutOfBoundsException if the item is smaller than 0 or larger or equal to the size of the disjointset total length
	 */
	public int find(int item) {
		if (item < 0 || item >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", item, upTree.length));
		
		int parent = upTree[item];
		
		if (parent < 0)
			return parent;
		
		return find(parent);
	}

	@Override
	public void union(int setNameA, int setNameB) {
		if (setNameA < 0 || setNameA >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameA, upTree.length));
		if (setNameB < 0 || setNameB >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameB, upTree.length));
		
		upTree[setNameA] = setNameB;
	}

}
