package com.example.roughwork.exams.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MovieTime {

    public static int findMinimumTime(List<Integer> a, List<Integer> timeForEachA,
                                      List<Integer> b, List<Integer> timeForEachB) {
        int totalTime = Integer.MAX_VALUE;
        int indexA = 0, indexB = 0;

        while (indexA < a.size() || indexB < b.size()) {
            int releaseTimeA = indexA < a.size() ? a.get(indexA) : Integer.MAX_VALUE;
            int lengthA = indexA < a.size() ? timeForEachA.get(indexA) : 0;

            int releaseTimeB = indexB < b.size() ? b.get(indexB) : Integer.MAX_VALUE;
            int lengthB = indexB < b.size() ? timeForEachB.get(indexB) : 0;

            if (releaseTimeA <= releaseTimeB) {
                // Watch movie from list A
                totalTime = Math.min(totalTime, releaseTimeA + lengthA);
                indexA++;
            } else {
                // Watch movie from list B
                totalTime = Math.min(totalTime, releaseTimeB + lengthB);
                indexB++;
            }
        }

        return totalTime;
    }

    public static void main(String[] args) {
        List<Integer> a = List.of(1, 4);
        List<Integer> timeForEachA = List.of(3, 2);
        List<Integer> b = List.of(5, 2);
        List<Integer> timeForEachB = List.of(2, 2);

        int minimumTime = findMinimumTime(a, timeForEachA, b, timeForEachB);
        System.out.println("Minimum time to watch movies: " + minimumTime);
    }
}

