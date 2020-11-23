package datastructures.arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class TwoDimensionalArrayTest {

	/*
	Random numbers
	max range included
	https://dzone.com/articles/random-number-generation-in-java
	https://mkyong.com/java/java-generate-random-integers-in-a-range/

	max random range excluded
	https://www.baeldung.com/java-generating-random-numbers-in-range

	Sum
	https://mkyong.com/java8/java-how-to-sum-all-the-stream-integers/

	 */
	private static final int HOUR_GLASS_RESOLUTION = 3;
	private int[][] arr;

	//    @BeforeEach
	private void generateRandomArr(int arrDimension, int minArrValue, int maxArrValue) {

		arr = new int[arrDimension][arrDimension];

		Random random = new Random();

		for (int i = 0; i < arrDimension; i++) {
			for (int j = 0; j < arrDimension; j++) {
				arr[i][j] = random.ints(minArrValue, (maxArrValue + 1)).limit(1).findFirst().orElse(minArrValue);
			}
		}

	}

	@Test
	void symmetricArrayLengthTest() {

		int arrDimension = 6;
		generateRandomArr(arrDimension, -9, 9);

		assertEquals(arrDimension, arr.length, "rows");
		assertEquals(arrDimension, arr[0].length, "columns");
	}

	@Test
	void deleni() {
		assertEquals(0 / 4, 0);
		assertEquals(1 / 4, 0);
		assertEquals(2 / 4, 0);
		assertEquals(3 / 4, 0);
		assertEquals(4 / 4, 1);
		assertEquals(5 / 4, 1);
		assertEquals(6 / 4, 1);
		assertEquals(7 / 4, 1);
		assertEquals(8 / 4, 2);
		assertEquals(9 / 4, 2);
		assertEquals(12 / 4, 3);
	}

	@Test
	void modulo() {
		assertEquals(0 % 4, 0);
		assertEquals(1 % 4, 1);
		assertEquals(2 % 4, 2);
		assertEquals(3 % 4, 3);
		assertEquals(4 % 4, 0);
		assertEquals(5 % 4, 1);
		assertEquals(6 % 4, 2);
		assertEquals(7 % 4, 3);
		assertEquals(8 % 4, 0);
		assertEquals(9 % 4, 1);
		assertEquals(12 % 4, 0);
	}

	@Test
	void sumHourglassesTest() throws Exception {

		arr = new int[][] { { 1, 1, 1, 0, 0, 0 }, { 0, 1, 0, 0, 0, 0 }, { 1, 1, 1, 0, 0, 0 }, { 0, 0, 2, 4, 4, 0 },
				{ 0, 0, 0, 2, 0, 0 }, { 0, 0, 1, 2, 4, 0 } };

		int result = maxHourglassSum();

		assertEquals(19, result, "max sum");
	}

	private int maxHourglassSum() throws Exception {
		//kolik bude presypacich hodin v arr?
		//arr.length=3->1,arr.length=4->2,arr.length=5->3,arr.length=6->4,arr.length=7->5
		int hourglassSumArrayRowsCount = arr.length >= HOUR_GLASS_RESOLUTION ? arr.length - 2 : 0;

		if (hourglassSumArrayRowsCount == 0) {
			throw new Exception("Input Array is smaller than 3");
		}
		//kolik bude mit prvku pole ktere vznikne danim vsech presypacich hodin vedle sebe a pod sebe
		// do symetricke matice s velikosti hourglassSumArrayRowsCount
		//arr.length=3->1->3,arr.length=4->2->6,arr.length=5->3->9,arr.length=6->4->12,arr.length=7->5->15
		//hourglassSumArrayRowsCount * HOUR_GLASS_RESOLUTION;
		int hourglassListListSize = (int) Math.pow(hourglassSumArrayRowsCount, 2);
		int hourglassListSize = (int) Math.pow(HOUR_GLASS_RESOLUTION, 2);

		//list se vsemi prvky hourglass
		List<List<Integer>> hourglassListList = new ArrayList<>(); //size hourglassSumArrayRowsCount * hourglassSumArrayRowsCount

		//list se vsemi prvky hourglass
		List<Integer> hourglassList; //size 3*3=9

		int startRow;
		int startCol;

		for (int c = 0; c < hourglassListListSize; c++) {

			hourglassList = new ArrayList<>();

			startRow = hourglassListList.size() / hourglassSumArrayRowsCount;
			startCol = hourglassListList.size() % hourglassSumArrayRowsCount;

			for (int i = 0, m = 0, n = 0; i < hourglassListSize; i++) {

				if (i == HOUR_GLASS_RESOLUTION || i == HOUR_GLASS_RESOLUTION + 2) {
					//nastav 0  u dvou krajnich hodnot v matici prostresypacich hodin,
					// tyhle hodnoty se nezapocitavaji do sumy prvku presypacich hodin
					hourglassList.add(0);
				} else {
					hourglassList.add(arr[startRow + m][startCol + n]);
				}

				if (n == HOUR_GLASS_RESOLUTION - 1) {
					n = 0;
					++m;
				} else {
					++n;
				}
			}

			hourglassListList.add(hourglassList);
		}

		return hourglassListList.stream().mapToInt(hourglass -> hourglass.stream().mapToInt(Integer::intValue).sum())
				.max().orElse(-1);
	}


	/*
		Two dimensional Array Manipulation

		https://www.hackerrank.com/challenges/crush/problem

		Starting with a 1-indexed array of zeros and a list of operations,
		for each operation add a value to each of the array element between two given indices, inclusive.
		Once all operations have been performed, return the maximum value in the array.
	 */

	/**
	 * The first line contains two space-separated integers n and m, the size of the array and the number of operations.
	 * 2 <= m <= 2 x 10^5
	 * Each of the next m lines contains three space-separated integers a,b  and k, the left index, right index and summand.
	 * 1 <= a <= b <= n
	 * 0 <= k <= 10^9
	 *
	 * @param n       3 <= n <= 10000000 (10^7)
	 * @param queries queries[m][3]
	 * @return the max item after all operations
	 */
	private long arrayManipulation(int n, int[][] queries) {

		long max = 0;

		//		int rows = queries.length;
		//		int columns = n;
		//		int[][] resultArray = new int[rows][columns];
		//one array is enough because I use the operation 'add' on each item in the array
		long[] resultArray = new long[n];

		int a, b; //indexes
		long add; //operation on item

		//max operations mxn = 2*10^5 * 10^7 = 2*10^12  O(n^2)
		for (int[] query : queries) {

			a = query[0];
			b = query[1];
			add = query[2];

			for (int j = 0; j < n; j++) {
				if (a - 1 <= j && j <= b - 1) {
					resultArray[j] = resultArray[j] + add;
					//whatch the maximum item value
					max = Math.max(max, resultArray[j]);
				}
			}
		}

		return max;

	}

	@Test
	void arrayManipulation1Test() {
		assertEquals(200L, arrayManipulation(5,
				new int[][] { new int[] { 1, 2, 100 }, new int[] { 2, 5, 100 }, new int[] { 3, 4, 100 } }));
	}

	@Test
	void arrayManipulation2Test() {
		assertEquals(10L, arrayManipulation(10,
				new int[][] { new int[] { 1, 5, 3 }, new int[] { 4, 8, 7 }, new int[] { 6, 9, 1 } }));
	}

	private long arrayManipulationVer2(int n, int[][] queries) {

		long max = 0;

		//		int rows = queries.length;
		//		int columns = n;
		//		int[][] resultArray = new int[rows][columns];
		//one array is enough because I use the operation 'add' on each item in the array
		long[] resultArray = new long[n];

		int a, b; //indexes
		long add; //operation on item

		//max operations mxn = 2*10^5 * 10^7 = 2*10^12  O(n^2)
		//the worst scenario is the same as the variant 1
		//the most optimistic scenario is better - 2*10^5 * 2*10^0 (2 items for a and b index) = 4*10^5 , take linear time
		for (int[] query : queries) {

			a = query[0];
			b = query[1];
			add = query[2];

			for (int i = a; i <= b; i++) {
				resultArray[i - 1] = resultArray[i - 1] + add;
				//whatch the maximum item value
				max = Math.max(max, resultArray[i - 1]);

			}
		}

		return max;

	}

	@Test
	void arrayManipulationVer21Test() {
		assertEquals(200L, arrayManipulationVer2(5,
				new int[][] { new int[] { 1, 2, 100 }, new int[] { 2, 5, 100 }, new int[] { 3, 4, 100 } }));
	}

	@Test
	void arrayManipulationVer22Test() {
		assertEquals(10L, arrayManipulationVer2(10,
				new int[][] { new int[] { 1, 5, 3 }, new int[] { 4, 8, 7 }, new int[] { 6, 9, 1 } }));
	}

	@Test
	void arrayManipulationVer23Test() {
		//test minimal
		assertEquals(0L, arrayManipulationVer2(3,
				new int[][] { new int[] { 1, 1, 0 } }));

		assertEquals(0L, arrayManipulationVer2(10,
				new int[][] { new int[] { 1, 1, 0 }, new int[] { 2, 2, 0 }, new int[] { 3, 3, 0 } }));

		assertEquals(30L, arrayManipulationVer2(3,
				new int[][] { new int[] { 1, 1, 10 }, new int[] { 2, 2, 20 }, new int[] { 3, 3, 30 } }));
	}

	@Test
	void arrayManipulationVer24Test() {
//test big numbers
		//Integer.MAX_VALUE = 2147483647
		assertEquals(12000000000L, arrayManipulationVer2(10000000,
				new int[][] { new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 },
						new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 },
						new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 },
						new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 }, new int[] { 1, 1, 1000000000 }}));

	}

	@Test
	void arrayManipulationVer25Test() {
		//test time consuming
		assertEquals(0L, arrayManipulationVer2(10000000,
				new int[][] { new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 },
						new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 },
						new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 },
						new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 }, new int[] { 1, 10000000, 0 }}));

	}

	private long arrayManipulationVer3(int n, int[][] queries) {

//TODO vylepsit rychlost
		//max operations mxn = 2*10^5 * 10^7 = 2*10^12  O(n^2)
		//the worst scenario is the same as the variant 1
		//the most optimistic scenario is better - 2*10^5 * 2*10^0 (2 items for a and b index) = 4*10^5 , take linear time

		int a = 1;
		int b = n;
		long max = 0;

		for (int[] query : queries) {

//			a = query[0];
//			b = query[1];
//			add = query[2];

		}

		return max;

	}

}
