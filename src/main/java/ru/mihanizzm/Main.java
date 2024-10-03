package ru.mihanizzm;

import ru.mihanizzm.benchmarks.QuickSortBenchmark;

public class Main {
    public static void main(String[] args) {
        QuickSortBenchmark.run(10000, 10000, false);
    }
}