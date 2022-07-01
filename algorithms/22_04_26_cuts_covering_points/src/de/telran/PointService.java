package de.telran;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PointService {

    Map<Integer, Integer> getCoveringCutsNumberByPoint(List<Integer> numbers, List<Cut> cuts) {
        List<Point> points = collectPoints(numbers, cuts);
        Map<Integer, Integer> res = new HashMap<>();
        int currentCover = 0;
        for (Point point : points) {
            if (point.type == PointType.NUMBER) {
                res.put(point.value, currentCover);
            } else if (point.type == PointType.LEFT) {
                currentCover++;
            } else {
                currentCover--;
            }

        }
        return res;
    }




    private List<Point> collectPoints(List<Integer> numbers, List<Cut> cuts) {
        List<Point> points = numbers.stream()
                .map(num -> new Point(num, PointType.NUMBER))
                .collect(Collectors.toList());

        points.addAll(cuts.stream()
                .flatMap(cut -> Stream.of(
                        new Point(cut.left, PointType.LEFT),
                        new Point(cut.right, PointType.RIGHT)
                ))
                .collect(Collectors.toList())
        );

        Collections.sort(points);
        return points;
    }
}