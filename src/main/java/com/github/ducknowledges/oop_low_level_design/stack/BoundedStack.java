package com.github.ducknowledges.oop_low_level_design.stack;

import java.util.LinkedList;
import java.util.List;

public class BoundedStack<T> {

    /**
     * Status: push() was not invoked.
     */
    public static final int PUSH_NIL = 0;

    /**
     * Status: last push() was invoked successfully.
     */
    public static final int PUSH_OK = 1;

    /**
     * Status: if push() was invoked when the stack is completely filled
     */
    public static final int PUSH_ERR = 2;

    /**
     * Status: pop() was not invoked
     */
    public static final int POP_NIL = 0;

    /**
     * Status: last pop() was invoked successfully.
     */
    public static final int POP_OK = 1;

    /**
     * Status: if pop() was invoked when the stack is empty.
     */
    public static final int POP_ERR = 2;

    /**
     * Status: peek() was not invoked.
     */
    public static final int PEEK_NIL = 0;

    /**
     * Status: last peek() returned the correct result.
     */
    public static final int PEEK_OK = 1;

    /**
     * Status: if peek() was invoked when the stack is empty.
     */
    public static final int PEEK_ERR = 2;

    private static final int DEFAULT_CAPACITY = 32;

    private final List<T> stack;
    private final int capacity;
    private int pushStatus;
    private int peekStatus;
    private int popStatus;


    /**
     * @Constructor
     * Creates a new BoundedStack with a determined size
     * @Post-condition: a new empty stack of a certain size was created
     * @param size the size of the stack.
     */
    public BoundedStack(int size) {
        this.capacity = size > 0 ? size : DEFAULT_CAPACITY;
        this.pushStatus = PUSH_NIL;
        this.peekStatus = PEEK_NIL;
        this.popStatus = POP_NIL;
        this.stack = new LinkedList<>();
    }

    /**
     * @Constructor
     * Creates a new BoundedStack with a default size
     * @Post-condition: a new empty stack of a default size was created
     */
    public BoundedStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * @Command
     * Pushes an element onto the top of this stack.
     * @Pre-condition: stack is not completely filled
     * @Post-condition: a new element is pushed onto the top of this stack
     *
     * @param element the element to be pushed onto this stack.
     */
    public void push(T element) {
        if (stack.size() < capacity) {
            stack.add(element);
            pushStatus = PUSH_OK;
        } else {
            pushStatus = PUSH_ERR;
        }
    }

    /**
     * @Command
     * Removes the element at the top of this stack
     * @Pre-condition: stack is not empty
     * @Post-condition: the top element is removed from the stack
     */
    public void pop() {
        if (stack.isEmpty()) {
            popStatus = POP_ERR;
        } else {
            stack.remove(stack.size() - 1);
            popStatus = POP_OK;
        }
    }

    /**
     * @Command
     * Removes all elements from the stack
     * @Post-condition: all elements are removed from the stack
     */
    public void clear() {
        this.stack.clear();
        this.pushStatus = PUSH_NIL;
        this.popStatus = POP_NIL;
        this.peekStatus = PEEK_NIL;
    }

    /**
     * @Query
     * Returns the element at the top of this stack without removing it
     * from the stack.
     * @Pre-condition: stack is not empty
     *
     * @return the element at the top of this stack
     */
    public T peek() {
        boolean isNotEmpty = !stack.isEmpty();
        peekStatus = isNotEmpty ? PEEK_OK : PEEK_ERR;
        return isNotEmpty ? stack.get(stack.size() - 1) : null;
    }

    /**
     * @Query
     * Returns the number of elements in this stack
     *
     * @return the current number of elements in the stack
     */
    public int size() {
        return this.stack.size();
    }

    /**
     * @Query
     * Returns the status of push() request
     *
     * @return one of the following statuses:
     *          {@link #PUSH_NIL} if push() was not invoked
     *          {@link #PUSH_OK}  if last push() was invoked successfully
     *          {@link #PUSH_ERR} if push() has error, the stack is completely filled
     */
    public int getPushStatus() {
        return this.pushStatus;
    }

    /**
     * @Query
     * Returns the status of pop() request
     *
     * @return one of the following statuses:
     *          {@link #POP_NIL} if pop() was not invoked
     *          {@link #POP_OK}  if last pop() was invoked successfully
     *          {@link #POP_ERR} if pop() has error, the stack is empty
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
     *          {@link #PEEK_ERR} if peek() has error, the stack is empty
     */
    public int getPeekStatus() {
        return this.peekStatus;
    }
}
