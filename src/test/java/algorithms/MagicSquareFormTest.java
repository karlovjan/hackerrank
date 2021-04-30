package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/magic-square-forming/problem
 * https://en.wikipedia.org/wiki/Magic_square
 */
public class MagicSquareFormTest {

	/**
	 * @param s s[i][j] => {1, 9}
	 * @return cost of transformation 3x3 array to the magic square, distinct values inclusive {1,9}
	 */
	private int formingMagicSquare_old(List<List<Integer>> s) {

		int cost;

		int a1 = s.get(0).get(0);
		int b1 = s.get(0).get(1);
		int c1 = s.get(0).get(2);
		int a2 = s.get(1).get(0);
		int b2 = s.get(1).get(1);
		int c2 = s.get(1).get(2);
		int a3 = s.get(2).get(0);
		int b3 = s.get(2).get(1);
		int c3 = s.get(2).get(2);

		//the magic constant 3c so long as 0 < a < b < c − a and b ≠ 2a

		int c = 5;
		int a = 0, b = 0;

		if (s.get(0).get(2) + c + s.get(2).get(0) == 3 * c) {
			a = c - c1;
		}

		if (s.get(0).get(0) + c + s.get(2).get(2) == 3 * c) {
			b = c - a1;
		}

		if (a == 0 && b != 0) {
			a = b1 - c - b;
		}

		if (a != 0 && b == 0) {
			b = b1 - c - a;
		}

		if (a == 0 && b == 0) {

			int r2 = s.get(0).get(2) + c + s.get(2).get(0) - 3 * c;
			int r1 = s.get(0).get(0) + c + s.get(2).get(2) - 3 * c;

			b = c - (a1 - r1);
			a = c - (c1 - r2);

		}

		int aa1 = (c - b) - a1;
		int bb1 = c + (a + b) - b1;
		int cc1 = c - a - c1;
		int aa2 = c - (a - b) - a2;
		int bb2 = c - b2;
		int cc2 = c + (a - b) - c2;
		int aa3 = c + a - a3;
		int bb3 = c - (a + b) - b3;
		int cc3 = c + b - c3;

		cost = Math.abs(aa1) + Math.abs(bb1) + Math.abs(cc1) + Math.abs(aa2) + Math.abs(bb2) + Math.abs(cc2) + Math
				.abs(aa3) + Math.abs(bb3) + Math.abs(cc3);

		return cost;
	}

	private int formingMagicSquare(List<List<Integer>> s) {

		Stream.Builder<List<List<Integer>>> builder = Stream.builder();

		IntStream comparedMagisSquareCost = builder.add(generateMagicSquare(1, 3, 5)).add(generateMagicSquare(-1, 3, 5))
				.add(generateMagicSquare(1, -3, 5)).add(generateMagicSquare(-1, -3, 5))
				.add(generateMagicSquare(3, 1, 5)).add(generateMagicSquare(-3, 1, 5)).add(generateMagicSquare(3, -1, 5))
				.add(generateMagicSquare(-3, -1, 5)).build().mapToInt(ms -> countCost(s, ms));

		return comparedMagisSquareCost.min().orElse(0);
	}

	private static List<List<Integer>> generateMagicSquare(int a, int b, int c) {

		List<List<Integer>> magicSquare = new ArrayList<>(3);
		magicSquare.add(List.of(c - b, c + (a + b), c - a));
		magicSquare.add(List.of(c - (a - b), c, c + (a - b)));
		magicSquare.add(List.of(c + a, c - (a + b), c + b));

		return magicSquare;
	}

	private static int countCost(List<List<Integer>> s, List<List<Integer>> magicSquare) {

		LinkedList<Integer> ms = magicSquare.stream().flatMap(m -> m.stream())
				.collect(Collectors.toCollection(LinkedList::new));
		return s.stream().flatMap(list -> list.stream()).mapToInt(si -> Math.abs(si - ms.poll())).sum();
	}

	private static int countCost(int a, int b, int c, List<List<Integer>> s) {

		int a1 = s.get(0).get(0);
		int b1 = s.get(0).get(1);
		int c1 = s.get(0).get(2);
		int a2 = s.get(1).get(0);
		int b2 = s.get(1).get(1);
		int c2 = s.get(1).get(2);
		int a3 = s.get(2).get(0);
		int b3 = s.get(2).get(1);
		int c3 = s.get(2).get(2);

		int aa1 = (c - b) - a1;
		int bb1 = c + (a + b) - b1;
		int cc1 = c - a - c1;
		int aa2 = c - (a - b) - a2;
		int bb2 = c - b2;
		int cc2 = c + (a - b) - c2;
		int aa3 = c + a - a3;
		int bb3 = c - (a + b) - b3;
		int cc3 = c + b - c3;

		int cost = Math.abs(aa1) + Math.abs(bb1) + Math.abs(cc1) + Math.abs(aa2) + Math.abs(bb2) + Math.abs(cc2) + Math
				.abs(aa3) + Math.abs(bb3) + Math.abs(cc3);

		return cost;
	}

	private static int[] getD1Array(List<List<Integer>> s) {
		return new int[] { s.get(0).get(0), s.get(1).get(1), s.get(2).get(2) };
	}

	private static int[] getD2Array(List<List<Integer>> s) {
		return new int[] { s.get(0).get(2), s.get(1).get(1), s.get(2).get(0) };
	}

	private int sumArray(int[] a) {
		return a[0] + a[1] + a[2];
	}

	private int getNonDistinctItem(int[] a) {
		return a[0] == a[1] ? a[0] : (a[1] == a[2] ? a[1] : (a[0] == a[2] ? a[0] : 0));
	}

	@Test
	void formingMagicSquare_zeroCost_Test() {

		//Mirroring
		assertEquals(0, formingMagicSquare(List.of(List.of(4, 9, 2), List.of(3, 5, 7), List.of(8, 1, 6))));
		assertEquals(0, formingMagicSquare(List.of(List.of(2, 9, 4), List.of(7, 5, 3), List.of(6, 1, 8))));
		assertEquals(0, formingMagicSquare(List.of(List.of(8, 1, 6), List.of(3, 5, 7), List.of(4, 9, 2))));
		assertEquals(0, formingMagicSquare(List.of(List.of(6, 1, 8), List.of(7, 5, 3), List.of(2, 9, 4))));

		assertEquals(0, formingMagicSquare(List.of(List.of(8, 3, 4), List.of(1, 5, 9), List.of(6, 7, 2))));
		assertEquals(0, formingMagicSquare(List.of(List.of(6, 7, 2), List.of(1, 5, 9), List.of(8, 3, 4))));
		assertEquals(0, formingMagicSquare(List.of(List.of(4, 3, 8), List.of(9, 5, 1), List.of(2, 7, 6))));
		assertEquals(0, formingMagicSquare(List.of(List.of(2, 7, 6), List.of(9, 5, 1), List.of(4, 3, 8))));

	}

	@Test
	void formingMagicSquareTest_hackerrank() {

		assertEquals(7, formingMagicSquare(List.of(List.of(5, 3, 4), List.of(1, 5, 8), List.of(6, 4, 2))));
		assertEquals(1, formingMagicSquare(List.of(List.of(4, 9, 2), List.of(3, 5, 7), List.of(8, 1, 5))));
		assertEquals(4, formingMagicSquare(List.of(List.of(4, 8, 2), List.of(4, 5, 7), List.of(6, 1, 6))));

	}

	@Test
	void squereTest() {
		//		assertEquals(0, formingMagicSquare(List.of(List.of(4,9,2), List.of(3,5,7), List.of(8,1,6))));

		assertEquals(2, formingMagicSquare(List.of(List.of(5, 9, 2), List.of(3, 5, 7), List.of(8, 1, 5))));

		assertEquals(2, formingMagicSquare(List.of(List.of(3, 9, 3), List.of(3, 5, 7), List.of(8, 1, 6))));
		assertEquals(10, formingMagicSquare(List.of(List.of(1, 9, 9), List.of(3, 5, 7), List.of(8, 1, 6))));
		assertEquals(6, formingMagicSquare(List.of(List.of(6, 9, 6), List.of(3, 5, 7), List.of(8, 1, 6))));
		assertEquals(12, formingMagicSquare(List.of(List.of(9, 9, 9), List.of(3, 5, 7), List.of(8, 1, 6))));

		assertEquals(1, formingMagicSquare(List.of(List.of(4, 9, 2), List.of(3, 4, 7), List.of(8, 1, 6))));
		assertEquals(2, formingMagicSquare(List.of(List.of(5, 9, 2), List.of(3, 5, 7), List.of(8, 1, 5))));

		assertEquals(5, formingMagicSquare(List.of(List.of(4, 7, 2), List.of(4, 5, 8), List.of(8, 2, 6))));
		assertEquals(6, formingMagicSquare(List.of(List.of(4, 7, 2), List.of(4, 6, 8), List.of(8, 2, 6))));
		assertEquals(4, formingMagicSquare(List.of(List.of(4, 8, 2), List.of(2, 5, 6), List.of(8, 2, 6))));
		assertEquals(2, formingMagicSquare(List.of(List.of(3, 9, 2), List.of(3, 5, 7), List.of(8, 1, 5))));
	}

	@Test
	void formingMagicSquareTest2() {

		assertEquals(36, formingMagicSquare(List.of(List.of(1, 1, 1), List.of(1, 1, 1), List.of(1, 1, 1))));
		assertEquals(36, formingMagicSquare(List.of(List.of(9, 9, 9), List.of(9, 9, 9), List.of(9, 9, 9))));
		assertEquals(32, formingMagicSquare(List.of(List.of(1, 2, 2), List.of(2, 1, 2), List.of(2, 2, 1))));

		assertEquals(29, formingMagicSquare(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3))));
		assertEquals(29, formingMagicSquare(List.of(List.of(1, 2, 3), List.of(2, 2, 2), List.of(1, 2, 3))));

		assertEquals(29, formingMagicSquare(List.of(List.of(7, 8, 9), List.of(7, 8, 9), List.of(7, 8, 9))));

		assertEquals(24, formingMagicSquare(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9))));

		assertEquals(12, formingMagicSquare(List.of(List.of(5, 8, 5), List.of(6, 5, 4), List.of(5, 2, 5))));

	}

/*
	void formingMagicSquareTest_old() {


		assertEquals(0, formingMagicSquare(new int[][] { { 8, 3, 4 }, { 1, 5, 9 }, { 6, 7, 2 } }));

		assertEquals(7, formingMagicSquare(new int[][] { { 5, 3, 4 }, { 1, 5, 8 }, { 6, 4, 2 } }));
		assertEquals(1, formingMagicSquare(new int[][] { { 4, 9, 2 }, { 3, 5, 7 }, { 8, 1, 5 } }));
		assertEquals(4, formingMagicSquare(new int[][] { { 4, 8, 2 }, { 4, 5, 7 }, { 6, 1, 6 } }));

		assertEquals(36, formingMagicSquare(new int[][] { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }));
		assertEquals(36, formingMagicSquare(new int[][] { { 9, 9, 9 }, { 9, 9, 9 }, { 9, 9, 9 } }));
		assertEquals(32, formingMagicSquare(new int[][] { { 1, 2, 2 }, { 2, 1, 2 }, { 2, 2, 1 } }));

		assertEquals(29, formingMagicSquare(new int[][] { { 1, 2, 3 }, { 1, 2, 3 }, { 1, 2, 3 } }));
		assertEquals(29, formingMagicSquare(new int[][] { { 1, 2, 3 }, { 2, 2, 2 }, { 1, 2, 3 } }));

		assertEquals(29, formingMagicSquare(new int[][] { { 7, 8, 9  }, { 7, 8, 9 }, { 7, 8, 9 } }));

		assertEquals(30, formingMagicSquare(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }));

		assertEquals(10, formingMagicSquare(new int[][] { { 5, 8, 5 }, { 6, 5, 4 }, { 5, 2, 5 } }));

	}

 */
}
