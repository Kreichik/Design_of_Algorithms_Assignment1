package org.example.benchmarks;

import org.example.algorithms.selection.DeterministicSelect;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(value = 1, jvmArgs = {"-Xms2G", "-Xmx2G"})
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
public class SelectVsSortBenchmark {

    @Param({"1000", "10000", "100000"})
    private int n;

    private int[] sourceArray;
    private int k;
    private DeterministicSelect deterministicSelect;

    @Setup
    public void setup() {
        sourceArray = new Random().ints(n, -n, n).toArray();
        k = n / 2;
        deterministicSelect = new DeterministicSelect();
    }

    @Benchmark
    public int benchmarkSelect() {
        return deterministicSelect.select(sourceArray.clone(), k);
    }

    @Benchmark
    public int benchmarkSortAndGet() {
        int[] cloned = sourceArray.clone();
        Arrays.sort(cloned);
        return cloned[k - 1];
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(SelectVsSortBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}