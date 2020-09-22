package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import org.junit.jupiter.api.Test;

public class PlusMinusTest {

	private static DecimalFormat df6 = new DecimalFormat("#.######");

	public static void main(String[] args) {
		plusMinus(new int[] {-4, 3, -9, 0, 4, 1});
	}

	static void plusMinus(int[] arr) {

		int zerouCounter = 0;
		int positiveCounter = 0;
		int negativeCounter = 0;

		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == 0){
				++zerouCounter;
			} else if (arr[i] > 0){
				++positiveCounter;
			} else {
				++negativeCounter;
			}
		}

		double zeroRation = ((double) zerouCounter / arr.length);
		double positiveRation = ((double) positiveCounter / arr.length);
		double negativeRation = ((double) negativeCounter / arr.length);

		System.out.println(String.format("%.6f", positiveRation));
		System.out.println(String.format("%.6f", negativeRation));
		System.out.println(String.format("%.6f", zeroRation));

		df6.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(df6.format(positiveRation));
		System.out.println(df6.format(negativeRation));
		System.out.println(df6.format(zeroRation));
	}

	@Test
	void plusMinusTest() {

		//arr.lenth > 0
		//-100 <= arr[i] <= 100

		int[] arr = new int[] {-4, 3, -9, 0, 4, 1};
		int zerouCounter = 0;
		int positiveCounter = 0;
		int negativeCounter = 0;

		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == 0){
				++zerouCounter;
			} else if (arr[i] > 0){
				++positiveCounter;
			} else {
				++negativeCounter;
			}
		}

		double zeroRation = ((double) zerouCounter / arr.length);
		double positiveRation = ((double) positiveCounter / arr.length); //0.5
		double negativeRation = ((double) negativeCounter / arr.length);

		System.out.println(String.format("%.6f", positiveRation));
		System.out.println(String.format("%.6f", negativeRation));
		System.out.println(String.format("%.6f", zeroRation));

		df6.setRoundingMode(RoundingMode.HALF_UP);
		System.out.println(df6.format(positiveRation));
		System.out.println(df6.format(negativeRation));
		System.out.println(df6.format(zeroRation));

		assertEquals("0.500000", new DecimalFormat("0.000000").format(positiveRation));//tento patern doplni nuly na konec
		assertEquals("0.5", df6.format(positiveRation)); //ve vysledku chybi nuly a pokud je maska s # tak se nedoplnuji musi byt v masce 0
		assertEquals("0.333333", df6.format(negativeRation));
		assertEquals("0.166667", df6.format(zeroRation));
	}

}
