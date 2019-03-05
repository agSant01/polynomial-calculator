/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.list
 * SinglyLinkedListFactory.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.list;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class SinglyLinkedListFactory<E> implements ListFactory<E> {
	private List<E> internalList;
	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.ListFactory#newInstance()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public List<E> newInstance() {
		internalList = new SinglyLinkedList<E>();
		return internalList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public String toString() {
		return ((SinglyLinkedList<E>) internalList).toString();
	}

}
