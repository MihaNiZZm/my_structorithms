package ru.mihanizzm;

import ru.mihanizzm.algorithms.sorts.FirstPivotQuickSort;
import ru.mihanizzm.algorithms.sorts.LastPivotQuickSort;
import ru.mihanizzm.algorithms.sorts.MiddlePivotQuickSort;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        benchmark(10000, 10000, false);
    }

    public static void benchmark(int iterations, int arraySize, boolean additionalPrints) {
        long totalTime1 = 0;
        long totalTime2 = 0;
        long totalTime3 = 0;

        for (int j = 0; j < iterations; ++j) {
            Integer[] arr1 = new Integer[arraySize];
            for (int i = 0; i < arr1.length; ++i) {
                arr1[i] = i + 1;
            }
            ArrayUtils.shuffle(arr1);
            Integer[] arr2 = Arrays.copyOf(arr1, arr1.length);
            Integer[] arr3 = Arrays.copyOf(arr1, arr1.length);

            long startTime = System.currentTimeMillis();
            MiddlePivotQuickSort.sort(arr1);
            long endTime = System.currentTimeMillis();
            totalTime1 += (endTime - startTime);
            if (additionalPrints) {
                System.out.println("Time taken (middle): " + (endTime - startTime) + "ms");
            }

            startTime = System.currentTimeMillis();
            FirstPivotQuickSort.sort(arr2);
            endTime = System.currentTimeMillis();
            totalTime2 += (endTime - startTime);
            if (additionalPrints) {
                System.out.println("Time taken (first): " + (endTime - startTime) + "ms");
            }

            startTime = System.currentTimeMillis();
            LastPivotQuickSort.sort(arr3);
            endTime = System.currentTimeMillis();
            totalTime3 += (endTime - startTime);
            if (additionalPrints) {
                System.out.println("Time taken (last): " + (endTime - startTime) + "ms");
            }
        }

        double averageTime1 = (double) totalTime1 / iterations;
        double averageTime2 = (double) totalTime2 / iterations;
        double averageTime3 = (double) totalTime3 / iterations;
        System.out.println("Average time of middle: " + String.format("%.3f", averageTime1) + "ms");
        System.out.println("Average time of first: " + String.format("%.3f", averageTime2) + "ms");
        System.out.println("Average time of last: " + String.format("%.3f", averageTime3) + "ms");
    }
}