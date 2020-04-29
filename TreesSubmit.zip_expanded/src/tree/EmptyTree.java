package tree;

import java.util.Collection;

/**
 * This class is used to represent the empty search tree: a search tree that
 * contains no entries.
 * 
 * This class is a singleton class: since all empty search trees are the same,
 * there is no need for multiple instances of this class. Instead, a single
 * instance of the class is created and made available through the static field
 * SINGLETON.
 * 
 * The constructor is private, preventing other code from mistakenly creating
 * additional instances of the class.
 * 
 */
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	/**
	 * This static field references the one and only instance of this class. We
	 * won't declare generic types for this one, so the same singleton can be used
	 * for any kind of EmptyTree.
	 */
	@SuppressWarnings("rawtypes")
	private static EmptyTree SINGLETON = new EmptyTree();

	@SuppressWarnings("unchecked")
	public static <K extends Comparable<K>, V> EmptyTree<K, V> getInstance() {
		return SINGLETON;
	}

	/**
	 * Constructor is private to enforce it being a singleton
	 * 
	 */
	private EmptyTree() {
	}

	public V search(K key) {
		return null;
	}

	public NonEmptyTree<K, V> insert(K key, V value) {
		// return non empty tree which points left and right to singleton
		return new NonEmptyTree(key, value, SINGLETON, SINGLETON);
	}

	public Tree<K, V> delete(K key) {
		// returns empty tree
		return SINGLETON;
	}

	public K max() throws TreeIsEmptyException {
		// throw above exception
		throw new TreeIsEmptyException();
	}

	public K min() throws TreeIsEmptyException {
		// throw above exceptions
		throw new TreeIsEmptyException();
	}

	public int size() {
		// size is 0
		return 0;
	}

	public void addKeysToCollection(Collection<K> c) {
		// no op
	}

	public Tree<K, V> subTree(K fromKey, K toKey) {
		return SINGLETON;
	}

	public int height() {
		// height is 0
		return 0;
	}

	public void inorderTraversal(TraversalTask<K, V> p) {
		// no op
	}

	public void rightRootLeftTraversal(TraversalTask<K, V> p) {
		// no op
	}
}