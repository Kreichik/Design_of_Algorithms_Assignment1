// File: algo/src/main/java/org/example/App.java
package org.example;

import org.example.algorithms.sorting.MergeSort;
import org.example.algorithms.sorting.QuickSort;
import org.example.metrics.CsvWriter;
import org.example.metrics.Metrics;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Callable;

@Command(name = "algo-runner", mixinStandardHelpOptions = true,
        version = "algo-runner 1.0",
        description = "Runs specified algorithms for given input sizes and records metrics.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "The algorithm(s) to run. Can be 'mergesort', 'quicksort'.")
    private String[] algorithms;

    @Option(names = {"-n", "--sizes"}, required = true, split = ",", description = "Comma-separated list of input sizes (e.g., 100,1000,10000).")
    private int[] sizes;

    @Option(names = {"-o", "--output"}, defaultValue = "results/metrics.csv", description = "Output CSV file path.")
    private String outputPath;

    private final Random random = new Random();

    @Override
    public Integer call() throws Exception {
        CsvWriter csvWriter = new CsvWriter(outputPath);
        System.out.println("Starting experiments. Results will be saved to " + outputPath);

        for (String algoName : algorithms) {
            for (int n : sizes) {
                System.out.printf("Running %s for n = %d...%n", algoName, n);

                int[] array = random.ints(n, -n, n).toArray();
                Metrics metrics = null;

                switch (algoName.toLowerCase()) {
                    case "mergesort":
                        MergeSort mergeSort = new MergeSort();
                        metrics = mergeSort.sort(array.clone());
                        break;
                    case "quicksort":
                        QuickSort quickSort = new QuickSort();
                        metrics = quickSort.sort(array.clone());
                        break;
                    default:
                        System.err.println("Unknown algorithm: " + algoName);
                        continue;
                }

                if (metrics != null) {
                    csvWriter.writeMetrics(algoName, n, metrics);
                }
            }
        }

        System.out.println("All experiments finished.");
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}