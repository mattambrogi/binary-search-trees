package tree;

import java.util.Collection;

/**
 * This class represents a non-empty search tree. An instance of this class
 * should contain:
 * <ul>
 * <li>A key
 * <li>A value (that the key maps to)
 * <li>A reference to a left Tree that contains key:value pairs such that the
 * keys in the left Tree are less than the key stored in this tree node.
 * <li>A reference to a right Tree that contains key:value pairs such that the
 * keys in the right Tree are greater than the key stored in this tree node.
 * </ul>
 * 
 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	// instance variables
	K key;
	V value;
	Tree<K, V> left;
	Tree<K, V> right;

	// constructor
	public NonEmptyTree(K key, V value, Tree<K, V> left, Tree<K, V> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}

	public V search(K key) {
		// if find key, return associated value
		if (key.compareTo(this.key) == 0) {
			return value;
		} else if (key.compareTo(this.key) < 0) {
			return left.search(key); // if smaller look for in left subtree
		} else if (key.compareTo(this.key) > 0) {
			return right.search(key);// if larger, look for in right subtree
		} else {
			return null;
		}
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		int comp = key.compareTo(this.key);

		if (comp == 0) {
			// if equivalent, key should point to new value
			this.value = value;
			return this;
		} else if (comp < 0) { // recursive call on left tree if less
			left = left.insert(key, value);
			return this;
		} else { // recursive call on right tree otherwise
			right = right.insert(key, value);
			return this;
		}
	}

	public Tree<K, V> delete(K key) {
		// new key
		K k;

		if (key.compareTo(this.key) == 0) {
			try {
				/*
				 * When find key Set that tree to point to max key and value from left Then
				 * remove the duplicate
				 */
				k = left.max();
				V v = left.search(k);
				this.key = k;
				this.value = v;
				left = left.delete(k);
			} catch (TreeIsEmptyException e) {
				/*
				 * if find left is empty either want to return right if exists or empty tree if
				 * right is empty
				 */
				return right;
			}
			return this;
		}

		else if (key.compareTo(this.key) < 0) {
			this.left = left.delete(key); // recursive call on left
			return this;
		} else {
			this.right = right.delete(key); // recursive call on right
			return this;
		}

	}

	public K max() {
		try {
			return right.max();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public K min() {
		try {
			return left.min();
		} catch (TreeIsEmptyException e) {
			return key;
		}
	}

	public int size() {
		return left.size() + right.size() + 1;
	}

	public void addKeysToCollection(Collection<K> c) {
		/*
		 * add key recursively call on left and right
		 */
		c.add(key);
		left.addKeysToCollection(c);
		right.addKeysToCollection(c);
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		// if starting key larger, look down right tree
		if (fromKey.compareTo(this.key) > 0) {
			return right.subTree(fromKey, toKey);
		}
		// to ending key is smaller, look down left tree
		else if (toKey.compareTo(this.key) < 0) {
			return left.subTree(fromKey, toKey);
		} else {
			NonEmptyTree tree = new NonEmptyTree(key, value, left.subTree(fromKey, toKey),
					right.subTree(fromKey, toKey));
			return tree;
		}
	}

	public int height() {
		/*
		 * goes through each possible path and returns max length
		 */
		return (Math.max(left.height(), right.height()) + 1);
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		// traverses tree left, root, right
		left.inorderTraversal(p);
		p.performTask(key, value);
		right.inorderTraversal(p);
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		// traverses tree right, root, left
		right.rightRootLeftTraversal(p);
		p.performTask(key, value);
		left.rightRootLeftTraversal(p);
	}
}