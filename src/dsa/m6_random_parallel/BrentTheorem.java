package dsa.m6_random_parallel;

public class BrentTheorem {

    // Calculate the upper bound
    // using Brent's theorem:
    //
    // T(P) <= W / P + D
    //
    // W = total work
    // D = parallel depth
    // P = number of processors

    public static double calculateTime(
            int work,
            int depth,
            int processors) {

        if (processors <= 0) {

            return -1;
        }

        return (double) work
                / processors
                + depth;
    }

    // Calculate sequential time
    public static int sequentialTime(
            int work) {

        return work;
    }

    // Calculate ideal parallel time
    public static double idealParallelTime(
            int work,
            int processors) {

        if (processors <= 0) {

            return -1;
        }

        return (double) work
                / processors;
    }

    // Calculate theoretical speedup
    public static double speedup(
            int work,
            int depth,
            int processors) {

        double parallelTime =
                calculateTime(
                        work,
                        depth,
                        processors
                );

        if (parallelTime <= 0) {
            return 0;
        }

        return work / parallelTime;
    }

    // Display Brent theorem calculation
    public static void displayResult(
            int work,
            int depth,
            int processors) {

        double time =
                calculateTime(
                        work,
                        depth,
                        processors
                );

        double speedup =
                speedup(
                        work,
                        depth,
                        processors
                );

        System.out.println(
                "Brent's Theorem Analysis:"
        );

        System.out.println(
                "Total Work (W): "
                + work
        );

        System.out.println(
                "Parallel Depth (D): "
                + depth
        );

        System.out.println(
                "Processors (P): "
                + processors
        );

        System.out.println(
                "Estimated Parallel Time: "
                + time
        );

        System.out.println(
                "Theoretical Speedup: "
                + speedup
        );
    }
}