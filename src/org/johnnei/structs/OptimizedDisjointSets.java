package org.johnnei.structs;

import java.util.LinkedList;

public class OptimizedDisjointSets implements IDisjointSet<Integer> {
	
	/**
	 * The up tree containing all the elements which are in all sets
	 */
	private int[] upTree;
	
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
	}
	
	public Integer find(Integer item) {
		if (item < 0 || item >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", item, upTree.length));
		
		int parent = upTree[item];
		LinkedList<Integer> childs = new LinkedList<>();
		
		// Find parent while remembering the children
		while (parent >= 0) {
			childs.add(item);
			item = parent;
			parent = upTree[item];
		}
		
		// Set the children to be direct children of the parent
		for (int child : childs) {
			upTree[child] = item;
		}
		
		return item;
	}

	@Override
	public void union(Integer setNameA, Integer setNameB) {
		if (setNameA < 0 || setNameA >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameA, upTree.length));
		if (setNameB < 0 || setNameB >= upTree.length)
			throw new IndexOutOfBoundsException(String.format("Item %s can not be present in disjointset of size %s", setNameB, upTree.length));
		
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
