/**
 * SimplePolynomialCalculator.edu.uprm.ece.icom4035.list
 * ArrayList.java
 * @date Mar 4, 2019
 * @author Gabriel S. Santiago
 */
package edu.uprm.ece.icom4035.list;

import java.util.Iterator;

/**
 * @author Gabriel S. Santiago
 * @date Mar 4, 2019
 *
 */
public class ArrayList<E> implements List<E> {

	private final static int CAPACITY = 5;

	private int size = 0;
	private E[] data;

	/**
	 * Creates an ArrayList instance
	 *
	 * @author Gabriel S. Santiago
	 */
	public ArrayList() {
		this(CAPACITY);
	}

	/**
	 * Creates an ArrayList instance
	 *
	 * @author Gabriel S. Santiago
	 */
	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.data = (E[]) new Object[CAPACITY];
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#add(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void add(E obj) {
		add(size, obj);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#add(int, java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void add(int index, E obj) {
		checkIndex(index, size+1);

		// push every cell one to the right
		for (int i = size; i > index; i--) {
			data[i] = data[i-1];
		}

		// add data
		data[index] = obj;
		size++;

		// modify array size if necessary
		modifyArraySize();
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#remove(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean remove(E obj) {
		int index = firstIndex(obj);
		if (index == -1) return false;
		return remove(index);
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#remove(int)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean remove(int index) {
		checkIndex(index, size);

		int i = 0;
		for (i = index; i < size-1; i++) {
			data[i] = data[i+1];
		}
		data[i] = null;

		size--;

		// can reduce array size
		modifyArraySize();

		return true;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#removeAll(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int removeAll(E obj) {
		if(isEmpty()) return 0;
		int objectsRemoved = 0;
		for (int i = 0; i < size; i++) {
			if(data[i].equals(obj)) {
				remove(i);
				objectsRemoved++;	
			}
		}
		return objectsRemoved;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#get(int)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E get(int index) {
		checkIndex(index, size);
		if(isEmpty()) return null;
		return data[index];
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#set(int, java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E set(int index, E obj) {
		checkIndex(index, size);

		E current = data[index];
		data[index] = obj;

		return current;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#first()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E first() {
		if (isEmpty()) return null;
		return data[0];
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#last()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public E last() {
		if (isEmpty()) return null;
		return data[size-1];
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#firstIndex(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int firstIndex(E obj) {
		for (int i = 0; i < size; i++)
			if (this.data.equals(obj))
				return i;
		return -1;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#lastIndex(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int lastIndex(E obj) {
		for (int i = size-1; i >= 0; i--)
			if (this.data.equals(obj))
				return i;
		return -1;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#size()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public int size() {
		return this.size;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#isEmpty()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#contains(java.lang.Object)
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public boolean contains(E obj) {
		for(int i = 0; i < size; i++)
			if (data[i].equals(obj))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see edu.uprm.ece.icom4035.list.List#clear()
	 * @date Mar 4, 2019
	 * @author Gabriel S. Santiago
	 */
	@Override
	public void clear() {
		for (int i = 0; i < this.size; i++)
			this.data[i] = null;

		size = 0;

		modifyArraySize();
	}

	protected static void checkIndex(int index, int size) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("Illegal Index: " + index + " for size: " + size);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Doubles or divides the size of the current array
	 *
	 * @author Gabriel S. Santiago
	 * @date Mar 5, 2019
	 */
	private void modifyArraySize() {

		int newSize;

		// verify action
		if (size >= data.length/2 + 1)
			newSize = data.length*2;
		else
			newSize = data.length/2 + 1;

		// new array
		E[] newArray = (E[]) new Object[newSize];

		// copy all items from the old array to the new array
		for (int i = 0; i < size; i++) {
			newArray[i] = data[i];
			data[i] = null; 				// help garbage collector
		}

		data = newArray;			
	}

	private class ArrayIterator implements Iterator<E> {
		private int current = 0;
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 * @date Mar 5, 2019
		 * @author Gabriel S. Santiago
		 */
		@Override
		public boolean hasNext() {
			return current < size;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 * @date Mar 5, 2019
		 * @author Gabriel S. Santiago
		 */
		@Override
		public E next() {
			E dtr = data[current];
			current++;
			return dtr;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("ArrayList [size=");
		builder.append(size); 
		builder.append("; data=");
		builder.append("[");		
		for (int i = 0; i < size; i++) {
			if (i != 0)	
				builder.append(", ");
			
			builder.append(this.data[i]);
		}
		builder.append("]]");
		return builder.toString();
	}
}