package datastructures.arrays;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * https://www.hackerrank.com/challenges/one-week-preparation-kit-diagonal-difference/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=one-week-preparation-kit&playlist_slugs%5B%5D=one-week-day-two
 * <p>
 * Given a square matrix, calculate the absolute difference between the sums of its diagonals.
 */
public class DiagonalDifferenceTest {


    public static int diagonalDifference(List<List<Integer>> arr) {

        int sumDiagonalFromLeftToRight = 0;
        int sumDiagonalFromRightToLeft = 0;
        List<Integer> row;

        for (int i = 0, l = 0, p = arr.size() - 1; i < arr.size(); i++, l++, p--) {
            row = arr.get(i);
            sumDiagonalFromLeftToRight += row.get(l);
            sumDiagonalFromRightToLeft += row.get(p);
        }

        return Math.abs(sumDiagonalFromLeftToRight - sumDiagonalFromRightToLeft);
    }


    @Test
    void diagonalDifferenceHRTest() {
        assertEquals(2, diagonalDifference(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(9, 8, 9))));
        assertEquals(15, diagonalDifference(List.of(List.of(11, 2, 4), List.of(4, 5, 6), List.of(10, 8, -12))));
    }
}
