/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.polynomial.junit.myjunit
 * MyTest1.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial.junit.myjunit;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import edu.uprm.ece.icom4035.polynomial.PolynomialImp;
import edu.uprm.ece.icom4035.polynomial.PolynomialParser;
import edu.uprm.ece.icom4035.polynomial.Term;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class MyTest3 extends Debug {
	private PolynomialParser parser;
	
	private String expected;
	
	@After
	public void showPolynomial() {
		print("\tPolynomial: " + new PolynomialImp(expected));
	}
	
	@Test
	public void testParse1() {
		expected = "12x^2+3";
				
		parser = new PolynomialParser(expected);
		
		print("testParse1:");
		
		print("\tExpected: [12.00x^2, 3.00]");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[12.00x^2, 3.00]"));
	}
	
	@Test
	public void testParse2() {
		expected = "-12x";
				
		parser = new PolynomialParser(expected);
		
		print("testParse2:");
		
		print("\tExpected: [-12.00x]");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[-12.00x]"));
	}
	
	@Test
	public void testParse3() {
		expected = "6x+2";
				
		parser = new PolynomialParser(expected);
		
		print("testParse3:");
		
		print("\tExpected: [6.00x, 2.00]");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[6.00x, 2.00]"));
	}
	
	@Test
	public void testParse4() {
		expected = "3x^2+2x+1";
				
		parser = new PolynomialParser(expected);
		
		print("testParse4:");
		
		print("\tExpected: [3.00x^2, 2.00x, 1]");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[3.00x^2, 2.00x, 1.00]"));
	}
	
	
	@Test
	public void testParse5() {
		expected = "x^2-2";
				
		parser = new PolynomialParser(expected);
		
		print("testParse5:");
		
		print("\tExpected: [x^2, -2.00]");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[1.00x^2, -2.00]"));
	}
	
	@Test
	public void testParse6() {
		expected = "";
				
		parser = new PolynomialParser(expected);
		
		print("testParse6:");
		
		print("\tExpected: []");
		
		ArrayList<Term> list = new ArrayList<Term>();
		
		while(parser.hasNext())
			list.add(parser.getTerm());
				
		print("\tActual: " + list);
		
		assertTrue(list.toString().equals("[]"));
	}
}
