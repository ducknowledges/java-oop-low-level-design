package com.github.ducknowledges.oop_low_level_design.hashtable;

import java.lang.reflect.Array;

public class HashTable<E> {

    /* Status: put() was not invoked */
    public static final int PUT_NIL = 0;
    /* Status: put() was invoked successfully */
    public static final int PUT_OK = 1;
    /* Status: put() was invoked when can't add element to hashtable */
    public static final int PUT_ERR = 2;

    /* Status: remove() was not invoked */
    public static final int REMOVE_NIL = 0;
    /* Status: remove() was invoked successfully */
    public static final int REMOVE_OK = 1;
    /* Status: remove() was invoked when can't find element to remove */
    public static final int REMOVE_ERR = 2;


    private final int capacity;
    private final int step;
    private E[] slots;
    private int size;

    private int putStatus;
    private int removeStatus;

    /**
     * @Constructor
     * Creates a new hash table with initial capacity
     * @Post-condition: a new empty hash table with initial capacity was created
     */
    public HashTable(int capacity) {
        this.capacity = capacity;
        this.step = 1;
        this.slots = (E[]) Array.newInstance(Object.class, this.capacity);

        this.putStatus = PUT_NIL;
        this.removeStatus = REMOVE_NIL;
    }

    /**
     * @Command
     * Put element to hash table.
     *
     * @Pre-condition: The hash table has a free place for putting an element
     * @Post-condition: a new element has been putted into the hash table
     *
     * @param element the element to be putted into the hash table.
     */
    public void put(E element) {
        int slotIndex = seekSlotIndex(element);
        if (slotIndex < 0) {
            this.putStatus = PUT_ERR;
        } else {
            this.slots[slotIndex] = element;
            this.putStatus = PUT_OK;
            this.size++;
        }
    }


    /**
     * @Command
     * Removes the specified element from the hash table
     *
     * @Pre-condition: the hash table has an element that will be removed;
     * @Post-condition: the element is removed from the hash table
     *
     * @param element the element to be removed from this hash table
     */
    public void remove(E element) {
        int elementIndex = find(element);

        if (elementIndex == -1) {
            this.removeStatus = REMOVE_ERR;
        } else {
            slots[elementIndex] = null;

            elementIndex = (elementIndex + this.step) % this.capacity;
            while (slots[elementIndex] != null) {
                E currentElement = slots[elementIndex];
                slots[elementIndex] = null;
                put(currentElement);
                elementIndex = (elementIndex + this.step) % this.capacity;
            }

            this.size--;
            this.removeStatus = REMOVE_OK;
        }
    }

    /**
     * @Query
     * Clear hash table
     *
     */
    public void clear() {
        this.size = 0;
        this.slots = (E[]) Array.newInstance(Object.class, this.capacity);

        this.putStatus = PUT_NIL;
        this.removeStatus = REMOVE_NIL;
    }

    /**
     * @Query
     * Checks if the specified element is present in the hash table
     *
     * @param element the element to check for existence in this hash table
     * @return {@code true} if the element is found in the hash table, {@code false} otherwise
     */
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }

        int hash = this.hashFun(element);
        if (this.slots[hash] == null) {
            return false;
        }

        if (this.slots[hash].equals(element)) {
            return true;
        }

        int temp = hash;
        do {
            hash = (hash + this.step) % this.capacity;
            if (this.slots[hash] != null && element.equals(this.slots[hash])) {
                return true;
            }
        } while (hash != temp && this.slots[hash] != null);

        return false;
    }

    /**
     * @Query
     * Returns the number of elements in this hash table.
     * @Pre-condition:
     * @Post-condition:
     *
     * @return the number of elements currently stored in this hash table
     */
    public int size() {
        return this.size;
    }

    /**
     * @Query
     * Checks if this hash table contains no elements.
     *
     * @return {@code true} if this hash table contains no elements, {@code false} otherwise
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    private int seekSlotIndex(E element) {
        if (element == null) {
            return -1;
        }

        int hash = this.hashFun(element);
        E temp = slots[hash];
        if (temp == null) {
            return hash;
        } else {
            hash = (hash + this.step) % this.capacity;
            while (!temp.equals(slots[hash])) {
                if(slots[hash] == null) {
                    return hash;
                }
                hash = (hash + this.step) % this.capacity;
            }
        }

        return -1;
    }

    private int find(E element) {
        if (element == null) {
            return -1;
        }
        int hash = this.hashFun(element);
        if (this.slots[hash] == null) {
            return -1;
        }
        if (this.slots[hash].equals(element)) {
            return hash;
        }

        int temp = hash;
        do {
            hash = (hash + this.step) % this.capacity;
            if (this.slots[hash] != null && element.equals(this.slots[hash])) {
                return hash;
            }
        } while (hash != temp && this.slots[hash] != null);

        return -1;
    }

    private int hashFun(E element) {
        if (element == null) {
            return 0;
        }

        return Math.abs(element.hashCode()) % this.capacity;
    }

}
