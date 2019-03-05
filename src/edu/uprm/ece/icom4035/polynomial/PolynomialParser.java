/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.polynomial
 * PolynomialParse.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class PolynomialParser {
	private Pattern pattern;
	private String poly;

	/**
	 * Creates a PolynomialParse instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public PolynomialParser(String poly) {
		this.poly = poly.replaceAll(" ", "");
	}

	public Term getTerm() {
		Matcher matcher;
		String string;
		String[] temp;
		for (PolynomialToken token : PolynomialToken.values()) {
			pattern = Pattern.compile(token.getPattern());
			matcher = pattern.matcher(poly);
			if (matcher.find()) {
				if (token == PolynomialToken.NEGATIVE_TERM) {
					string = matcher.group();
					this.poly = this.poly.replace(string, "");
					string = string.replace("^", "");
					string = string.replace("+", "");
					// [0] coefficient
					// [1] exponent
					temp = string.split("x");
					
					String coeff = temp[0];
					String exp = "1";

					if (coeff.length() == 0)
						coeff = "1";
					
					if (temp.length > 1) {
						exp = temp[1];
					}
					
					return new TermImp(Double.parseDouble(coeff), Integer.parseInt(exp));
				} else if (token == PolynomialToken.POSITIVE_TERM){

					string = matcher.group();
					this.poly = this.poly.replace(string, "");

					string = string.replace("^", "");

					// [0] coefficient
					// [1] exponent
					temp = string.split("x");
					
					String coeff = temp[0];
					String exp = "1";

					coeff = coeff.replace("+", "");
					if (coeff.length() == 0)
						coeff = "1";
					
					if (temp.length > 1) {
						exp = temp[1];
					}
					return new TermImp(Double.parseDouble(coeff), Integer.parseInt(exp));

				} else if (token == PolynomialToken.NEGATIVE_CONSTANT) {
					string = matcher.group();
					this.poly = this.poly.replace(string, "");
					
					string = string.replace("+", "");

					return new TermImp(Double.parseDouble(string), 0);
				}
				else if (token == PolynomialToken.POSITIVE_CONSTANT) {
					string = matcher.group();
					this.poly = this.poly.replace(string, "");
					return new TermImp(Double.parseDouble(string), 0);
				}
			}
		}

		return null;
	}

	public boolean hasNext() {
		return !this.poly.isEmpty();
	}
}
