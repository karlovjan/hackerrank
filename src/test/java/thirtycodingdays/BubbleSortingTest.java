package thirtycodingdays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BubbleSortingTest {

    public static int bubbleSortFromTutorial(int[] x) {

        int totalSwaps = 0;
        int endPosition = x.length - 1;
        int swapPosition;

        //sortuj dokud nebude pruchod polem bez prohozeni elementu, swapPosition zustane 0
        while (endPosition > 0) {
            swapPosition = 0;

            //prochazej polem a prohod nasledujici element s aktualnim pokud je aktualni vetsi nez nasledujici, sortujeme vzestupne.
            for (int i = 0; i < endPosition; i++) {

                if (x[i] > x[i + 1]) {
                    // Swap elements 'i' and 'i + 1':
                    int tmp = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = tmp;

                    swapPosition = i;

                    totalSwaps++;
                } // end if

            } // end for

            endPosition = swapPosition;
        } // end while

        return totalSwaps;
    }

    public static int bubbleSortFromTask(int[] a) {

        int totalSwaps = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            // Track number of elements swapped during a single array traversal
            int numberOfSwaps = 0;

            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1);
                    numberOfSwaps++;
                    totalSwaps++;
                }
            }

            // If no elements were swapped during a traversal, array is sorted
            if (numberOfSwaps == 0) {
                break;
            }


        }

        return totalSwaps;
    }

    private static void swap(int[] a, int from, int to) {
        int tmp = a[from];
        a[from] = a[to];
        a[to] = tmp;
    }

    @Test
    void bubbleSortFromTutorial_notSorted_test() {
        int[] arr = new int[]{1, 2, 3};
        int totalwaps = bubbleSortFromTutorial(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1, 2, 3}, arr);

        arr = new int[]{1, 2};
        totalwaps = bubbleSortFromTutorial(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1, 2}, arr);

        arr = new int[]{1};
        totalwaps = bubbleSortFromTutorial(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1}, arr);

    }

    @Test
    void bubbleSortFromTutorialTest() {
        int[] arr = new int[]{3, 2, 1};
        int totalwaps = bubbleSortFromTutorial(arr);

        assertEquals(3, totalwaps);
        assertArrayEquals(new int[]{1, 2, 3}, arr);

        arr = new int[]{2, 1};
        totalwaps = bubbleSortFromTutorial(arr);

        assertEquals(1, totalwaps);
        assertArrayEquals(new int[]{1, 2}, arr);

    }

    @Test
    void bubbleSortFromTask_notSorted_test() {
        int[] arr = new int[]{1, 2, 3};
        int totalwaps = bubbleSortFromTask(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1, 2, 3}, arr);

        arr = new int[]{1, 2};
        totalwaps = bubbleSortFromTask(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1, 2}, arr);

        arr = new int[]{1};
        totalwaps = bubbleSortFromTask(arr);

        assertEquals(0, totalwaps);
        assertArrayEquals(new int[]{1}, arr);

    }

    @Test
    void bubbleSortFromTaskTest() {
        int[] arr = new int[]{3, 2, 1};
        int totalwaps = bubbleSortFromTask(arr);

        assertEquals(3, totalwaps);
        assertArrayEquals(new int[]{1, 2, 3}, arr);

        arr = new int[]{2, 1};
        totalwaps = bubbleSortFromTask(arr);

        assertEquals(1, totalwaps);
        assertArrayEquals(new int[]{1, 2}, arr);

    }

}
