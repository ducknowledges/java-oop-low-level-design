package com.github.ducknowledges.oop_low_level_design.dequeue;

import java.util.LinkedList;

public abstract class ParentQueue<E> {

    /** Status: addLast() was not invoked. */
    public static final int ADD_LAST_NIL = 0;
    /** Status: last addLast() was invoked successfully. */
    public static final int ADD_LAST_OK = 1;
    /** Status: if addLast() was invoked when the queue is completely filled*/
    public static final int ADD_LAST_ERR = 2;

    /** Status: removeFirst() was not invoked. */
    public static final int REMOVE_FIRST_NIL = 0;
    /** Status: last removeFirst() was invoked successfully. */
    public static final int REMOVE_FIRST_OK = 1;
    /** Status: if removeFirst() was invoked when the queue is empty*/
    public static final int REMOVE_FIRST_ERR = 2;

    /** Status: peekFirst() was not invoked. */
    public static final int PEEK_FIRST_NIL = 0;
    /** Status: last peekFirst() was invoked successfully. */
    public static final int PEEK_FIRST_OK = 1;
    /** Status: if peekFirst() was invoked when the queue is empty*/
    public static final int PEEK_FIRST_ERR = 2;

    protected static final int DEFAULT_CAPACITY = 32;

    protected final java.util.Deque<E> storage;
    protected final int capacity;
    private int addLastStatus;
    private int removeFirstStatus;
    private int peekFirstStatus;


    /**
     * @Constructor
     * Creates a new queue with a determined size
     * @Post-condition: a new empty queue of a certain size was created
     * @param size the size of the queue.
     */
    public ParentQueue(int size) {
        this.capacity = size > 0 ? size : DEFAULT_CAPACITY;
        this.addLastStatus = ADD_LAST_NIL;
        this.removeFirstStatus = REMOVE_FIRST_NIL;
        this.peekFirstStatus = REMOVE_FIRST_OK;
        this.storage = new LinkedList<>();
    }

    /**
     * @Constructor
     * Creates a new queue with a default size
     * @Post-condition: a new empty queue of a default size was created
     */
    public ParentQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @Command
     * Add an element into end of this queue.
     * @Pre-condition: queue is not completely filled
     * @Post-condition: a new element is added into this queue
     *
     * @param element the element to be added into end this queue.
     */
    void addLast(E element) {
        if (storage.size() < capacity) {
            storage.addLast(element);
            addLastStatus = ADD_LAST_OK;
        } else {
            addLastStatus = ADD_LAST_ERR;
        }
    }

    /**
     * @Command
     * Removes the element at the head of this queue
     * @Pre-condition: queue is not empty
     * @Post-condition: the head element is removed from the queue
     */
    void removeFirst() {
        if (storage.isEmpty()) {
            removeFirstStatus = REMOVE_FIRST_ERR;
        } else {
            storage.removeFirst();
            removeFirstStatus = REMOVE_FIRST_OK;
        }
    }

    /**
     * @Command
     * Removes all elements from the queue
     * @Post-condition: all elements are removed from the queue
     */
    public void clear() {
        this.storage.clear();
        this.addLastStatus = ADD_LAST_NIL;
        this.removeFirstStatus = REMOVE_FIRST_NIL;
        this.peekFirstStatus = PEEK_FIRST_NIL;
    }

    /**
     * @Query
     * Retrieves, but does not remove, the head of the queue.
     *
     * @Pre-condition: queue is not empty
     *
     * @return the element from head of this queue
     */
    E peekFirst() {
        boolean isEmpty = storage.isEmpty();
        peekFirstStatus = isEmpty ? PEEK_FIRST_ERR : PEEK_FIRST_OK;
        return isEmpty ? null : storage.getFirst();
    }

    /**
     * @Query
     * Checks if the queue is empty.
     *
     * @return {@code true} if queue is empty, otherwise {@code false}
     */
    boolean isEmpty() {
        return this.storage.isEmpty();
    }

    /**
     * @Query
     * Returns the size of the queue
     *
     * @return number of values in the queue
     */
    int size() {
        return this.storage.size();
    }

    /**
     * @Query
     * Returns the status of addFirst() request
     *
     * @return one of the following statuses:
     *          {@link #ADD_LAST_NIL}
     *          {@link #ADD_LAST_OK}
     *          {@link #ADD_LAST_ERR}
     */
    int getAddLastStatus() {
        return this.addLastStatus;
    }

    /**
     * @Query
     * Returns the status of removeFirst() request
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_FIRST_NIL}
     *          {@link #REMOVE_FIRST_OK}
     *          {@link #REMOVE_FIRST_ERR}
     */
    int getRemoveFirstStatus() {
        return this.removeFirstStatus;
    }

    /**
     * @Query
     * Returns the status of peekFirst() request
     *
     * @return one of the following statuses:
     *          {@link #PEEK_FIRST_NIL}
     *          {@link #PEEK_FIRST_OK}
     *          {@link #PEEK_FIRST_ERR}
     */
    int getPeekFirstStatus() {
        return this.peekFirstStatus;
    }

}

class Queue<E> extends ParentQueue<E> {

    /**
     * @Constructor
     * Creates a new queue with a determined size
     * @Post-condition: a new empty queue of a certain size was created
     * @param size the size of the queue.
     */
    public Queue(int size) {
        super(size);
    }

    /**
     * @Constructor
     * Creates a new queue with a default size
     * @Post-condition: a new empty queue of a default size was created
     */
    public Queue() {
        super();
    }
}

class Deque<E> extends ParentQueue<E> {

    /** Status: addFirst() was not invoked. */
    public static final int ADD_FIRST_NIL = 0;
    /** Status: last addFirst() was invoked successfully. */
    public static final int ADD_FIRST_OK = 1;
    /** Status: if addFirst() was invoked when the deque is completely filled*/
    public static final int ADD_FIRST_ERR = 2;

    /** Status: removeLast() was not invoked. */
    public static final int REMOVE_LAST_NIL = 0;
    /** Status: last removeLast() was invoked successfully. */
    public static final int REMOVE_LAST_OK = 1;
    /** Status: if removeLast() was invoked when the deque is empty*/
    public static final int REMOVE_LAST_ERR = 2;

    /** Status: peekLast() was not invoked. */
    public static final int PEEK_LAST_NIL = 0;
    /** Status: last peekLast() was invoked successfully. */
    public static final int PEEK_LAST_OK = 1;
    /** Status: if peekLast() was invoked when the deque is empty*/
    public static final int PEEK_LAST_ERR = 2;

    private int addFirstStatus;
    private int removeLastStatus;
    private int peekLastStatus;

    /**
     * @Constructor
     * Creates a new queue with a determined size
     * @Post-condition: a new empty queue of a certain size was created
     * @param size the size of the queue.
     */
    public Deque(int size) {
        super(size);
        this.addFirstStatus = ADD_FIRST_NIL;
        this.removeLastStatus = REMOVE_LAST_NIL;
        this.peekLastStatus = REMOVE_LAST_NIL;
    }

    /**
     * @Constructor
     * Creates a new queue with a default size
     * @Post-condition: a new empty queue of a default size was created
     */
    public Deque() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @Command
     * Add first an element into head of this queue.
     * @Pre-condition: deque is not completely filled
     * @Post-condition: a new element is added into head of this dequeue
     *
     * @param element the element to be enqueued into this queue.
     */
    void addFirst(E element) {
        if (this.storage.size() < capacity) {
            storage.addLast(element);
            addFirstStatus = ADD_FIRST_OK;
        } else {
            addFirstStatus = ADD_FIRST_ERR;
        }
    }

    /**
     * @Command
     * Removes the element at the end of this queue
     * @Pre-condition: queue is not empty
     * @Post-condition: the last element is removed from the dequeue
     */
    void removeLast() {
        if (storage.isEmpty()) {
            removeLastStatus = REMOVE_LAST_ERR;
        } else {
            storage.removeFirst();
            removeLastStatus = REMOVE_LAST_OK;
        }
    }

    /**
     * @Command
     * Removes all elements from the queue
     * @Post-condition: all elements are removed from the queue
     */
    public void clear() {
        super.clear();
        this.addFirstStatus = ADD_FIRST_NIL;
        this.removeLastStatus = REMOVE_LAST_NIL;
        this.peekLastStatus = PEEK_LAST_NIL;
    }

    /**
     * @Query
     * Retrieves, but does not remove, the last element of the deque.
     *
     * @Pre-condition: deque is not empty
     *
     * @return the element from end of this deque
     */
    E peekLast() {
        boolean isEmpty = storage.isEmpty();
        peekLastStatus = isEmpty ? PEEK_LAST_ERR : PEEK_LAST_OK;
        return isEmpty ? null : storage.getLast();
    }

    /**
     * @Query
     * Returns the status of addFirst() request
     *
     * @return one of the following statuses:
     *          {@link #ADD_FIRST_NIL}
     *          {@link #ADD_FIRST_OK}
     *          {@link #ADD_FIRST_ERR}
     */
    int getFindStatus() {
        return this.addFirstStatus;
    }

    /**
     * @Query
     * Returns the status of removeLast() request
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_LAST_NIL}
     *          {@link #REMOVE_LAST_OK}
     *          {@link #REMOVE_LAST_ERR}
     */
    int getRemoveLastStatus() {
        return this.removeLastStatus;
    }

    /**
     * @Query
     * Returns the status of peekLast() request
     *
     * @return one of the following statuses:
     *          {@link #PEEK_LAST_NIL}
     *          {@link #PEEK_LAST_OK}
     *          {@link #PEEK_LAST_ERR}
     */
    int getPeekLastStatus() {
        return this.peekLastStatus;
    }
}
