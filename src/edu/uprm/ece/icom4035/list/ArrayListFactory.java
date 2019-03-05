/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.list
 * ArrayListFactory.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.list;

/**
 * @author Gabriel S. Santiago
 * @param <E>
 * @date Mar 5, 2019
 *
 */
public class ArrayListFactory<E> implements ListFactory<E> {

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.ListFactory#newInstance()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public List<E> newInstance() {
		return new ArrayList<E>();
	}
}
