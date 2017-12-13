package com.codeChallenge;

/**
 * For command line usage.
 *
 */
public class App 
{
    public static void main( String... args ) throws IllegalArgumentException {

        RangeTree rangeTree = new RangeTree();
        for (String range: args) {
            String numbers[] = range.split(",");
            int a = Integer.parseInt(numbers[0].substring(1));
            int b = Integer.parseInt(numbers[1].substring(0,5));
            rangeTree.add(a,b);
        }

        System.out.printf("\n result: %s%n", rangeTree.toString());
    }
}
