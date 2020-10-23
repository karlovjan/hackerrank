package datastructures;

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

}
