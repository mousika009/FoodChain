package dsa.m6_random_parallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelReduce {

    // Threshold decides when
    // the calculation becomes sequential
    private static final int THRESHOLD = 2;

    // Main parallel sum method
    public static int sum(
            int[] array) {

        if (array == null ||
                array.length == 0) {

            return 0;
        }

        ForkJoinPool pool =
                ForkJoinPool.commonPool();

        SumTask task =
                new SumTask(
                        array,
                        0,
                        array.length
                );

        return pool.invoke(task);
    }

    // Recursive task
    private static class SumTask
            extends RecursiveTask<Integer> {

        private int[] array;
        private int start;
        private int end;

        public SumTask(
                int[] array,
                int start,
                int end) {

            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {

            int length =
                    end - start;

            // Small section:
            // calculate directly
            if (length <= THRESHOLD) {

                int total = 0;

                for (int i = start;
                        i < end;
                        i++) {

                    total += array[i];
                }

                return total;
            }

            // Divide the array
            int middle =
                    (start + end) / 2;

            SumTask leftTask =
                    new SumTask(
                            array,
                            start,
                            middle
                    );

            SumTask rightTask =
                    new SumTask(
                            array,
                            middle,
                            end
                    );

            // Run left side in parallel
            leftTask.fork();

            // Calculate right side
            int rightResult =
                    rightTask.compute();

            // Get left result
            int leftResult =
                    leftTask.join();

            return leftResult
                    + rightResult;
        }
    }

    // Sequential sum for comparison
    public static int sequentialSum(
            int[] array) {

        if (array == null) {
            return 0;
        }

        int total = 0;

        for (int value : array) {

            total += value;
        }

        return total;
    }

    // Display result
    public static void displayResult(
            int result) {

        System.out.println(
                "Reduced Sum: "
                + result
        );
    }
}