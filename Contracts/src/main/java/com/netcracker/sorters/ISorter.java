package com.netcracker.sorters;

import java.util.Comparator;

/**
 * Generic sorter.
 *
 * @param <E> the array to sort
 * @param <T> the comparator to use to sort
 */
public interface ISorter<E, T> {
    void sort(E list, Comparator<T> comparator);
}
