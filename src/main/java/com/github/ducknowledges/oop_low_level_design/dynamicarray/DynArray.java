package com.github.ducknowledges.oop_low_level_design.dynamicarray;

import java.lang.reflect.Array;
import java.util.Objects;

public class DynArray<E> {

    /** Status: replace() was not invoked. */
    public static final int REPLACE_NIL = 0;
    /** Status: last replace() was invoked successfully. */
    public static final int REPLACE_OK = 1;
    /** Status: replace() was invoked when the index is out of range or the array is empty */
    public static final int REPLACE_ERR = 2;

    /** Status: remove() was not invoked. */
    public static final int REMOVE_NIL = 0;
    /** Status: last remove() was invoked successfully. */
    public static final int REMOVE_OK = 1;
    /** Status: remove() was invoked when the index is out of range or the array is empty */
    public static final int REMOVE_ERR = 2;

    /** Status: get() was not invoked. */
    public static final int GET_NIL = 0;
    /** Status: last get() was invoked successfully. */
    public static final int GET_OK = 1;
    /** Status: get() was invoked when the index is out of range or the array is empty */
    public static final int GET_ERR = 2;

    /** Status: getIndexOf() was not invoked. */
    public static final int GET_INDEX_NIL = 0;
    /** Status: last getIndexOf() was invoked successfully. */
    public static final int GET_INDEX_OK = 1;
    /** Status: getIndexOf() was invoked when the index is out of range or the array is empty */
    public static final int GET_INDEX_ERR = 2;

    /** Status: insert() was not invoked. */
    public static final int INSERT_NIL = 0;
    /** Status: last insert() was invoked successfully. */
    public static final int INSERT_OK = 1;
    /** Status: insert() was invoked when the index is out of range or the array is empty */
    public static final int INSERT_ERR = 2;

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final int INCREASE_CAPACITY = 2;
    private static final double DECREASE_CAPACITY = 1.5;

    private E[] buffer;
    private int capacity;
    private int size;

    private int replaceStatus;
    private int removeStatus;
    private int getStatus;
    private int getIndexStatus;
    private int insertStatus;

    /**
     * @Constructor
     * Creates a new DynArray with a specified initial capacity
     *
     * @Post-condition: a new empty array of a specified initial capacity was created
     *
     */
    DynArray(int initialCapacity) {
        this.capacity = initialCapacity;
        this.makeArray(initialCapacity);
        this.size = 0;

        this.replaceStatus = REPLACE_NIL;
        this.removeStatus = REMOVE_NIL;
        this.getStatus = GET_NIL;
        this.getIndexStatus = GET_INDEX_NIL;
        this.insertStatus = INSERT_NIL;
    }

    /**
     * @Constructor
     * Creates a new DynArray with a default initial capacity
     *
     * @Post-condition: a new empty array of a default initial capacity was created
     *
     */
    DynArray() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /* Commands */

    /**
     * @Command
     * Adds an element as the last array element.
     *
     * @Post-condition: element is added as the last element of array
     *
     * @param element the element to be added to the array
     */
    public void append(E element) {
        if (size == capacity) {
            this.increaseBuffer();
        }
        buffer[size] = element;
        size = size + 1;
    }

    /**
     * @Command
     * Replaces the element at the specified position.
     *
     * @Pre-condition: - the array is not empty
     *                 - index is not out of range (index >= 0 && index < size())
     * @Post-condition: the element at the specified position is replaced with the new element
     *
     * @param index the specified position number of element in array.
     * @param element the new element that replaces the current element
     */
    public void replace(int index, E element) {
        if (this.isEmpty() || this.isOutOfRange(index)) {
            this.replaceStatus = REPLACE_ERR;
        } else {
            buffer[index] = element;
            this.replaceStatus = REPLACE_OK;
        }
    }

    /**
     * @Command
     * Insert the element at the specified position.
     *
     * @Pre-condition: - the array is not empty
     *                 - index is not out of range (index >= 0 && index < size())
     * @Post-condition: insert the element at the specified position with
     *
     * @param index the specified position number of element in array.
     * @param element the new element that inserted at the specified position.
     */
    public void insert(int index, E element) {
        if (this.isEmpty() || this.isOutOfRange(index)) {
            insertStatus = INSERT_ERR;
        } else {
            if (size == capacity) {
                this.increaseBuffer();
            }
            this.shiftElementsToRightFrom(index);
            buffer[index] = element;
            size = size + 1;
            insertStatus = INSERT_OK;
        }
    }

    /**
     * @Command
     * Removes the element at the specified position.
     *
     * @Pre-condition: - the array is not empty
     *                 - index is not out of range (index >= 0 && index < size())
     * @Post-condition: the element is removed by index
     *
     * @param index the specified position number of element in array.
     */
    public void remove(int index) {
        if (this.isEmpty() || this.isOutOfRange(index)) {
            this.removeStatus = REMOVE_ERR;
        } else {
            this.shiftElementsToLeftFrom(index);
            buffer[this.size - 1] = null;
            size = size - 1;
            if (((double) size / capacity) < 0.5 && capacity > 16) {
                this.decreaseBuffer();
            }
            this.removeStatus = REMOVE_OK;
        }
    }

    /**
     * @Command
     * Removes all elements from the array
     *
     * @Post-condition: all elements are removed from the array
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        this.buffer = (E[]) Array.newInstance(Object.class, DEFAULT_INITIAL_CAPACITY);
        this.size = 0;
        this.replaceStatus = REPLACE_NIL;
        this.removeStatus = REMOVE_NIL;
        this.getStatus = GET_NIL;
        this.getIndexStatus = GET_INDEX_NIL;
    }

    /* Query */

    /**
     * @Query
     * Returns the element by index.
     *
     * @Pre-condition: - the array is not empty
     *                 - index is not out of range (index >= 0 && index < size())
     *
     * @param index the specified position number of element in array.
     * @return the element at the specified position
     */
    public E get(int index) {
        if (this.isEmpty() || this.isOutOfRange(index)) {
            getStatus = GET_ERR;
            return null;
        }
        getStatus = GET_OK;
        return buffer[index];
    }

    /**
     * @Query
     * Returns the index of the first occurrence of the specified element
     *
     * @Pre-condition: - the array is not empty
     *
     * @param element the specified position number of element in array.
     * @return index the index of found element or -1 if not found
     */
    public int getIndexOf(E element) {
        if (!this.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (Objects.equals(buffer[i], element)) {
                    getIndexStatus = GET_INDEX_OK;
                    return i;
                }
            }
        }
        getIndexStatus = GET_INDEX_ERR;
        return -1;
    }

    /**
     * @Query
     * Returns the size of the array
     *
     * @return number of values in the array
     */
    public int size() {
        return size;
    }

    /**
     * @Query
     * Checks if the array is empty.
     *
     * @return {@code true} if array is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @Query
     * Returns the status of replace() command
     *
     * @return one of the following statuses:
     *          {@link #REPLACE_NIL} if replace() was not invoked
     *          {@link #REPLACE_OK}  if last replace() was invoked successfully
     *          {@link #REPLACE_ERR} if replace() has error
     */
    public int getReplaceStatus() {
        return this.replaceStatus;
    }

    /**
     * @Query
     * Returns the status of remove() command
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_NIL} if remove() was not invoked
     *          {@link #REMOVE_OK}  if last remove() was invoked successfully
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

    /**
     * @Query
     * Returns the status of getIndexOf() query
     *
     * @return one of the following statuses:
     *          {@link #GET_INDEX_NIL} if getIndexOf() was not invoked
     *          {@link #GET_INDEX_OK}  if last getIndexOf() returned correct result
     *          {@link #GET_INDEX_ERR} if getIndexOf() has error
     */
    public int getGetIndexStatus() {
        return this.getIndexStatus;
    }

    /**
     * @Query
     * Returns the status of insert() query
     *
     * @return one of the following statuses:
     *          {@link #INSERT_NIL} if insert() was not invoked
     *          {@link #INSERT_OK}  if last insert() returned correct result
     *          {@link #INSERT_ERR} if insert() has error
     */
    public int getInsertStatus() {
        return this.insertStatus;
    }

    @SuppressWarnings("unchecked")
    private void makeArray(int new_capacity) {
        this.capacity = Math.max(new_capacity, DEFAULT_INITIAL_CAPACITY);
        E[] tmp = buffer;
        this.buffer = (E[]) Array.newInstance(Object.class, this.capacity);
        if (tmp != null) {
            System.arraycopy(tmp, 0, buffer, 0, this.size);
        }
    }

    private boolean isOutOfRange(int index) {
        return index >= this.size || index < 0;
    }

    private void increaseBuffer() {
        makeArray(INCREASE_CAPACITY * this.capacity);
    }

    private void decreaseBuffer() {
        makeArray((int)(this.capacity / DECREASE_CAPACITY));
    }

    private void shiftElementsToLeftFrom(int index) {
        System.arraycopy(buffer, index + 1, buffer, index, this.size - index - 1);
    }

    private void shiftElementsToRightFrom(int index) {
        System.arraycopy(buffer, index, buffer, index + 1, this.size - index);
    }
}