package org.johnnei.structs;

public class SimpleDisjointSets implements IDisjointSet<Integer> {
	
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
	
	public Integer find(Integer item) {
		if (item < 0 || item >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", item, upTree.length));
		
		int parent = upTree[item];
		
		if (parent < 0)
			return item;
		
		return find(parent);
	}

	@Override
	public void union(Integer setNameA, Integer setNameB) {
		if (setNameA < 0 || setNameA >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameA, upTree.length));
		if (setNameB < 0 || setNameB >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameB, upTree.length));
		
		upTree[setNameA] = setNameB;
	}

	@Override
	public int setCount() {
		int count = 0;
		for (int i = 0; i < upTree.length; i++) {
			if (upTree[i] < 0)
				count++;
		}
		return count;
	}

}
