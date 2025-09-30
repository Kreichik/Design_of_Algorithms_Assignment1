package org.example.metrics;

public class Metrics {
    private long comparisons;
    private long swaps;
    private int maxDepth;
    private long executionTimeNanos;

    public Metrics() {
        reset();
    }

    public void reset() {
        this.comparisons = 0;
        this.swaps = 0;
        this.maxDepth = 0;
        this.executionTimeNanos = 0;
    }

    public void incrementComparisons() {
        this.comparisons++;
    }

    public void incrementSwaps() {
        this.swaps++;
    }


    public void addSwaps(int count) {
        this.swaps += count;
    }

    public void recordDepth(int depth) {
        if (depth > this.maxDepth) {
            this.maxDepth = depth;
        }
    }

    public void setExecutionTime(long nanos) {
        this.executionTimeNanos = nanos;
    }

    public long getComparisons() { return comparisons; }
    public long getSwaps() { return swaps; }
    public int getMaxDepth() { return maxDepth; }
    public long getExecutionTimeNanos() { return executionTimeNanos; }

    @Override
    public String toString() {
        return "Metrics{" +
                "comparisons=" + comparisons +
                ", swaps=" + swaps +
                ", maxDepth=" + maxDepth +
                ", executionTimeNanos=" + executionTimeNanos +
                '}';
    }
}