/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.list
 * SinglyLinkedList.java
 * @date Mar 5, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

/**
 * @author Gabriel S. Santiago
 * @date Mar 5, 2019
 *
 */
public class SinglyLinkedList<E> implements List<E> {
	private SLNode<E> header;		// dummy header
	private int size = 0;

	/**
	 * Creates a {} instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public SinglyLinkedList() {
		header = new SLNode<E>(null);
		size = 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Iterator<E> iterator() {
		return new SLLIterator();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#add(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void add(E obj) {
		add(size, obj);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#add(int, java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void add(int index, E obj) {
		checkIndex(index, size+1);
		
		SLNode<E> current = this.header;
		SLNode<E> newNode = new SLNode<E>(obj);
		
		int currentIndex = 0;

		while(currentIndex < index) {
			current = current.getNext();
			currentIndex++;
		}
		
		newNode.setNext(current.getNext());
		current.setNext(newNode);
		
		size++;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#remove(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean remove(E obj) {
		SLNode<E> current = this.header.getNext();
		SLNode<E> prev = this.header;
		
		while (current != null) {
			if (current.getData().equals(obj)) {
				// bypass current
				prev.setNext(current.getNext());
				
				// help grabage collector
				current.setNext(null);
				current.setData(null);
				
				size--;
				
				return true;
			}
			prev = current;
			current = current.getNext();
		}
		
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#remove(int)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean remove(int index) {
		checkIndex(index, size);
		
		SLNode<E> current = this.header.getNext();
		SLNode<E> prev = this.header;

		int currIndex = 0;
		while (currIndex < index) {
			prev = current;
			current = current.getNext();
			currIndex++;
		}
		
		// help grabage collector
		prev.setNext(current.getNext());
		current.setData(null);
		current.setNext(null);
		
		size--;
		
		return true;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#removeAll(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int removeAll(E obj) {
		SLNode<E> current = this.header.getNext();
		SLNode<E> prev = this.header;

		int count = 0;
		
		while(current != null) {
			if (current.getData().equals(obj)) {
				// bypass current
				prev.setNext(current.getNext());

				// help garbage collector
				current.setData(null);
				current.setNext(null);
				
				count++;
			}
			prev = current;
			current = current.getNext();
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#get(int)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E get(int index) {
		checkIndex(index, size);

		SLNode<E> current = this.header.getNext();
		int currIndex = 0;
		
		while (currIndex < index) {
			current = current.getNext();
			currIndex++;
		}
		
		return current.getData();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#set(int, java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E set(int index, E obj) {
		checkIndex(index, size);

		SLNode<E> current = this.header.getNext();
		int currIndex = 0;

		while (currIndex < index) {
			current = current.getNext();
			currIndex++;
		}

		E itemToReturn = current.getData();
		current.setData(obj);

		return itemToReturn;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#first()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E first() {
		if (isEmpty()) return null;
		return this.header.getNext().getData();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#last()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E last() {
		SLNode<E> current = this.header.getNext();
		while(current.getNext() != null) {
			current = current.getNext();
		}
		return current.getData();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#firstIndex(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int firstIndex(E obj) {
		SLNode<E> current = this.header.getNext();
		int index = 0;

		while(current != null) {
			if (current.getData().equals(obj)) {
				return index;
			}
			current = current.getNext();
			index++;
		}

		return -1;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#lastIndex(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int lastIndex(E obj) {
		SLNode<E> current = this.header.getNext();
		int lastIndex = -1;
		int index = 0;

		while (current != null) {
			if (current.getData().equals(obj))
				lastIndex = index;
			
			current = current.getNext();
			index++;
		}

		return lastIndex;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#size()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int size() {
		return size;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#isEmpty()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#contains(java.lang.Object)
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean contains(E obj) {
		SLNode<E> current = this.header.getNext();

		while (current != null) {
			if (current.getData().equals(obj))
				return true;

			current = current.getNext();
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#clear()
	 * @date Mar 5, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void clear() {
		this.header.setNext(null);
		this.size = 0;
	}

	protected static void checkIndex(int index, int size) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Illegal Index: " + index + " for size: " + size);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SinglyLinkedList [size=");
		builder.append(size);
		builder.append("; data=");
		builder.append("[");	
		
		SLNode<E> current = header.getNext();
		int i = 0;
		while(current != null) {
			// only adds comma after first item
			if (i != 0)	
				builder.append(", ");
			// append data
			builder.append(current.getData());
			current = current.getNext();
			i++;
		}		
		builder.append("]]");
		return builder.toString();
	}

	private class SLLIterator implements Iterator<E> {
		private SLNode<E> current;
			
		/**
		 * Creates an SLLIterator instance
		 *
		 * @author Gabriel S. Santiago
		 */
		public SLLIterator() {
			current = header.getNext();
		}
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 * @date Mar 5, 2019
		 * @author Gabriel S. Santiago
		 */
		@Override
		public boolean hasNext() {
			return current != null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 * @date Mar 5, 2019
		 * @author Gabriel S. Santiago
		 */
		@Override
		public E next() {
			if (!hasNext()) return null;
			E itemToReturn = current.getData();
			current = current.getNext();
			return itemToReturn;
		}
	}
}
