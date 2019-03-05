/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.list
 * SLNode.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.list;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class SLNode<E> {
	private E data;
	private SLNode<E> next;

	/**
	 * Creates a SLNode instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public SLNode(E data) {
		this(data, null);
	}

	/**
	 * Creates a SLNode instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public SLNode(E data, SLNode<E> next) {
		this.data = data;
		this.next = next;
	}
	
	public SLNode<E> getNext() {
		return next;
	}

	public void setNext(SLNode<E> next) {
		this.next = next;
	}

	public E getData() {
		return data;
	}
	
	public void setData(E data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SLNode [data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
}
