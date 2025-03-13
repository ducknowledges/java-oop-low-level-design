package com.github.ducknowledges.oop_low_level_design.set;

import com.github.ducknowledges.oop_low_level_design.hashtable.HashTable;

public class PowerSet<E> extends HashTable<E> {

    /**
     * @Constructor
     * Creates a new set with initial capacity
     * @Post-condition: a new empty set with initial capacity was created
     */
    public PowerSet(int capacity) {
        super(capacity);
    }

    /**
     * @Query
     * Returns a new PowerSet containing elements that exist in both this set and the specified set
     *
     * @param set the set to be intersected with this set
     * @return the intersection of the current set with set in the argument
     */
    public PowerSet<E> intersection(PowerSet<E> set) {
        PowerSet<E> result = new PowerSet<>(this.size());

        PowerSet<E> smallerSet = (this.size() <= set.size()) ? this : set;
        PowerSet<E> largerSet = (this.size() <= set.size()) ? set : this;

        for (E element : smallerSet.slots) {
            if (element != null && largerSet.contains(element)) {
                result.put(element);
            }
        }

        return result;
    }

    /**
     * @Query
     * Returns a new PowerSet containing all elements from both this set and the specified set
     *
     * @param set the set to be combined with this set
     * @return the union of the current set with set in the argument
     */
    public PowerSet<E> union(PowerSet<E> set) {
        PowerSet<E> result = new PowerSet<>(this.size() + set.size());

        for (E element : this.slots) {
            if (element != null) {
                result.put(element);
            }
        }

        for (E element : set.slots) {
            if (element != null) {
                result.put(element);
            }
        }

        return result;
    }

    /**
     * @Query
     * Returns a new PowerSet containing elements from this set that are not present in the specified set
     *
     * @param set the set whose elements are to be excluded from this set
     * @return the difference between the current set and the set in the argument
     */
    public PowerSet<E> difference(PowerSet<E> set) {
        PowerSet<E> result = new PowerSet<>(this.size());

        for (E element : this.slots) {
            if (element != null && !set.contains(element)) {
                result.put(element);
            }
        }

        return result;
    }


    /**
     * @Query
     * Checking whether set in argument is a subset of the current set
     *
     * @param set the set to be checked for being a subset of this set
     * @return {@code true} if the specified set is a subset of this set, {@code false} otherwise
     */
    public boolean isSubset(PowerSet<E> set) {
        if (this.size() < set.size()) {
            return false;
        }

        for (E element : set.slots) {
            if (element != null && !this.contains(element)) {
                return false;
            }
        }

        return true;
    }

}
