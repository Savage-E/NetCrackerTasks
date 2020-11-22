package com.netcracker;


import com.netcracker.sorters.ISorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Repository interface provides get, delete, add and set methods.
 *
 * @author Vlad Kotov
 */
public interface IRepository<T> {
    /**
     * Returns the specified contract with specified id.
     *
     * @param id the id of the contract to get
     * @return the specified contract entity
     */
    T get(int id);

    /**
     * Deletes specified contract.
     *
     * @param id the id of the contract to delete
     * @return true if this repository contained the specified contract
     */
    boolean delete(int id);

    /**
     * Appends the specified element to the end of this array.
     *
     * @param contract the contract to be appended to this repository
     * @return true if the contract was added to the repository
     */
    boolean add(T contract);

    /**
     * Replaces the contract with the specified id in this repository with the specified contract.
     *
     * @param id       the id contract to replace
     * @param contract the contract to be stored in the specified position
     * @return the contract previously at the specified position
     */
    T set(int id, T contract);

    /**
     * Returns new repository with specified objects by specified condition.
     *
     * @param condition the condition to search the contracts
     * @return new repository with specific objects satisfying the condition
     */
    IRepository<T> searchBy(Predicate<T> condition);

    /**
     * Converts repository to arrayList.
     *
     * @return the arraylist
     */
    ArrayList<T> toArrayList();

    /**
     * Sorts repository with specified comparator.
     *
     * @param comparator the comparator to use to sort
     * @param sorter     the type of sorter to sort
     */
    void sortBy(Comparator<T> comparator, ISorter sorter);

}

