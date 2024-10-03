package ru.mihanizzm.benchmarks;

import ru.mihanizzm.ArrayUtils;
import ru.mihanizzm.algorithms.sorts.FirstPivotQuickSort;
import ru.mihanizzm.algorithms.sorts.LastPivotQuickSort;
import ru.mihanizzm.algorithms.sorts.MiddlePivotQuickSort;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class QuickSortBenchmark {
    private static volatile AtomicInteger completedIterations = new AtomicInteger(0);

    private static double averageTime1;
    private static double averageTime2;
    private static double averageTime3;

    public static void run(int iterations, int arraySize, boolean additionalPrints) {
        completedIterations = new AtomicInteger(0);

        Thread progressThread = new Thread(() -> {
           while (completedIterations.get() < iterations) {
               try {
                   Thread.sleep(3000);
               }
               catch (InterruptedException e) {
                   Thread.currentThread().interrupt();
               }
               printProgress(iterations);
           }
        });

        System.out.println("Benchmark started!");
        progressThread.start();
        processBenchmark(iterations, arraySize, additionalPrints);
        try {
            progressThread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Average time of middle: " + String.format("%.3f", averageTime1) + "ms");
        System.out.println("Average time of first: " + String.format("%.3f", averageTime2) + "ms");
        System.out.println("Average time of last: " + String.format("%.3f", averageTime3) + "ms");
        System.out.println("Benchmark completed: " + completedIterations + " iterations.");
    }

    private static void printProgress(int iterations) {
        double progress = ((double) completedIterations.get() / iterations) * 100;
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < 100; ++i) {
            if ((double) i >= progress) {
                sb.append('.');
            }
            else {
                sb.append('#');
            }
        }
        sb.append(']');
        System.out.printf("Progress: %s %.2f%%\n", sb, progress);
    }

    private static void processBenchmark(int iterations, int arraySize, boolean additionalPrints) {
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
            completedIterations.incrementAndGet();
        }

        averageTime1 = (double) totalTime1 / iterations;
        averageTime2 = (double) totalTime2 / iterations;
        averageTime3 = (double) totalTime3 / iterations;
    }
}
