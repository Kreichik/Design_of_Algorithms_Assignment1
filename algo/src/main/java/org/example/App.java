package org.example;

import org.example.metrics.CsvWriter;
import org.example.metrics.Metrics;

public class App {
    public static void main(String[] args) {
        System.out.println("Running metrics collection demo...");

        String outputPath = "results/metrics.csv";
        CsvWriter csvWriter = new CsvWriter(outputPath);
        Metrics metrics = new Metrics();

        int n = 100;

        long startTime = System.nanoTime();
        dummyRecursiveFunction(0, n, 1, metrics); // Начальная глубина 1
        long endTime = System.nanoTime();

        metrics.setExecutionTime(endTime - startTime);
        csvWriter.writeMetrics("DummyAlgorithm", n, metrics);

        System.out.println("Metrics collected: " + metrics);
        System.out.println("Results saved to " + outputPath);
    }

    private static void dummyRecursiveFunction(int start, int end, int depth, Metrics metrics) {
        metrics.recordDepth(depth);

        if (end - start <= 1) {
            return;
        }

        for(int i = 0; i < 5; i++) {
            metrics.incrementComparisons();
        }
        metrics.incrementSwaps();

        int mid = start + (end - start) / 2;
        dummyRecursiveFunction(start, mid, depth + 1, metrics);
        dummyRecursiveFunction(mid, end, depth + 1, metrics);
    }
}