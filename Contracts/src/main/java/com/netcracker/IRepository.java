package com.netcracker;


public interface IRepository {
     Contract get(int id);
     boolean delete(int id);
     boolean add(Contract contract);
}

