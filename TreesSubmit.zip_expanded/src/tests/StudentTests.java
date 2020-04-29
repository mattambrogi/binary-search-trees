package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {
	
	@Test
	public void testSubMap() {
		PolymorphicBST<Integer,String> ptree = new PolymorphicBST<Integer,String>();
		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(5, "Five");
		ptree.put(8, "Eight");
		PolymorphicBST<Integer,String> subTree = ptree.subMap(2, 8);
		int size = subTree.size();
		System.out.println(size);
	}
}