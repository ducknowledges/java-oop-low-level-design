package com.github.ducknowledges.oop_low_level_design.bloomfilter;

public class BloomFilter<T> {

    private static final long BIT = 1L;

    private final int filterLen;
    private long binaryFilter;

    /**
     * @Constructor
     * Creates a new bloom filter with initial length
     * @Post-condition: a new empty bloom filter with initial length was created
     */
    public BloomFilter(int filterLen) {
        this.filterLen = filterLen;
        this.binaryFilter = 0;
    }

    /**
     * @Command
     * Add value to the bloom filter
     *
     * @Post-condition: a new value has been added into the bloom filter
     *
     * @param value the value to be added into the bloom filter.
     */
    public void add(T value) {
        binaryFilter |= BIT << hashCode1(value);
        binaryFilter |= BIT << hashCode2(value);
    }

    /**
     * @Query
     * Checks if the specified value is present in the bloom filter
     * False positive result is allowed
     *
     * @param value the value to check for existence in this bloom filter
     * @return {@code true} if the element is present in the bloom filter, {@code false} otherwise
     */
    public boolean hasValue(T value) {
        boolean isSetFilterBit1 = (binaryFilter >> hashCode1(value) & BIT) == BIT;
        boolean isSetFilterBit2 = (binaryFilter >> hashCode2(value) & BIT) == BIT;
        return isSetFilterBit1 && isSetFilterBit2;
    }


    private int hashCode1(T value) {
        return Math.abs(value.hashCode() % filterLen);
    }

    private int hashCode2(T value) {
        return Math.abs(value.hashCode() % filterLen);
    }

}
