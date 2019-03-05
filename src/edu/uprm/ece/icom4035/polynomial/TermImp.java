/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.polynomial
 * TermImp.java
 * @date Mar 4, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial;

import java.text.DecimalFormat;

/**
 * @author Gabriel S. Santiago
 * @date Mar 4, 2019
 *
 */
public class TermImp implements Term {
	private Double coefficient;
	private int exponent;
	
	/**
	 * Creates a TermImp instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public TermImp(Double coeffiecient, int exponent) {
		this.coefficient = coeffiecient;
		this.exponent = exponent;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Term#getCoefficient()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public double getCoefficient() {
		return coefficient;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Term#getExponent()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int getExponent() {
		return exponent;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Term#evaluate(double)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public double evaluate(double x) {
		return coefficient * Math.pow(x, exponent);
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TermImp other = (TermImp) obj;
		if (coefficient == null) {
			if (other.coefficient != null)
				return false;
		} else if (!coefficient.equals(other.coefficient))
			return false;
		if (exponent != other.exponent)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(new DecimalFormat("#0.00").format(coefficient));
		if (exponent != 0) {
			builder.append("x");
			if (exponent != 1) {
				builder.append("^");
				builder.append(exponent);			
			}
		}
		return builder.toString();
	}
}
