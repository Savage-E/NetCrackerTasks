package com.netcracker;


/**
 * Repository interface provides get, delete, add and set methods.
 *
 * @author Vlad Kotov
 */
public interface IRepository {
    /**
     * Returns the specified contract with specified id.
     *
     * @param id  the id of the contract to get
     * @return the specified contract entity
     */
    Contract get(int id);

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
    boolean add(Contract contract);

    /**
     * Replaces the contract with the specified id in this repository with the specified contract.
     *
     * @param id the id contract to replace
     * @param contract the contract to be stored in the specified position
     * @return the contract previously at the specified position
     */
    Contract set(int id, Contract contract);
}

