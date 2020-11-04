package com.netcracker;

public final class DynamicArray <T> implements Iterable<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    //private int capacity;
    private int size;

    public int size() {
        return size;
    }

   /* public void setSize(int size) {
        this.size = size;
    }
*/

    public DynamicArray() {
        this.array = new Object[DEFAULT_CAPACITY];;
        this.size = 0;
    }
    public boolean add(T data){
        add(size,data);
        return true;
    }
    public void add(int index, T data ){
        if(array.length==size){

        }
    }
}

