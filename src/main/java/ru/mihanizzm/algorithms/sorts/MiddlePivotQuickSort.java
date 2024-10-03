package ru.mihanizzm.algorithms.sorts;

import ru.mihanizzm.ArrayUtils;

public class MiddlePivotQuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            int startOfRightHalf = divideIntoHalf(arr, left, right);

            quickSort(arr, left, startOfRightHalf - 1);
            quickSort(arr, startOfRightHalf, right);
        }
    }
    private static <T extends Comparable<T>> int divideIntoHalf(T[] arr, int left, int right) {
        T pivot = arr[(left + right) / 2];
        while (left <= right) {
            while (arr[left].compareTo(pivot) < 0) {
                ++left;
            }
            while (arr[right].compareTo(pivot) > 0) {
                --right;
            }
            if (left <= right) {
                if (arr[left].compareTo(arr[right]) > 0) {
                    ArrayUtils.swap(arr, left, right);
                }
                ++left;
                --right;
            }
        }

        return left;
    }
}
