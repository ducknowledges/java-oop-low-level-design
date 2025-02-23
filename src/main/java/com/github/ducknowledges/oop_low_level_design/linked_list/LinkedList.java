package com.github.ducknowledges.oop_low_level_design.linked_list;

public abstract class LinkedList<T> {

    /* Status: head() was not invoked */
    public static final int HEAD_NIL = 0;
    /* Status: last head() was invoked successfully. */
    public static final int HEAD_OK = 1;
    /* Status: head() was invoked when the list is empty  */
    public static final int HEAD_ERR = 2;

    /* Status: tail() was not invoked */
    public static final int TAIL_NIL = 0;
    /* Status: last tail() was invoked successfully. */
    public static final int TAIL_OK = 1;
    /* Status: tail() was invoked when the list is empty  */
    public static final int TAIL_ERR = 2;

    /* Status: right() was not invoked */
    public static final int RIGHT_NIL = 0;
    /* Status: last right() was invoked successfully. */
    public static final int RIGHT_OK = 1;
    /* Status: last right() was invoked when the current value is last or list is empty. */
    public static final int RIGHT_ERR = 2;

    /* Status: putRight() was not invoked */
    public static final int PUT_RIGHT_NIL = 0;   // putRight() has not been called
    /* Status: putRight() was invoked successfully */
    public static final int PUT_RIGHT_OK = 1;    // putRight() succeeded
    /* Status: putRight() was invoked when the list is empty */
    public static final int PUT_RIGHT_ERR = 2;   // list is empty

    /* Status: putLeft() was not invoked */
    public static final int PUT_LEFT_NIL = 0;
    /* Status: putLeft() was invoked successfully */
    public static final int PUT_LEFT_OK = 1;
    /* Status: putLeft() was invoked when the list is empty */
    public static final int PUT_LEFT_ERR = 2;

    /* Status: remove() was not invoked */
    public static final int REMOVE_NIL = 0;
    /* Status: remove() was invoked successfully */
    public static final int REMOVE_OK = 1;
    /* Status: remove() was invoked when the list is empty */
    public static final int REMOVE_ERR = 2;

    /* Status: addToEmpty() was not invoked */
    public static final int ADD_TO_EMPTY_NIL = 0;
    /* Status: addToEmpty() was invoked successfully */
    public static final int ADD_TO_EMPTY_OK = 1;
    /* Status: addToEmpty() was invoked when the list is not empty */
    public static final int ADD_TO_EMPTY_ERR = 2;

    /* Status: addTail() was not invoked */
    public static final int ADD_TAIL_NIL = 0;
    /* Status: addTail() was invoked successfully */
    public static final int ADD_TAIL_OK = 1;
    /* Status: addTail() was invoked when the list is empty */
    public static final int ADD_TAIL_ERR = 2;

    /* Status: replace() was not invoked */
    public static final int REPLACE_NIL = 0;
    /* Status: replace() was invoked successfully */
    public static final int REPLACE_OK = 1;
    /* Status: replace() was invoked when the list is empty */
    public static final int REPLACE_ERR = 2;

    /* Status: find() was not invoked */
    public static final int FIND_NIL = 0;
    /* Status: find() was invoked successfully */
    public static final int FIND_OK = 1;
    /* Status: find() was invoked when the list is empty or value not found*/
    public static final int FIND_ERR = 2;

    /* Status: get() was not invoked */
    public static final int GET_NIL = 0;
    /* Status: get() was invoked successfully */
    public static final int GET_OK = 1;
    /* Status: get() was invoked when the list is empty*/
    public static final int GET_ERR = 2;



    /** Constructor
     * Creates a new LinkedList
     *
     * @Post-condition: a new empty list was created
     */
    LinkedList() {

    }

    /* Commands */

    /**
     * @Command
     * Sets the first value as current.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: first value becomes current
     *
     */
    abstract void head();

    /**
     * @Command
     * Sets the last value as current.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: last value becomes current
     *
     */
    abstract void tail();

    /**
     * @Command
     * Sets the next value as current.
     *
     * @Pre-condition: the list is not empty && and the current value is not last
     * @Post-condition: next value becomes current
     *
     */
    abstract void right();

    /**
     * @Command
     * Put a new value after the current value.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: new value is putted after current value
     *
     * @param value the value to be putted after the current node
     */
    abstract void putRight(T value);

    /**
     * @Command
     * Put a new value before the current value.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: new value is putted before the current value
     *
     * @param value the value to be putted before the current value
     */
    abstract void putLeft(T value);

    /**
     * @Command
     * Removes the current value.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: current value is removed.
     *                  - if next value exists, it becomes current,
     *                  - otherwise if previous value exists, it becomes current
     *                  - if was removed last value in the list, then current value is empty
     */
    abstract void remove();

    /**
     * @Command
     * Clears the list, removing all elements.
     *
     * @Post-condition: the list is empty, current value is empty
     *
     */
    abstract void clear();

    /**
     * @Command
     * Adds a value to an empty list.
     *
     * @Pre-condition: the list is empty
     * @Post-condition: value is added to the list and becomes current
     *
     * @param value the value to be added to the empty list
     */
    abstract void addToEmpty(T value);

    /**
     * @Command
     * Adds a value to the end of the list.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: value is added to the end of the list
     *
     * @param value the value to be added to the end of the list
     */
    abstract void addTail(T value);

    /**
     * @Command
     * Replaces current value with a new value.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: current value is replaced with the new value
     *
     * @param value the new value that replaces the current value
     */
    abstract void replace(T value);

    /**
     * @Command
     * Finds for the next equivalent of a value, and make it current value.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: if value is found, it becomes current,
     *                  otherwise current position remains unchanged
     *
     * @param value the value to search for in the list
     */
    abstract void find(T value);

    /**
     * @Command
     * Removes all equivalents of the specified value.
     *
     * @Post-condition: all equivalents of the specified value are removed
     *                  set the current value as last
     *                  if was removed last value in the list, then current value is empty
     *
     * @param value the all equivalents of value to be removed from the list
     */
    abstract void removeAll(T value);


    /* Query */

    /**
     * @Query
     * Returns the current value.
     *
     * @Pre-condition: list is not empty
     *
     * @return the current value
     */
    abstract T get();

    /**
     * @Query
     * Returns the size of the list
     *
     * @return number of values in the list
     */
    abstract int size();

    /**
     * @Query
     * Checks if the current value is the first value
     *
     * @return {@code true} if the current value is the first value of the list,
     *          otherwise {@code false}
     */
    abstract boolean isHead();

    /**
     * @Query
     * Checks if the current value is the last value
     *
     * @return {@code true} if the current value is the last value of the list,
     *          otherwise {@code false}
     */
    abstract boolean isTail();

    /**
     * @Query
     * Checks if the list is not empty.
     *
     * @return {@code true} if list is not empty, otherwise {@code false}
     */
    abstract boolean isValue();

    /**
     * @Query
     * Returns the status of head() request
     *
     * @return one of the following statuses:
     *          {@link #HEAD_NIL}
     *          {@link #HEAD_OK}
     *          {@link #HEAD_ERR}
     */
    abstract int getHeadStatus();

    /**
     * @Query
     * Returns the status of tail() request
     *
     * @return one of the following statuses:
     *          {@link #TAIL_NIL}
     *          {@link #TAIL_OK}
     *          {@link #TAIL_ERR}
     */
    abstract int getTailStatus();

    /**
     * @Query
     * Returns the status of right() request
     *
     * @return one of the following statuses:
     *          {@link #RIGHT_NIL}
     *          {@link #RIGHT_OK}
     *          {@link #RIGHT_ERR}
     */
    abstract int getRightStatus();

    /**
     * @Query
     * Returns the status of putRight() request
     *
     * @return one of the following statuses:
     *          {@link #PUT_RIGHT_NIL}
     *          {@link #PUT_RIGHT_OK}
     *          {@link #PUT_RIGHT_ERR}
     */
    abstract int getPutRightStatus();

    /**
     * @Query
     * Returns the status of putLeft() request
     *
     * @return one of the following statuses:
     *          {@link #PUT_LEFT_NIL}
     *          {@link #PUT_LEFT_OK}
     *          {@link #PUT_LEFT_ERR}
     */
    abstract int getPutLeftStatus();

    /**
     * @Query
     * Returns the status of remove() request
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_NIL}
     *          {@link #REMOVE_OK}
     *          {@link #REMOVE_ERR}
     */
    abstract int getRemoveStatus();

    /**
     * @Query
     * Returns the status of addToEmpty() request
     *
     * @return one of the following statuses:
     *          {@link #ADD_TO_EMPTY_NIL}
     *          {@link #ADD_TO_EMPTY_OK}
     *          {@link #ADD_TO_EMPTY_ERR}
     */
    abstract int getAddToEmptyStatus();

    /**
     * @Query
     * Returns the status of addTail() request
     *
     * @return one of the following statuses:
     *          {@link #ADD_TAIL_NIL}
     *          {@link #ADD_TAIL_OK}
     *          {@link #ADD_TAIL_ERR}
     */
    abstract int getAddTailStatus();

    /**
     * @Query
     * Returns the status of replace() request
     *
     * @return one of the following statuses:
     *          {@link #REPLACE_NIL}
     *          {@link #REPLACE_OK}
     *          {@link #REPLACE_ERR}
     */
    abstract int getReplaceStatus();

    /**
     * @Query
     * Returns the status of find() request
     *
     * @return one of the following statuses:
     *          {@link #FIND_NIL}
     *          {@link #FIND_OK}
     *          {@link #FIND_ERR}
     */
    abstract int getFindStatus();

    /**
     * @Query
     * Returns the status of get() request
     *
     * @return one of the following statuses:
     *          {@link #GET_NIL}
     *          {@link #GET_OK}
     *          {@link #GET_ERR}
     */
    abstract int getGetStatus();

}
