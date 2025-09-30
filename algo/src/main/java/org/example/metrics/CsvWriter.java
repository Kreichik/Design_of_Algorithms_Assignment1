package org.example.metrics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CsvWriter {
    private static final String HEADER = "Algorithm,N,Time(ns),Comparisons,Swaps,MaxDepth\n";
    private final String filePath;

    public CsvWriter(String filePath) {
        this.filePath = filePath;
        initFile();
    }

    private void initFile() {
        File file = new File(filePath);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        if (!file.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
                writer.write(HEADER);
            } catch (IOException e) {
                System.err.println("Error initializing CSV file: " + e.getMessage());
            }
        }
    }

    public synchronized void writeMetrics(String algorithmName, int n, Metrics metrics) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(algorithmName).append(',');
            sb.append(n).append(',');
            sb.append(metrics.getExecutionTimeNanos()).append(',');
            sb.append(metrics.getComparisons()).append(',');
            sb.append(metrics.getSwaps()).append(',');
            sb.append(metrics.getMaxDepth()).append('\n');
            writer.write(sb.toString());
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}