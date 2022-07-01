package de.telran;

import java.util.ArrayList;
import java.util.Arrays;

public class MinAmountPointInCuts {
    // Function to compute minimum number
// of points which cover all segments
    static void minPoints(int[][] points, int n)
    {

        // Sort the list of tuples by
        // their second element.
        Arrays.sort(points, (a, b) -> a[1] - b[1]);

        // To store the solution
        ArrayList<Integer> coordinates = new ArrayList<>();
        int i = 0;

        // Iterate over all the segments
        while (i < n)
        {
            int seg = points[i][1];
            coordinates.add(seg);
            int p = i + 1;

            if (p >= n)
                break;

            // Get the start point of next segment
            int arrived = points[p][0];

            // Loop over all those segments whose
            // start point is less than the end
            // point of current segment
            while (seg >= arrived)
            {
                p += 1;

                if (p >= n)
                    break;

                arrived = points[p][0];
            }
            i = p;
        }

        // Print the possibles values of M
        for(Integer point : coordinates)
            System.out.print(point + " ");
    }

    // Driver code
    public static void main(String[] args)
    {

        int n = 4;

        // Starting points of segments
        int[] start = { 4, 1, 2, 5 };

        // Ending points of segments
        int[] end = { 7, 3, 5, 6 };

        int[][] points = new int[n][2];

        // Insert ranges in points[]
        for(int i = 0; i < n; i++)
        {
            points[i][0] = start[i];
            points[i][1] = end[i];
        }

        // Function call
        minPoints(points, n);
    }
}
