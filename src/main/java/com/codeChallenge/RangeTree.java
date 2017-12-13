package com.codeChallenge;

import java.util.Stack;

/**
 * Implementation of interval tree, with no overlapping.
 * Ranges that overlapping are merged.
 */
public class RangeTree {

    private Range root;

    /**
     * Adding range to the tree
     * @param lower
     * @param upper
     * @throws IllegalAccessException
     */
    public void add(int lower, int upper) throws IllegalArgumentException {

        //Validation of the entry
        if (lower > upper){
            throw new IllegalArgumentException(" Error: lower: " + lower + " should be less than upper: " + upper);
        } else if ( 10000 > lower || lower > 99999){
            throw new IllegalArgumentException(" Error Lower must be 5 digits: " + lower);
        } else if (10000 > upper || upper > 99999){
            throw new IllegalArgumentException(" Error Lower must be 5 digits: " + upper);
        }

        Range current = this.root;
        while (current != null){
            if ( upper > current.getMax()) {
                current.setMax(upper);
            }
            //Check if overlapping, remove existing add merged values.
            if (lower <= current.getUpper() && upper >= current.getLower()){

                if (lower > current.getLower()){
                    lower = current.getLower();
                }
                if (upper < current.getUpper()){
                    upper = current.getUpper();
                }
                this.root = remove(current, this.root);
                add(lower, upper);
                return;
            }
            //Creating balanced tree smaller to the left, bigger to the right.
            if (lower < current.getLower()){
                if (current.getLeft() == null){

                    current.setLeft(new Range(lower, upper, null, null, upper));
                    return;
                }
                current = current.getLeft();
            } else {
                if (current.getRight() == null){
                    current.setRight(new Range(lower, upper, null, null, upper));
                    return;
                }
                current = current.getRight();
            }
        }
        //empty tree, adding root
        root = new Range(lower, upper, null, null, upper);
    }

    /**
     * Removing existing range
     * @param current
     * @param root
     * @return
     */
    private Range remove(Range current, Range root) {
        Range range, range2;
        //removing root
        if (root.equals(current)) {
            if (root.getLeft() == null && root.getRight() == null) {
                return null;
            } else if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            } else {
                range = root.getRight();
                range2 = root.getRight();
                while (range.getLeft() != null) {
                    range = range.getLeft();
                }
                range.setLeft(root.getLeft());
                return range2;
            }
        }
        //in case node is dipper than root, using recursive call to remove.
        if (current.getMax() < root.getMax()) {
            root.setLeft(remove(current, root.getLeft()));

        } else {
            root.setRight(remove(current, root.getRight()));
        }
        return root;

    }

    /**
     * In order traversal to return result.
     * @return
     */
    public String toString(){
        StringBuilder result = new StringBuilder();
        if (root == null){
            return " ";
        }
        Range current = root;
        Stack<Range> stack = new Stack<Range>();
        while (current != null || !stack.empty()){
            if (current != null){
                stack.push(current);
                current = current.getLeft();

            } else {
                Range range = stack.pop();
                result.append(range.toString()).append(" ");
                current = range.getRight();
                }
            }
        return result.toString();
    }

}
