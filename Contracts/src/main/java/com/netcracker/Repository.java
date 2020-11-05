package com.netcracker;

public class Repository implements IRepository {
    private final DynamicArray<Contract> repo;

    public Repository() {
        this.repo = new DynamicArray<Contract>();
    }

    public boolean set(int id, Contract contract) {
        Contract temp;
        for (int i = 0; i < repo.size(); i++) {
            temp = repo.get(i);
            if (temp.getId() == id) {
                repo.set(i, contract);
                return true;
            }

        }
        return false;

    }

    public Contract get(int id) {
        Contract temp;
        for (int i = 0; i < repo.size(); i++) {
            temp = repo.get(i);
            if (temp.getId() == id)
                return temp;
        }
        System.out.println("Contract with id " + id + " not found");
        return null;

    }

    public boolean delete(int id) {
        Contract temp;
        for (int i = 0; i < repo.size(); i++) {
            temp = repo.get(i);
            if (temp.getId() == id) {
                repo.remove(i);
                return true;
            }
        }

        return false;
    }

    public boolean add(Contract contract) {
        repo.add(contract);
        return true;
    }

    public void printAll() {
        for (Contract c : repo) {
            System.out.println(c.getId());
        }
    }
}
