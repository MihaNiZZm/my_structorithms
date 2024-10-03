package ru.mihanizzm;

import java.util.Arrays;
import java.util.Collections;

public class ArrayUtils {
    public static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static <T> void shuffle(T[] arr) {
        Collections.shuffle(Arrays.asList(arr));
    }
}
