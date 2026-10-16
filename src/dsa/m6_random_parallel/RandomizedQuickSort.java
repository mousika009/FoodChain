package dsa.m6_random_parallel;

import java.util.Random;

public class RandomizedQuickSort {

    private static Random random =
            new Random();

    // Main sorting method
    public static void sort(int[] array) {

        if (array == null ||
                array.length <= 1) {

            return;
        }

        quickSort(
                array,
                0,
                array.length - 1
        );
    }

    // Recursive QuickSort
    private static void quickSort(
            int[] array,
            int low,
            int high) {

        if (low >= high) {
            return;
        }

        int pivotIndex =
                chooseRandomPivot(
                        low,
                        high
                );

        swap(
                array,
                pivotIndex,
                high
        );

        int partitionIndex =
                partition(
                        array,
                        low,
                        high
                );

        quickSort(
                array,
                low,
                partitionIndex - 1
        );

        quickSort(
                array,
                partitionIndex + 1,
                high
        );
    }

    // Choose a random pivot
    private static int chooseRandomPivot(
            int low,
            int high) {

        return low +
                random.nextInt(
                        high - low + 1
                );
    }

    // Partition the array
    private static int partition(
            int[] array,
            int low,
            int high) {

        int pivot =
                array[high];

        int smallerIndex =
                low - 1;

        for (int j = low;
                j < high;
                j++) {

            if (array[j] <= pivot) {

                smallerIndex++;

                swap(
                        array,
                        smallerIndex,
                        j
                );
            }
        }

        swap(
                array,
                smallerIndex + 1,
                high
        );

        return smallerIndex + 1;
    }

    // Swap two elements
    private static void swap(
            int[] array,
            int first,
            int second) {

        int temp =
                array[first];

        array[first] =
                array[second];

        array[second] =
                temp;
    }

    // Display array
    public static void display(
            int[] array) {

        for (int value : array) {

            System.out.print(
                    value + " "
            );
        }

        System.out.println();
    }
}