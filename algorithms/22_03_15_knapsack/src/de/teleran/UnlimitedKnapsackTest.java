package de.teleran;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnlimitedKnapsackTest {

    UnlimitedKnapsack unk = new UnlimitedKnapsack();

    @Test
    public void test_U_245() {
        int value[] = new int[] { 80, 110, 135 };

        int weight[] = new int[] { 15, 20, 35 };

        int W = 60;

        assertEquals(245, unk.getMaximumKnapsackValue(60, weight,value,3));

    }

    @Test
    public void test_U_60() {
        int value[] = new int[] { 25, 30, 15 };
        int weight[] = new int[] { 15, 5, 10 };
        int w = 60;

        assertEquals(70, unk.getMaximumKnapsackValue(60, weight,value,3));

    }
    @Test
    public void test_U_5() {
        int value[] = new int[] { 25, 30, 15 };
        int weight[] = new int[] { 15, 5, 10 };
        int w = 5;

        assertEquals(30, unk.getMaximumKnapsackValue(5, weight,value,3));

    }
    @Test
    public void test_U_3() {
        int value[] = new int[] { 25, 30, 15 };
        int weight[] = new int[] { 15, 5, 10 };
        int w = 3;

        assertEquals(0, unk.getMaximumKnapsackValue(3, weight,value,3));

    }
}