package de.telran;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        List<Integer> sequence = Arrays.asList(1, 3, 6);
        List<Integer> global = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Iterator<Integer> iter = global.iterator();
        boolean subSequence = sequence.stream().allMatch(itemSequence -> {
            return Stream.generate(iter::next)
                    .anyMatch(itemGlobal -> itemSequence.equals(itemGlobal));
        });
        System.out.println(subSequence);
    }
}
