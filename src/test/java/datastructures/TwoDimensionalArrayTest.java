package datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TwoDimensionalArrayTest {

    /*
    Random numbers
    max range included
    https://dzone.com/articles/random-number-generation-in-java
    https://mkyong.com/java/java-generate-random-integers-in-a-range/

    max random range excluded
    https://www.baeldung.com/java-generating-random-numbers-in-range


     */
    private final int[][] arr = new int[6][6];

    @BeforeEach
    void setUp() {

        int min = -9;
        int max = 9;
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = random.ints(min,(max+1)).limit(1).findFirst().orElse(min);
            }
        }

    }

    @Test
    void symmetricArrayLengthTest() {

        assertEquals(6, arr.length, "rows");
        assertEquals(6, arr[0].length, "columns");
    }

    @Test
    void sumHourglassesTest() {

        assertEquals(6, arr.length, "rows");
    }
}
