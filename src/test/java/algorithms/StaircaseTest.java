package algorithms;

public class StaircaseTest {
	public static void main(String[] args) {
		//		new Staircase(6).print();
		new Staircase(6, false).print();
	}
}

class Staircase {

	private int steps;
	private boolean rightAligned;
	private char symbol;

	Staircase(int steps) {
		this(steps, '#', true);
	}

	Staircase(int steps, boolean rightAligned) {
		this(steps, '#', rightAligned);
	}

	Staircase(int steps, char symbol, boolean rightAligned) {
		validateSteps(steps);
		this.steps = steps;
		this.rightAligned = rightAligned;
		this.symbol = symbol;
	}

	private void validateSteps(int steps) {
		if (steps < 0 || steps > 100) {
			throw new IllegalArgumentException("'steps' is 0<=n<=100");
		}
	}

	void print() {
		if (rightAligned) {
			printFromRight();
		} else {
			printFromLeft();
		}
	}

	private void printFromRight() {

		for (int i = 1; i < steps + 1; i++) {
			for (int j = 0; j < steps - i; j++) {
				System.out.print(' ');
			}
			for (int j = 0; j < i; j++) {
				System.out.print(symbol);
			}
			System.out.println();
		}
	}

	private void printFromLeft() {
		for (int i = 1; i < steps + 1; i++) {
			for (int j = 0; j < i; j++) {
				System.out.print(symbol);
			}
			for (int j = 0; j < steps - i; j++) {
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}
