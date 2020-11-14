package com.netcracker.sorters;

import com.netcracker.DynamicArray;
import com.netcracker.contracts.Contract;
import com.netcracker.sorters.ISorter;

import java.util.Comparator;

/**
 * Merge Sorter.
 * <p>
 * Please see the {@link ISorter} interface for true identity
 *
 * @author Vlad Kotov
 */
public class MergeSorter implements ISorter<DynamicArray<Contract>, Contract> {
    /**
     * Sorts source array with specified comparator.
     *
     * @param array the source array to sort
     * @param comparator the comparator that we use to sort
     */
    @Override
    public void sort(DynamicArray<Contract> array, Comparator<Contract> comparator) {
        Contract temp;
        if (array.size() != 1) {

            final DynamicArray<Contract> left = new DynamicArray<>();
            final DynamicArray<Contract> right = new DynamicArray<>();
            boolean logicalSwitch = true;

            while (!array.isEmpty()) {
                if (logicalSwitch) {
                    temp = array.get(0);
                    left.add(temp);
                    array.remove(0);
                } else {
                    temp = array.get(0);
                    right.add(temp);
                    array.remove(0);
                }
                logicalSwitch = !logicalSwitch;
            }
            sort(left, comparator);
            sort(right, comparator);

            for (Contract c :
                    merge(left, right, comparator)) {
                array.add(c);
            }
        }
    }

    private DynamicArray<Contract> merge(final DynamicArray<Contract> left, final DynamicArray<Contract> right, Comparator<Contract> comparator) {
        final DynamicArray<Contract> merged = new DynamicArray<>();
        Contract temp;
        while (!left.isEmpty() && !right.isEmpty()) {
            if (comparator.compare(left.get(0), right.get(0)) <= 0) {
                temp = left.get(0);
                merged.add(temp);
                left.remove(0);
            } else {
                temp = right.get(0);
                merged.add(temp);
                right.remove(0);
            }
        }
        for (Contract c :
                left) {
            merged.add(c);
        }
        for (Contract c :
                right) {
            merged.add(c);
        }
        return merged;
    }


}





