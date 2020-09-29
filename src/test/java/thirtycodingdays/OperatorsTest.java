package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.text.NumberFormatter;

import org.junit.jupiter.api.Test;

public class OperatorsTest {

	// Complete the solve function below.
	static long mealPriceTotal(double meal_cost, int tip_percent, int tax_percent) {

		double tip = meal_cost * tip_percent / 100;
		double tax = meal_cost * tax_percent / 100;

		long result = Math.round(meal_cost + tax + tip);

		System.out.println(result);

		return result;
	}

	@Test
	void mealPriceTest() {

		assertEquals(15l, mealPriceTotal(12, 20, 8));
	}
}
