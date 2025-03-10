package com.github.ducknowledges.oop_low_level_design.native_dictionary;

import java.lang.reflect.Array;

public class NativeDictionary<V> {

    /* Status: put() was not invoked */
    public static final int PUT_NIL = 0;
    /* Status: put() was invoked successfully */
    public static final int PUT_OK = 1;
    /* Status: put() was invoked when can't add key-value pair to dictionary */
    public static final int PUT_ERR = 2;

    /* Status: remove() was not invoked */
    public static final int REMOVE_NIL = 0;
    /* Status: remove() was invoked successfully */
    public static final int REMOVE_OK = 1;
    /* Status: remove() was invoked when can't find key-value to remove */
    public static final int REMOVE_ERR = 2;

    /* Status: get() was not invoked */
    public static final int GET_NIL = 0;
    /* Status: get() was invoked successfully */
    public static final int GET_OK = 1;
    /* Status: get() was invoked when can't find key to get value */
    public static final int GET_ERR = 2;


    private final int capacity;
    private String[] slots;
    private V [] values;
    private int size;

    private final int step = 3;

    private int putStatus;
    private int removeStatus;
    private int getStatus;

    /**
     * @Constructor
     * Creates a new dictionary with an initial capacity
     * @Post-condition: a new empty dictionary with an initial capacity was created
     */
    public NativeDictionary(int capacity) {
        this.capacity = capacity;
        this.slots = new String[capacity];
        this.values = (V[]) Array.newInstance(Object.class, this.capacity);
        this.size = 0;

        this.putStatus = PUT_NIL;
        this.removeStatus = REMOVE_NIL;
        this.getStatus = GET_NIL;
    }

    /**
     * @Command
     * Puts and associates the specified value with the specified key in this map
     *
     * @Pre-condition: the dictionary is not filled
     * @Post-condition: added value for the key if the key was absent or replaced by the existing value
     *
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     */
    public void put(String key, V value) {
        if (key == null || this.size == this.capacity) {
            this.putStatus = PUT_ERR;
            return;
        }
        int findIndex = findIndex(key);
        if (findIndex >= 0) {
            slots[findIndex] = key;
            values[findIndex] = value;
            this.putStatus = PUT_OK;
            return;
        }
        int seekIndex = seekSlot(key);
        if (seekIndex >= 0) {
            this.slots[seekIndex] = key;
            this.values[seekIndex] = value;
            this.size++;
            this.putStatus = PUT_OK;
            return;
        }
        this.putStatus = PUT_ERR;
    }

    /**
     * @Command
     * Removes the key-value pair from this dictionary if it is present
     *
     * @Pre-condition: the key is present in the dictionary
     * @Post-condition: the pair of the key-value is removed
     *
     * @param key the key with which the specified value is associated
     */
    public void remove(String key) {
        if (key == null) {
            this.removeStatus = REMOVE_ERR;
        }
        int index = findIndex(key);
        if (index >= 0) {
            slots[index] = null;
            values[index] = null;
            this.size--;
            this.removeStatus = REMOVE_OK;
        } else {
            this.removeStatus = REMOVE_ERR;
        }
    }

    /**
     * @Query
     * Remove all kay-value pairs in the dictionary
     *
     */
    public void clear() {
        this.slots = new String[capacity];
        this.values = (V[]) Array.newInstance(Object.class, this.capacity);

        this.size = 0;
        this.putStatus = PUT_NIL;
        this.removeStatus = REMOVE_NIL;
        this.getStatus = GET_NIL;
    }

    /**
     * @Query
     * Returns the value to which the specified key is present, or null
     *
     * @Pre-condition: the key is present in the dictionary
     *
     * @param key the key with which the specified value is associated
     * @return value that is associated with the specified key
     */
    public V get(String key) {
        if (key == null) {
            this.getStatus = GET_ERR;
            return null;
        }

        int index = findIndex(key);

        this.getStatus = index >= 0 ? GET_OK : GET_ERR;
        return index >= 0 ? values[index] : null;
    }

    /**
     * @Query
     * Check that the dictionary contains key
     *
     * @param key the key with which the specified value is associated
     * @return {@code true} if key is present in dictionary, {@code false} otherwise
     */
    public boolean containsKey(String key) {
        if (key == null) {
            return false;
        }
        return this.findIndex(key) >= 0;
    }

    /**
     * @Query
     * Returns the number of elements in this dictionary.
     *
     * @return the number of elements currently stored in this dictionary
     */
    public int size() {
        return this.size;
    }

    /**
     * @Query
     * Returns the status of put() command
     *
     * @return one of the following statuses:
     *          {@link #PUT_NIL} if put() was not invoked
     *          {@link #PUT_OK}  if last put() returned correct result
     *          {@link #PUT_ERR} if put() has error
     */
    public int getPutStatus() {
        return this.putStatus;
    }

    /**
     * @Query
     * Returns the status of remove() query
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_NIL} if remove() was not invoked
     *          {@link #REMOVE_OK}  if last remove() returned correct result
     *          {@link #REMOVE_ERR} if remove() has error
     */
    public int getRemoveStatus() {
        return this.removeStatus;
    }

    /**
     * @Query
     * Returns the status of get() query
     *
     * @return one of the following statuses:
     *          {@link #GET_NIL} if get() was not invoked
     *          {@link #GET_OK}  if last get() returned correct result
     *          {@link #GET_ERR} if get() has error
     */
    public int getGetStatus() {
        return this.getStatus;
    }


    private int hashFun(String key) {
        int sum = 0;
        if (key == null) {
            return sum;
        }
        for (char ch: key.toCharArray()) {
            sum += ch;
        }
        return sum % this.capacity;
    }

    private int findIndex(String key) {

        int currentIndex = this.hashFun(key);
        if (this.slots[currentIndex] != null && this.slots[currentIndex].equals(key)) {
            return currentIndex;
        }

        int referenceIndex = currentIndex;
        do {
            currentIndex = (currentIndex + this.step) % capacity;
            if (this.slots[currentIndex] != null && key.equals(this.slots[currentIndex])) {
                return currentIndex;
            }
        } while (currentIndex != referenceIndex);

        return -1;
    }

    private int seekSlot(String key) {

        int index = this.hashFun(key);
        String temp = this.slots[index];
        if (temp == null || key.equals(temp)) {
            return index;
        } else {
            index = (index + this.step) % capacity;
            while (!temp.equals(this.slots[index])) {
                if(this.slots[index] == null) {
                    return index;
                }
                index = (index + this.step) % this.capacity;
            }
        }

        return -1;
    }

}
