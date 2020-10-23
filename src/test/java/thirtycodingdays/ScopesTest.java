package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ScopesTest {

	class Difference {
		private int[] elements;
		int maximumDifference;

		Difference(int[] elements) {
			this.elements = elements;
		}

		void computeDifference() {
			for (int i = 0; i < elements.length - 1; i++) {
				for (int j = 1; j < elements.length; j++) {
					maximumDifference = Math.max(maximumDifference, Math.abs(elements[i] - elements[j]));
				}
			}
		}

	}

	@Test
	void computeDifferenceTest() {
		Difference difference = new Difference(new int[] { 1, 2, 5 });

		difference.computeDifference();

		assertEquals(4, difference.maximumDifference);
	}

	@Test
	void computeDifferenceTestN1() {
		Difference difference = new Difference(new int[] { 100 });

		difference.computeDifference();

		assertEquals(0, difference.maximumDifference);
	}
}
