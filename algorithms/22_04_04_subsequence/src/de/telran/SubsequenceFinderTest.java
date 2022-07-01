package de.telran;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SubsequenceFinderTest {

    SubsequenceFinder finder;

    public static String text = "Subsequence Finder";

    @Before
    public void init() {
        finder = new SubsequenceFinder();
    }

    @Test
    public void test_lengthOfLongestCommonSubsequence_0() {

        assertEquals(0,finder.lengthOfLongestCommonSubsequence(text, "mm"));
    }
    @Test
    public void test_lengthOfLongestCommonSubsequence_3() {
        assertEquals(3,finder.lengthOfLongestCommonSubsequence(text, "ser"));
    }
    @Test
    public void testIsSubsequence_3lettersOnAfterAnother_3() {
        assertEquals(3,finder.lengthOfLongestCommonSubsequence(text, "ser"));
    }
    @Test
    public void testIsSubsequence_3lettersOnAfterAnother_4() {
        assertEquals(4,finder.lengthOfLongestCommonSubsequence(text, "uebfircmm"));
    }
    @Test
    public void testIsSubsequence_emptyPattern_true() {
        assertTrue(finder.isSubsequence(text, ""));
    }

    @Test
    public void testIsSubsequence_3lettersOnAfterAnother_true() {
        assertTrue(finder.isSubsequence(text, "ser"));
    }

    @Test
    public void testIsSubsequence_3letters_false() {
        assertFalse(finder.isSubsequence(text, "olo"));
    }
}