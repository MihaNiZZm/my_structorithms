package ru.mihanizzm.algorithms.sorts;

import ru.mihanizzm.ArrayUtils;

public class LastPivotQuickSort {
    public static <T extends Comparable<T>> void sort(T[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        if (left < right) {
            int startOfRightHalf = divideIntoHalf(arr, left, right);

            quickSort(arr, left, startOfRightHalf - 1);
            quickSort(arr, startOfRightHalf + 1, right);
        }
    }

    private static <T extends Comparable<T>> int divideIntoHalf(T[] arr, int left, int right) {
        T pivot = arr[right];
        int i = (left - 1);

        for (int j = left; j < right; ++j) {
            if (arr[j].compareTo(pivot) <= 0) {
                ++i;
                ArrayUtils.swap(arr, i, j);
            }
        }
        ArrayUtils.swap(arr, i + 1, right);

        return i + 1;
    }
}
