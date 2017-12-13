package com.codeChallenge;

import com.codeChallenge.RangeTree;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class RangeTreeTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void simpleAddFromTaskWithOverlapping() throws IllegalArgumentException {
        RangeTree rangeTree = new RangeTree();
        rangeTree.add(94133, 94133);
        rangeTree.add(94200, 94299);
        rangeTree.add(94600, 94699);

        assertThat("[94133,94133] [94200,94299] [94600,94699] ",is(rangeTree.toString()));
    }

    @Test
    public void  simpleAddFromTask() throws IllegalArgumentException {
        RangeTree rangeTree = new RangeTree();
        rangeTree.add(94133, 94133);
        rangeTree.add(94200, 94299);
        rangeTree.add(94226, 94399);

        assertThat("[94133,94133] [94200,94399] ",is(rangeTree.toString()));
    }

    @Test
    public void addMaxRangesPossible() throws IllegalArgumentException {
        RangeTree rangeTree = new RangeTree();
        StringBuilder in = new StringBuilder();
        for (int i = 10000; i < 99998; i = i + 2) {
            int a = i;
            int b = i + 1;
            in.append("[").append(a).append(",").append(b).append("] ");
            rangeTree.add(a, b);
        }
        assertThat(in.toString(),is((rangeTree.toString())));
    }

    @Test
    public void addWithRandomNumberGenerator() throws IllegalArgumentException {
        try {
            RangeTree rangeTree = new RangeTree();
            Random rand = new Random();
            for (int i = 1; i < 100000; i++) {
                int a = rand.nextInt((99999 - 10000) + 1) + 10000;
                int b;
                do {
                    b = rand.nextInt((99999 - 10000) + 1) + 10000;
                } while (b < a);
                rangeTree.add(a, b);
            }
        } catch (Exception e){
            fail("No reason to fail!");
        }
    }

    @Test
    public void addLowerBiggerThanUpper() throws IllegalArgumentException {
        exception.expect(IllegalArgumentException.class);
        RangeTree rangeTree = new RangeTree();
        rangeTree.add(94143, 94135);
    }

    @Test
    public void addInvalidLower() throws IllegalArgumentException {
        exception.expect(IllegalArgumentException.class);
        RangeTree rangeTree = new RangeTree();
        rangeTree.add(9414, 94135);

    }

    @Test
    public void addInvalidUpper() throws IllegalArgumentException {
        exception.expect(IllegalArgumentException.class);
        RangeTree rangeTree = new RangeTree();
        rangeTree.add(94141, 941352);

    }
}
