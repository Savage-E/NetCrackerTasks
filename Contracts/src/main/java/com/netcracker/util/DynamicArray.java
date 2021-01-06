package com.netcracker.util;

import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;


/**
 * Dynamic array with self-expanding size.
 *
 * @param <T> the type of elements in this array
 * @author Vlad Kotov
 */
public final class DynamicArray<T> implements Iterable<T> {
  private static final int DEFAULT_CAPACITY = 10;
  private T[] array;
  private static final Logger logger = LogManager.getLogger(DynamicArray.class);
  private int size;


  /**
   * Initializes an array.
   */
  public DynamicArray() {
    this.array = (T[]) new Object[DEFAULT_CAPACITY];

    this.size = 0;
  }


  /**
   * Returns the element at the specified position in this array.
   *
   * @param index the index of the element to return
   * @return the element at the specified position in this array
   * @throws ArrayIndexOutOfBoundsException if index greater than size of array
   */
  public T get(int index) {
    if (index < 0 || index >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }

    return array[index];
  }


  /**
   * Replaces the element at the specified position in this array with the specified element.
   *
   * @param index    the index of the element to replace
   * @param newValue - new value to be stored at the specified position
   * @return the element previously at the specified position
   * @throws ArrayIndexOutOfBoundsException if index greater than size of array
   */
  public T set(int index, T newValue) {
    if (index < 0 || index >= size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    Object el = array[index];
    array[index] = newValue;
    return (T) el;
  }


  /**
   * Removes the element at the specified position in this array.
   *
   * @param index the index of the element to be removed
   * @return true if this array contained the specified element
   */
  public boolean remove(int index) {
    if (size() - 1 - index >= 0) {
      System.arraycopy(array, index + 1, array, index, size() - 1 - index);
      size--;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns an iterator over the elements in this array in proper sequence.
   *
   * @return an iterator over the elements in this array in proper sequence
   */
  @NotNull
  public Iterator<T> iterator() {
    return new ArrayListIterator();
  }


  /**
   * Returns the number of elements in this array.
   *
   * @return the number of elements in this array
   */
  public int size() {
    return size;
  }

  /**
   * Appends the specified element to the end of the array.
   *
   * @param element the element to be appended to this array
   * @return true if the contract was added to the array
   */
  public boolean add(T element) {
    if (array.length == size) {
      ensureCapacity((array.length) * 2);
    }
    array[size] = element;
    size++;
    return true;
  }

  /**
   * Inserts the specified element at the specified position in this array.
   *
   * @param index   - the index at which the specified element is to be inserted
   * @param element - the element to be inserted
   */
  public void add(int index, T element) {
    if (index == array.length) {
      add(element);
    } else {
      array[index] = element;
    }
  }

  /**
   * Returns true if this array contains no elements.
   *
   * @return true if this array contains no elements
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * Returns the capacity of the array.
   *
   * @return the capacity of the array
   */
  public int getCapacity() {
    return array.length;
  }

  /**
   * Reduces the capacity of array to current size.
   */
  public void trimToSize() {
    ensureCapacity(size());
  }

  /**
   * Removes all of the elements from this array.
   */
  public void clear() {
    size = 0;
    array = (T[]) new Object[DEFAULT_CAPACITY];
  }


  /**
   * Sets up new capacity of the array.
   *
   * @param newSize new capacity to be set
   */
  public void ensureCapacity(int newSize) {
    T[] temp = array;
    array = (T[]) new Object[newSize];

    if (size() >= 0) {
      System.arraycopy(temp, 0, array, 0, size());
    }
  }

  private class ArrayListIterator implements java.util.Iterator<T> {

    private int current = 0;

    public boolean hasNext() {
      return current < size();
    }

    public T next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException();
      }
      return array[current++];
    }

    public void remove() {
      DynamicArray.this.remove(--current);
    }

  }

}

