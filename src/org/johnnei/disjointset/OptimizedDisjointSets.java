package org.johnnei.disjointset;


public class OptimizedDisjointSets implements IDisjointSet<Integer> {
	
	/**
	 * The up tree containing all the elements which are in all sets
	 */
	private int[] upTree;
	/**
	 * The amount of sets in the set
	 */
	private int setCount;
	
	/**
	 * Creates a new DisjointSets with the size given size.<br/>
	 * This will be able to store the values 0 - (n - 1)
	 * @param size
	 */
	public OptimizedDisjointSets(int n) {
		upTree = new int[n];
		for (int i = 0; i < n; i++) {
			upTree[i] = -1;
		}
		setCount = n;
	}
	
	public Integer find(Integer item) {
		if (item < 0 || item >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", item, upTree.length));
		
		int parent = upTree[item];
		
		if (parent < 0)
			return item;
		
		return upTree[item] = find(parent);
	}

	@Override
	public void union(Integer setNameA, Integer setNameB) {
		if (setNameA < 0 || setNameA >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameA, upTree.length));
		if (setNameB < 0 || setNameB >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameB, upTree.length));
		
		if (setNameA == setNameB)
			return;
		
		// Union by size
		if (upTree[setNameA] < upTree[setNameB]) {
			int size = upTree[setNameB];
			upTree[setNameB] = setNameA;
			upTree[setNameA] += size;
		} else {
			int size = upTree[setNameA];
			upTree[setNameA] = setNameB;
			upTree[setNameB] += size;
		}
		
		--setCount;
	}

	@Override
	public int setCount() {
		return setCount;
	}

}
