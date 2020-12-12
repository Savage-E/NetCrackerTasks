package com.netcracker;


import com.netcracker.entities.Contract;
import com.netcracker.sorters.ISorter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;

/**
 * Represents repository for contracts.
 *
 * <p>Please see the {@link com.netcracker.IRepository} interface for true identity
 *
 * @author Vlad Kotov
 */
public class Repository implements IRepository<Contract> {
  private final DynamicArray<Contract> repo;

  /**
   * Initializes repository.
   */
  public Repository() {
    this.repo = new DynamicArray<>();
  }

  /**
   * Sorts repository with specified comparator.
   *
   * @param comparator the comparator to use to sort
   * @param sorter     the type of sorter to sort
   */
  @Override
  public void sortBy(Comparator<Contract> comparator, ISorter sorter) {


    sorter.sort(repo, comparator);


  }

  /**
   * Converts repository to arrayList.
   *
   * @return the arraylist
   */
  @Override
  public ArrayList<Contract> toArrayList() {
    ArrayList<Contract> list = new ArrayList<>();
    for (Contract c : repo) {
      list.add(c);
    }
    return list;
  }

  /**
   * Returns new repository with specified contracts by specified condition.
   *
   * @param condition - the condition to search the contracts
   * @return new repository with specific contracts satisfying the condition
   */
  public IRepository<Contract> searchBy(Predicate<Contract> condition) {
    Repository repository = new Repository();
    for (Contract c : repo) {
      if (condition.test(c)) {
        repository.add(c);
      }
    }
    return repository;
  }

  /**
   * Replaces the contract at the specified position in this repository with the specified contract.
   *
   * @param id       the id of the contract to replace
   * @param contract - new contract to be stored at the specified position
   * @return the contract at the specified position
   */
  public Contract set(int id, Contract contract) {
    Contract temp;
    for (int i = 0; i < repo.size(); i++) {
      temp = repo.get(i);
      if (temp.getId() == id) {

        repo.set(i, contract);
        return temp;
      }
    }
    System.out.println("Contract with id " + id + " not found");
    return null;
  }

  /**
   * Returns the contract at the specified position in this array.
   *
   * @param id the id of the contract to return
   * @return the contract at the specified position in this array
   */
  public Contract get(int id) {
    Contract temp;
    for (int i = 0; i < repo.size(); i++) {
      temp = repo.get(i);
      if (temp.getId() == id) {
        return temp;
      }
    }
    return null;
  }

  /**
   * Removes the element at the specified position in this array.
   *
   * @param id the id of the contract to be removed
   * @return true if this array contained the specified contract
   */
  public boolean delete(int id) {
    Contract temp;
    for (int i = 0; i < repo.size(); i++) {
      temp = repo.get(i);
      if (temp.getId() == id) {
        return repo.remove(i);
      }
    }
    return false;
  }

  /**
   * Appends the specified contract to the end of the array.
   *
   * @param contract the contract to be appended to this array
   * @return true if the contract was added to the array
   */
  public boolean add(Contract contract) {
    return repo.add(contract);
  }

}