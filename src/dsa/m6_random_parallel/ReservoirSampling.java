package dsa.m6_random_parallel;

import java.util.Random;

public class ReservoirSampling {

    private static Random random =
            new Random();

    // Select k random elements
    // from an integer array
    public static int[] sample(
            int[] data,
            int k) {

        if (data == null ||
                k <= 0 ||
                k > data.length) {

            return new int[0];
        }

        int[] reservoir =
                new int[k];

        // First k elements
        // are placed into reservoir
        for (int i = 0;
                i < k;
                i++) {

            reservoir[i] =
                    data[i];
        }

        // Process remaining elements
        for (int i = k;
                i < data.length;
                i++) {

            int randomIndex =
                    random.nextInt(i + 1);

            // Replace an existing
            // element with probability k/i
            if (randomIndex < k) {

                reservoir[randomIndex] =
                        data[i];
            }
        }

        return reservoir;
    }

    // Display sample
    public static void displaySample(
            int[] sample) {

        System.out.println(
                "Random Sample:"
        );

        for (int value : sample) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();
    }
}