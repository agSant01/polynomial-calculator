/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.polynomial.junit.myjunit
 * MyTest1.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial.junit.myjunit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.uprm.ece.icom4035.list.ArrayList;
import edu.uprm.ece.icom4035.list.List;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class MyTest1 extends Debug {
	public List<Integer> list;
	
	@Before
	public void setUp() {
		list = new ArrayList<Integer>();
	}
	
	@After
	public void printActual() {
		print("\tActual: " + list);
		list.clear();
	}
	
	@Test
	public void testAddObject() {
		list.add(1);
		list.add(2);
		list.add(3);
		
		print("add(obj):");
		print("\tAdd to list: [1,2,3]");
		assertTrue(list.toString().equals("ArrayList [size=3; data=[1, 2, 3]]"));
	}
	
	@Test
	public void testAddIndex() {
		list.add(0 ,1);
		list.add(1 ,2);
		list.add(1 ,3);
		list.add(1 ,4);
		
		print("add(index):");
		
		String expected = "ArrayList [size=4; data=[1, 4, 3, 2]]";
		
		print("\tAdd to list: " + expected);
		assertTrue(list.toString().equals(expected));
	}
	
	@Test
	public void testRemove() {
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		
		list.remove(2);
				
		print("remove(index):");
		
		print("\tadd 1>2>3>4 remove index=2 obj=3");
		assertTrue(list.toString().equals("ArrayList [size=3; data=[1, 2, 4]]"));
	}
	
	@Test
	public void testAddClear() {
		list.add(0 ,1);
		list.add(1 ,2);
		list.add(1 ,3);
		list.add(1 ,4);
		
		list.clear();
		
		print("clear():");
		
		String expected = "ArrayList [size=0; data=[]]";
		
		print("\tAdd to list: " + expected);
		assertTrue(list.toString().equals(expected));
	}
	
	@Test
	public void testAddContains() {
		print("contains(obj):");
		
		int[] items = { 1, 2, 3, 4 };
		
		for (int item : items)
			list.add(item);
	
		print("\tAdded: [1, 2, 3, 4]");
		print("\tActual #1: " + list);
		
		list.remove(2);
		
		print("\tActual After remove index2 #2: " + list);
		
		assertFalse(list.contains(3));
		
		int[] items2 = { 1, 2, 4 };
		
		for (int item : items2)
			assertTrue(list.contains(item));		
	}
	
	@Test
	public void testAddIterator() {
		print("iterator():");
		
		int[] items = { 1, 2, 3, 4 };
		
		for (int item : items)
			list.add(item);
	
		print("\tAdded: [1, 2, 3, 4]");
		print("\tActual #1: " + list);
		
		
		print("\tActual After remove index2 #2: " + list);
		
		Iterator<Integer> iter = list.iterator();
		int count=0;
		while(iter.hasNext()) {
			iter.next();
			count++;
		}
		
		assertTrue(list.size() == count);
		
		int[] items2 = { 1, 2, 4 };
		
		for (int item : items2)
			assertTrue(list.contains(item));		
	}
}
