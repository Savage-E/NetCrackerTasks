package com.netcracker;

import java.util.Comparator;

/**
 * Bubble Sorter.
 * <p>
 * Please see the {@link com.netcracker.ISorter} interface for true identity
 *
 * @author Vlad Kotov
 */
public class BubbleSorter implements ISorter<DynamicArray<Contract>, Contract> {

    /**
     * Sorts source array with specified comparator.
     *
     * @param array      the source array to sort
     * @param comparator the comparator that we use to sort
     */
    @Override
    public void sort(DynamicArray<Contract> array, Comparator<Contract> comparator) {

        Contract temp;
        boolean sorted = false;

        while (!sorted) {
            sorted = true;
            for (int i = 0; i < array.size() - 1; i++) {
                if (comparator.compare(array.get(i), array.get(i + 1)) > 0) {
                    temp = array.get(i);
                    array.set(i, array.get(i + 1));
                    array.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
    }
}