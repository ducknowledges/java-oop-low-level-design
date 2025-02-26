package com.github.ducknowledges.oop_low_level_design.two_way_list;

import java.util.Objects;

public abstract class ParentList<E> {

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
    /* Status: last right() was invoked when the current element is last or list is empty. */
    public static final int RIGHT_ERR = 2;

    /* Status: putRight() was not invoked */
    public static final int PUT_RIGHT_NIL = 0;
    /* Status: putRight() was invoked successfully */
    public static final int PUT_RIGHT_OK = 1;
    /* Status: putRight() was invoked when the list is empty */
    public static final int PUT_RIGHT_ERR = 2;

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
    /* Status: find() was invoked when the list is empty or element not found*/
    public static final int FIND_ERR = 2;

    /* Status: get() was not invoked */
    public static final int GET_NIL = 0;
    /* Status: get() was invoked successfully */
    public static final int GET_OK = 1;
    /* Status: get() was invoked when the list is empty*/
    public static final int GET_ERR = 2;

    private final DumbHead<E> dumbHead;
    private final DumbTail<E> dumbTail;
    protected Node<E> current;
    private int size;

    private int tailStatus;
    private int headStatus;
    private int rightStatus;
    private int putRightStatus;
    private int putLeftStatus;
    private int removeStatus;
    private int addToEmptyStatus;
    private int replaceStatus;
    private int findStatus;
    private int getStatus;

    /** Constructor
     * Creates a new ParentList
     *
     * @Post-condition: a new empty list was created
     */
    public ParentList() {
        this.dumbHead = new DumbHead<>();
        this.dumbTail = new DumbTail<>();
        this.dumbHead.setNext(dumbTail);
        this.dumbTail.setPrev(dumbHead);
        this.size = 0;
        this.current = null;

        this.headStatus = HEAD_NIL;
        this.tailStatus = TAIL_NIL;
        this.rightStatus = RIGHT_NIL;
        this.putRightStatus = PUT_RIGHT_NIL;
        this.putLeftStatus = PUT_LEFT_NIL;
        this.removeStatus = REMOVE_NIL;
        this.addToEmptyStatus = ADD_TO_EMPTY_NIL;
        this.replaceStatus = REPLACE_NIL;
        this.findStatus = FIND_NIL;
        this.getStatus = GET_NIL;
    }

    /* Command */

    /**
     * @Command
     * Sets the first element as current.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: first element becomes current
     *
     */
    void head() {
        if (isValue()) {
            this.current = dumbHead.getNext();
            this.headStatus = HEAD_OK;
        } else {
            this.headStatus = HEAD_ERR;
        }
    }

    /**
     * @Command
     * Sets the last element as current.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: last element becomes current
     *
     */
    void tail() {
        if (isValue()) {
            this.current = dumbTail.getPrev();
            this.tailStatus = TAIL_OK;
        } else {
            this.tailStatus = TAIL_ERR;
        }
    }

    /**
     * @Command
     * Sets the next element as current.
     *
     * @Pre-condition: the list is not empty && and the current element is not last
     * @Post-condition: next element becomes current
     *
     */
    void right() {
        if (isValue() && !isTail()) {
            this.current = this.current.getNext();
            this.rightStatus = RIGHT_OK;
        } else {
            this.rightStatus = RIGHT_ERR;
        }
    }

    /**
     * @Command
     * Put a new element after the current element.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: new element is putted after current element
     *
     * @param element the element to be putted after the current node
     */
    void putRight(E element) {
        if (isValue()) {
            Node<E> currentNode = this.current;
            Node<E> newNode = new Node<>(element, currentNode.getNext(), currentNode);
            currentNode.getNext().setPrev(newNode);
            currentNode.setNext(newNode);
            this.size++;
            this.putRightStatus = PUT_RIGHT_OK;
        } else {
            this.putRightStatus = PUT_RIGHT_ERR;
        }
    }

    /**
     * @Command
     * Put a new element before the current element.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: new element is putted before the current element
     *
     * @param element the element to be putted before the current element
     */
    void putLeft(E element) {
        if (isValue()) {
            Node<E> currentNode = this.current;
            Node<E> newNode = new Node<>(element, currentNode, currentNode.getPrev());
            currentNode.getPrev().setNext(newNode);
            currentNode.setPrev(newNode);
            this.putLeftStatus = PUT_LEFT_OK;
        } else {
            this.putLeftStatus = PUT_LEFT_ERR;
        }
    }

    /**
     * @Command
     * Removes the current element.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: current element is removed.
     *                  - if next element exists, it becomes current,
     *                  - otherwise if previous element exists, it becomes current
     *                  - if was removed last element in the list, then current element is empty
     */
    void remove() {
        if (isValue()) {
            Node<E> prevNode = this.current.getPrev();
            Node<E> nextNode = this.current.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrev(prevNode);

            if (isTail() && isHead()) {
                this.current = null;
            } else {
                this.current = isTail() ? prevNode : nextNode;
            }

            this.size--;
            this.removeStatus = REMOVE_OK;
        } else {
            this.removeStatus = REMOVE_ERR;
        }
    }

    /**
     * @Command
     * Adds an element to an empty list.
     *
     * @Pre-condition: the list is empty
     * @Post-condition: element is added to the list and becomes current
     *
     * @param element the element to be added to the empty list
     */
    void addToEmpty(E element) {
        if (isValue()) {
            this.addToEmptyStatus = ADD_TO_EMPTY_ERR;
        } else {
            this.current = new Node<>(element, this.dumbTail, this.dumbHead);
            this.dumbHead.setNext(current);
            this.dumbTail.setPrev(current);
            this.size++;
            this.addToEmptyStatus = ADD_TO_EMPTY_OK;
        }
    }

    /**
     * @Command
     * Adds an element to the end of the list.
     *
     * @Post-condition: element is added to the end of the list
     *
     * @param element the element to be added to the end of the list
     */
    void addTail(E element) {
        if (isValue()) {
            Node<E> newNode = new Node<>(element, this.dumbTail, this.dumbTail.getPrev());
            this.dumbTail.getPrev().setNext(newNode);
            this.dumbTail.setPrev(newNode);
            this.size++;
        } else {
            this.addToEmpty(element);
        }
    }

    /**
     * @Command
     * Replaces current element with a new element.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: current element is replaced with the new element
     *
     * @param element the new element that replaces the current element
     */
    void replace(E element) {
        if (isValue()) {
            this.current.setElement(element);
            this.replaceStatus = REPLACE_OK;
        } else {
            this.replaceStatus = REPLACE_ERR;
        }
    }

    /**
     * @Command
     * Finds for the next equivalent of an element, and make it current element.
     *
     * @Pre-condition: the list is not empty
     * @Post-condition: if element is found, it becomes current,
     *                  otherwise current position remains unchanged
     *
     * @param element the element to search for in the list
     */
    void find(E element) {
        this.findStatus = FIND_ERR;
        if (isValue()) {
            Node<E> currentNode = this.current;
            while (!(currentNode.getNext() instanceof DumbTail)) {
                currentNode = currentNode.getNext();
                if ( Objects.equals(currentNode.getElement(), element)) {
                    this.current = currentNode;
                    this.findStatus = FIND_OK;
                    return;
                }
            }
        }
    }

    /**
     * @Command
     * Removes all equivalents of the specified element.
     *
     * @Post-condition: all equivalents of the specified element are removed
     *                  set the current element as last
     *                  if was removed last element in the list, then current element is empty
     *
     * @param element the all equivalents of element to be removed from the list
     */
    void removeAll(E element) {
        if (!isValue()) {
            return;
        }

        head();

        while (isValue() && !isTail()) {
            if (Objects.equals(get(), element)) {
                remove();
            } else {
                right();
            }
        }

        if (isValue() && Objects.equals(get(), element)) {
            remove();
        }
    }

    /**
     * @Command
     * Clears the list, removing all elements.
     *
     * @Post-condition: the list is empty, current element is empty
     *
     */
    void clear() {
        this.size = 0;
        this.current = null;
        this.dumbHead.setNext(dumbTail);
        this.dumbTail.setPrev(dumbHead);

        this.headStatus = HEAD_NIL;
        this.tailStatus = TAIL_NIL;
        this.rightStatus = RIGHT_NIL;
        this.putRightStatus = PUT_RIGHT_NIL;
        this.putLeftStatus = PUT_LEFT_NIL;
        this.removeStatus = REMOVE_NIL;
        this.addToEmptyStatus = ADD_TO_EMPTY_NIL;
        this.replaceStatus = REPLACE_NIL;
        this.findStatus = FIND_NIL;
        this.getStatus = GET_NIL;
    }


    /* Query */

    /**
     * @Query
     * Returns the current element.
     *
     * @Pre-condition: list is not empty
     *
     * @return the current element
     */
    E get() {
        if (isValue()) {
            this.getStatus = GET_OK;
            return this.current.getElement();
        } else {
            this.getStatus = GET_ERR;
            return null;
        }
    }

    /**
     * @Query
     * Returns the size of the list
     *
     * @return number of values in the list
     */
    int size() {
        return size;
    }

    /**
     * @Query
     * Checks if the current element is the first element
     *
     * @return {@code true} if the current element is the first element of the list,
     *          otherwise {@code false}
     */
    boolean isHead() {
        if (isValue()) {
            return this.current.getPrev() instanceof DumbHead<E>;
        }
        return false;
    }

    /**
     * @Query
     * Checks if the current element is the last element
     *
     * @return {@code true} if the current element is the last element of the list,
     *          otherwise {@code false}
     */
    boolean isTail() {
        if (isValue()) {
            return this.current.getNext() instanceof DumbTail<E>;
        }
        return false;
    }

    /**
     * @Query
     * Checks if the list is not empty.
     *
     * @return {@code true} if list is not empty, otherwise {@code false}
     */
    boolean isValue() {
        return this.current != null;
    }

    /**
     * @Query
     * Returns the status of head() request
     *
     * @return one of the following statuses:
     *          {@link #HEAD_NIL}
     *          {@link #HEAD_OK}
     *          {@link #HEAD_ERR}
     */
    int getHeadStatus() {
        return headStatus;
    }

    /**
     * @Query
     * Returns the status of tail() request
     *
     * @return one of the following statuses:
     *          {@link #TAIL_NIL}
     *          {@link #TAIL_OK}
     *          {@link #TAIL_ERR}
     */
    int getTailStatus() {
        return tailStatus;
    }

    /**
     * @Query
     * Returns the status of right() request
     *
     * @return one of the following statuses:
     *          {@link #RIGHT_NIL}
     *          {@link #RIGHT_OK}
     *          {@link #RIGHT_ERR}
     */
    int getRightStatus() {
        return rightStatus;
    }

    /**
     * @Query
     * Returns the status of putRight() request
     *
     * @return one of the following statuses:
     *          {@link #PUT_RIGHT_NIL}
     *          {@link #PUT_RIGHT_OK}
     *          {@link #PUT_RIGHT_ERR}
     */
    int getPutRightStatus() {
        return putRightStatus;
    }

    /**
     * @Query
     * Returns the status of putLeft() request
     *
     * @return one of the following statuses:
     *          {@link #PUT_LEFT_NIL}
     *          {@link #PUT_LEFT_OK}
     *          {@link #PUT_LEFT_ERR}
     */
    public int getPutLeftStatus() {
        return putLeftStatus;
    }

    /**
     * @Query
     * Returns the status of remove() request
     *
     * @return one of the following statuses:
     *          {@link #REMOVE_NIL}
     *          {@link #REMOVE_OK}
     *          {@link #REMOVE_ERR}
     */
    int getRemoveStatus() {
        return removeStatus;
    }

    /**
     * @Query
     * Returns the status of addToEmpty() request
     *
     * @return one of the following statuses:
     *          {@link #ADD_TO_EMPTY_NIL}
     *          {@link #ADD_TO_EMPTY_OK}
     *          {@link #ADD_TO_EMPTY_ERR}
     */
    int getAddToEmptyStatus() {
        return addToEmptyStatus;
    }

    /**
     * @Query
     * Returns the status of replace() request
     *
     * @return one of the following statuses:
     *          {@link #REPLACE_NIL}
     *          {@link #REPLACE_OK}
     *          {@link #REPLACE_ERR}
     */
    int getReplaceStatus() {
        return replaceStatus;
    }

    /**
     * @Query
     * Returns the status of find() request
     *
     * @return one of the following statuses:
     *          {@link #FIND_NIL}
     *          {@link #FIND_OK}
     *          {@link #FIND_ERR}
     */
    int getFindStatus() {
        return this.findStatus;
    }

    /**
     * @Query
     * Returns the status of get() request
     *
     * @return one of the following statuses:
     *          {@link #GET_NIL}
     *          {@link #GET_OK}
     *          {@link #GET_ERR}
     */
    int getGetStatus() {
        return this.getStatus;
    }

    protected static class Node<E> {
        private E element;
        private Node<E> next;
        private Node<E> prev;

        Node(E element, Node<E> next, Node<E> prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        E getElement() {
            return element;
        }

        void setElement(E element) {
            this.element = element;
        }

        Node<E> getNext() {
            return next;
        }

        void setNext(Node<E> next) {
            this.next = next;
        }

        Node<E> getPrev() {
            return prev;
        }

        void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    private static class DumbHead<E> extends Node<E> {
        DumbHead() {
            super(null, null, null);
        }
    }

    private static class DumbTail<E> extends Node<E> {
        DumbTail() {
            super(null, null, null);
        }
    }
}



class LinkedList<T> extends ParentList<T> {

    /** Constructor
     * Creates a new LinkedList
     *
     * @Post-condition: a new empty list was created*
     */
    public LinkedList() {}

}

class TwoWayList<T> extends ParentList<T> {

    /* Status: left() was not invoked */
    public static final int LEFT_NIL = 0;
    /* Status: last left() was invoked successfully. */
    public static final int LEFT_OK = 1;
    /* Status: last left() was invoked when the current element is first or list is empty. */
    public static final int LEFT_ERR = 2;

    private int leftStatus;

    /** Constructor
     * Creates a new TwoWayList
     *
     * @Post-condition: a new empty list was created*
     */
    public TwoWayList() {
        super();
        this.leftStatus = LEFT_NIL;
    }

    /**
     * @Command
     * Sets the next element as current.
     *
     * @Pre-condition: the list is not empty && and the current element is not first
     * @Post-condition: previous element becomes current
     *
     */
    void left() {
        if (isValue() && !isHead()) {
            this.current = this.current.getPrev();
            this.leftStatus = LEFT_OK;
        } else {
            this.leftStatus = LEFT_ERR;
        }
    }

    /**
     * @Query
     * Returns the status of left() request
     *
     * @return one of the following statuses:
     *          {@link #LEFT_NIL}
     *          {@link #LEFT_OK}
     *          {@link #LEFT_ERR}
     */
    public int getLeftStatus() {
        return this.leftStatus;
    }
}




