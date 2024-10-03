package ru.mihanizzm;

import java.util.Arrays;
import java.util.Collections;

public class ArrayUtils {
    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T> void reverse(T[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            swap(arr, i, arr.length - i - 1);
        }
    }

    public static <T> void shuffle(T[] arr) {
        Collections.shuffle(Arrays.asList(arr));
    }

    public static <T> T[] copy(T[] arr) {
        T[] copy = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        return copy;
    }
}
