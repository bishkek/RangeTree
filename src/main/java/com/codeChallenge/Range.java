package com.codeChallenge;

import java.util.Objects;

/**
 * Node for the RangeTree, to hold one range of ZipCodes
 */
public class Range {

    private int lower;
    private int upper;
    private Range left;
    private Range right;
    private int max;

    public Range(int lower, int upper, Range left, Range right, int max) {
        this.lower = lower;
        this.upper = upper;
        this.left = left;
        this.right = right;
        this.max = max;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public Range getLeft() {
        return left;
    }

    public void setLeft(Range left) {
        this.left = left;
    }

    public Range getRight() {
        return right;
    }

    public void setRight(Range right) {
        this.right = right;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "["+lower+","+upper+"]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return getLower() == range.getLower() &&
                getUpper() == range.getUpper();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLower(), getUpper());
    }
}
