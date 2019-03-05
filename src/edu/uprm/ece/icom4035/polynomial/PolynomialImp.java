/**
 * SimplePolynomialCalculator.implementations
 * PolynomialImp.java
 * @date Mar 4, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.polynomial;

import java.util.Iterator;

import edu.uprm.ece.icom4035.list.List;
import edu.uprm.ece.icom4035.list.ListFactory;

/**
 * @author Gabriel S. Santiago
 * @date Mar 4, 2019
 *
 */
public class PolynomialImp implements Polynomial {
	private ListFactory<Term> listFactory;
	private List<Term> termList;

	/**
	 * Creates a {} instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public PolynomialImp(String poly) {
		listFactory = TermListFactory.newListFactory();
		termList = listFactory.newInstance();

		// parser
		PolynomialParser parser = new PolynomialParser(poly);

		// adding terms
		while(parser.hasNext()) {
			Term term = parser.getTerm();
			if (term == null) break;
			if (term.getCoefficient() != 0) {
				termList.add(term);				
			}
		}

		if (termList.size() == 0)
			termList.add(new TermImp(0.0, 0));
	}

	/**
	 * Creates a {} instance
	 *
	 * @author Gabriel S. Santiago
	 */
	private PolynomialImp(List<Term> terms) {
		listFactory = TermListFactory.newListFactory();
		termList = listFactory.newInstance();

		for (Term term : terms)
			if (term.getCoefficient() != 0.0) {
				termList.add(term);
			}
		
		if (termList.size() == 0)
			termList.add(new TermImp(0.0, 0));
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Iterator<Term> iterator() {
		return termList.iterator();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#add(edu.uprm.ece.icom4035.polynomial.Polynomial)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial add(Polynomial P2) {
		Iterator<Term> thisIterator = this.iterator();
		Iterator<Term> p2Iterator = P2.iterator();
		Term newTerm;

		Term t1 = thisIterator.next();
		Term t2 = p2Iterator.next();
		List<Term> terms = this.listFactory.newInstance();

		while (t1 != null && t2 != null) {
			if (t1.getExponent() == t2.getExponent()) {
				newTerm = new TermImp(t1.getCoefficient()+t2.getCoefficient(), t1.getExponent());
				terms.add(newTerm);
				t1 = thisIterator.next();
				t2 = p2Iterator.next();
			} else if (t1.getExponent() > t2.getExponent()) {
				terms.add(new TermImp(t1.getCoefficient(), t1.getExponent()));
				t1 = thisIterator.next();
			} else if (t1.getExponent() < t2.getExponent()) {
				terms.add(new TermImp(t2.getCoefficient(), t2.getExponent()));
				t2 = p2Iterator.next();
			}
		}

		while (t1 != null) {
			newTerm = new TermImp(t1.getCoefficient(), t1.getExponent());
			terms.add(newTerm);
			t1 = thisIterator.next();
		}

		while (t2 != null) {
			newTerm = new TermImp(t2.getCoefficient(), t2.getExponent());
			terms.add(newTerm);
			t2 = p2Iterator.next();
		}
		return new PolynomialImp(terms);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#subtract(edu.uprm.ece.icom4035.polynomial.Polynomial)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial subtract(Polynomial P2) {
		return this.add(P2.multiply(-1.0));
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#multiply(edu.uprm.ece.icom4035.polynomial.Polynomial)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial multiply(Polynomial P2) {
		List<Term> terms = listFactory.newInstance();
		Polynomial newPoly = new PolynomialImp("0.0");

		for (Term term : termList) {
			for (Term p2Term : ((PolynomialImp) P2).termList) {
				Term newTerm = new TermImp(term.getCoefficient() * p2Term.getCoefficient(), 
						term.getExponent() + p2Term.getExponent());
				terms.add(newTerm);
			}
			newPoly = newPoly.add(new PolynomialImp(terms));
			terms.clear();
		}

		return newPoly;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#multiply(double)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial multiply(double c) {
		Term newTerm;
		List<Term> terms = this.listFactory.newInstance();

		for (Term term : termList) {
			newTerm = new TermImp(c * term.getCoefficient(), term.getExponent());
			terms.add(newTerm);
		}
		return new PolynomialImp(terms);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#derivative()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial derivative() {
		List<Term> terms = listFactory.newInstance();
		for (Term term : termList) {
			Term newTerm = new TermImp(term.getExponent()*term.getCoefficient(), term.getExponent()-1);
			terms.add(newTerm);
		}
		return new PolynomialImp(terms);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#indefiniteIntegral()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Polynomial indefiniteIntegral() {
		List<Term> terms = listFactory.newInstance();
		for (Term term : termList) {
			Term newTerm = new TermImp(term.getCoefficient()/(term.getExponent()+1), term.getExponent()+1);
			terms.add(newTerm);
		}
		terms.add(new TermImp(1.0, 0));
		return new PolynomialImp(terms);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#definiteIntegral(double, double)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public double definiteIntegral(double a, double b) {
		Polynomial integral = this.indefiniteIntegral();
		return integral.evaluate(b) - integral.evaluate(a);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#degree()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int degree() {
		int maxDegree = 0;
		for (Term term : termList)
			if (term.getExponent() > maxDegree)
				maxDegree = term.getExponent();
		return maxDegree;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#evaluate(double)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public double evaluate(double x) {
		double result = 0;

		for (Term term : termList)
			result += term.evaluate(x);

		return result;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.polynomial.Polynomial#equals(edu.uprm.ece.icom4035.polynomial.Polynomial)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean equals(Polynomial P) {
		if (this.termList.size() != ((PolynomialImp) P).termList.size()) return false;

		for (Term term : P) {
			if(!termList.contains(term))
				return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Term term : termList) {
			builder.append(term.toString());
			builder.append("+");
		}

		if (builder.length() > 0)
			builder = builder.replace(builder.length()-1, builder.length(), "");
		return builder.toString();
	}
}
