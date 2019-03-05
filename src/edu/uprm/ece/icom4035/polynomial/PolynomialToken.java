/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.polynomial
 * PolynomialToken.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public enum PolynomialToken {
	NEGATIVE_TERM("^[\\+]?\\-((\\d+\\.\\d+)?|\\d+?)(x(\\^\\d+)?)"),
	POSITIVE_TERM("^[+]?((\\d+\\.\\d+)?|\\d+?)(x(\\^\\d+)?)"), 
	NEGATIVE_CONSTANT("^[\\+]?\\-((\\d+\\.\\d+)|\\d+)"),
	POSITIVE_CONSTANT("^[+]?((\\d+\\.\\d+)|\\d+)");
	
	private String pattern;
	
	private PolynomialToken(String pattern) {
		this.pattern = pattern;
	}
	
	public String getPattern() {
		return pattern;
	}
}
