package com.github.ducknowledges.oop_low_level_design.queue;

import java.util.Deque;
import java.util.LinkedList;

public class Queue<E> {

    /** Status: add() was not invoked. */
    public static final int ADD_NIL = 0;
    /** Status: last add() was invoked successfully. */
    public static final int ADD_OK = 1;
    /** Status: if add() was invoked when the queue is completely filled*/
    public static final int ADD_ERR = 2;

    /** Status: pop() was not invoked. */
    public static final int POP_NIL = 0;
    /** Status: last pop() was invoked successfully. */
    public static final int POP_OK = 1;
    /** Status: if pop() was invoked when the queue is empty*/
    public static final int POP_ERR = 2;

    /** Status: peek() was not invoked. */
    public static final int PEEK_NIL = 0;
    /** Status: last peek() was invoked successfully. */
    public static final int PEEK_OK = 1;
    /** Status: if peek() was invoked when the queue is empty*/
    public static final int PEEK_ERR = 2;

    private static final int DEFAULT_CAPACITY = 32;

    private final Deque<E> storage;
    private final int capacity;
    private int addStatus;
    private int popStatus;
    private int peekStatus;


    /**
     * @Constructor
     * Creates a new Queue with a determined size
     * @Post-condition: a new empty queue of a certain size was created
     * @param size the size of the queue.
     */
    public Queue(int size) {
        this.capacity = size > 0 ? size : DEFAULT_CAPACITY;
        this.addStatus = ADD_NIL;
        this.popStatus = POP_NIL;
        this.peekStatus = PEEK_NIL;
        this.storage = new LinkedList<>();
    }

    /**
     * @Constructor
     * Creates a new Queue with a default size
     * @Post-condition: a new empty queue of a default size was created
     */
    public Queue() {
        this(DEFAULT_CAPACITY);
    }

    /* Commands */

    /**
     * @Command
     * Enqueue an element into this queue.
     * @Pre-condition: queue is not completely filled
     * @Post-condition: a new element is enqueued into this queue
     *
     * @param element the element to be enqueued into this queue.
     */
    public void add(E element) {
        if (storage.size() < capacity) {
            storage.addLast(element);
            addStatus = ADD_OK;
        } else {
            addStatus = ADD_ERR;
        }
    }

    /**
     * @Command
     * Removes the element at the head of this queue
     * @Pre-condition: queue is not empty
     * @Post-condition: the head element is removed from the queue
     */
    public void pop() {
        if (storage.isEmpty()) {
            popStatus = POP_ERR;
        } else {
            storage.removeFirst();
            popStatus = POP_OK;
        }
    }

    /**
     * @Command
     * Removes all elements from the queue
     * @Post-condition: all elements are removed from the queue
     */
    public void clear() {
        this.storage.clear();
        this.addStatus = ADD_NIL;
        this.popStatus = POP_NIL;
        this.peekStatus = PEEK_NIL;
    }

    /* Queries */

    /**
     * @Query
     * Retrieves, but does not remove, the head of the queue.
     *
     * @Pre-condition: queue is not empty
     *
     * @return the element from head of this queue
     */
    public E peek() {
        boolean isEmpty = storage.isEmpty();
        peekStatus = isEmpty ? PEEK_ERR : PEEK_OK;
        return isEmpty ? null : storage.getFirst();
    }

    /**
     * @Query
     * Returns the number of elements in this queue
     *
     * @return the current number of elements in the stack
     */
    public int size() {
        return storage.size();
    }

    /**
     * @Query
     * Checks if the queue is empty.
     *
     * @return {@code true} if queue is empty, otherwise {@code false}
     */
    public boolean isEmpty() {
        return storage.isEmpty();
    }


    /**
     * @Query
     * Returns the status of add() command
     *
     * @return one of the following statuses:
     *          {@link #ADD_NIL} if add() was not invoked
     *          {@link #ADD_OK}  if last add() was invoked successfully
     *          {@link #ADD_ERR} if add() has error, the queue is completely filled
     */
    public int getAddStatus() {
        return this.addStatus;
    }

    /**
     * @Query
     * Returns the status of pop() command
     *
     * @return one of the following statuses:
     *          {@link #POP_NIL} if pop() was not invoked
     *          {@link #POP_OK}  if last pop() was invoked successfully
     *          {@link #POP_ERR} if pop() has error, the queue is empty
     */
    public int getPopStatus() {
        return this.popStatus;
    }

    /**
     * @Query
     * Returns the status of peek() request
     *
     * @return one of the following statuses:
     *          {@link #PEEK_NIL} if peek() was not invoked
     *          {@link #PEEK_OK}  if last peek() returned the correct result
     *          {@link #PEEK_ERR} if peek() has error, the queue is empty
     */
    public int getPeekStatus() {
        return this.peekStatus;
    }

}
