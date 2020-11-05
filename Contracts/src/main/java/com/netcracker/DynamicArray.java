package com.netcracker;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public final class DynamicArray<T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;

    private int size;


    public DynamicArray() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];

        this.size = 0;
    }


    public T get(int index) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();

        return array[index];
    }

    public void set(int index, T newValue) {
        if (index < 0 || index >= size()) throw new ArrayIndexOutOfBoundsException();
        array[index] = newValue;
    }

    public void remove(int index) {
        if (size() - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, size() - 1 - index);
        size--;
    }

    @NotNull
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }


    public int size() {
        return size;
    }

    public void add(T data) {
        if (array.length == size) {
            ensureCapacity((array.length) * 2);
        }
        array[size] = data;
        size++;
    }


    public void add(int index, T data) {
        if (index == array.length)
            add(data);
        else
            array[index] = data;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int getCapacity() {
        return array.length;
    }

    public void trimToSize() {
        ensureCapacity(size());
    }

    void clear() {
        size = 0;
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void ensureCapacity(int newSize) {
        T[] temp = array;
        array = (T[]) new Object[newSize];

        if (size() >= 0) System.arraycopy(temp, 0, array, 0, size());
    }

    private class ArrayListIterator implements java.util.Iterator<T> {

        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            return array[current++];
        }

        public void remove() {
            DynamicArray.this.remove(--current);
        }

    }

}

