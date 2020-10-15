package ai;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * In this version of "Bot saves princess", Princess Peach and bot's position are randomly set. Can you save the princess?
 */
class SavePrincess2_2Test {

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

/*
Complete the function nextMove which takes in 4 parameters - an integer N,
integers r and c indicating the row & column position of the bot
and the character array grid -
and outputs the next move the bot makes to rescue the princess..

The first line of the input is N (<100), the size of the board (NxN).
The second line of the input contains two space separated integers, which is the position of the bot.

The position of the princess is indicated by the character 'p' and the position of the bot is indicated by
the character 'm' and each cell is denoted by '-' (ascii value: 45).

Output only the next move you take to rescue the princess. Valid moves are LEFT, RIGHT, UP or DOWN
 */

		int n, r, c;
		String[] grid;

		try (Scanner scan = new Scanner(System.in)) {

			n = scan.nextInt();
			r = scan.nextInt();
			c = scan.nextInt();
			scan.useDelimiter("\n");
			grid = new String[n];

			for (int i = 0; i < n; i++) {
				grid[i] = scan.next();
			}

		}

		nextMove(n, r, c, grid);
	}

	/**
	 * Output only the next move you take to rescue the princess. Valid moves are LEFT, RIGHT, UP or DOWN
	 *
	 * @param n    matrix dimension
	 * @param r    bot i matrix coordinate
	 * @param c    bot j matrix coordinate
	 * @param grid matrix
	 */
	static void nextMove(int n, int r, int c, String[] grid) {

		Point botPoint = new Point(r, c);

		Point princessPoint = findPrincessPoint(grid);

		findNextMove(botPoint, princessPoint).forEach(System.out::println);

	}

	private static List<Movements> findNextMove(Point startPoint, Point finalPoint) {
		List<Movements> movements = new ArrayList<>();
		int distanceX = finalPoint.x - startPoint.x;
		int distanceY = finalPoint.y - startPoint.y;

		if (distanceX == 0 && distanceY == 0) {
			return new ArrayList<>();
		}

		int maxMovements = 1;

		//look at two next bot vertex of the grid
		//is the princess under the bot?
		Point nextPointInX;
		//is the princess on the right side from the bot?
		Point nextPointInY;
		Point startPointTmp = startPoint;

		Movements nextMovementX;
		Movements nextMovementY;

		double distance1;
		double distance2;

		for (int i = 0; i < maxMovements; i++) {

			//is the princess on the right side from the bot?
			if (distanceY > 0) {
				nextMovementX = Movements.RIGHT;
				nextPointInX = new Point(startPointTmp.x, startPointTmp.y + 1);
			} else {
				//the princess is on the left side from the bot
				nextMovementX = Movements.LEFT;
				nextPointInX = new Point(startPointTmp.x, startPointTmp.y - 1);
			}
			//is the princess under the bot?
			if (distanceX > 0) {
				nextMovementY = Movements.DOWN;
				nextPointInY = new Point(startPointTmp.x + 1, startPointTmp.y);
			} else {
				//the princess is above the bot
				nextMovementY = Movements.UP;
				nextPointInY = new Point(startPointTmp.x - 1, startPointTmp.y);
			}

			if (distanceX == 0) {
				//finish
				movements.add(nextMovementX);
				break;
			}
			if (distanceY == 0) {
				//finish
				movements.add(nextMovementY);
				break;
			}

			distance1 = nextPointInX.distance(finalPoint);
			distance2 = nextPointInY.distance(finalPoint);

			if (distance1 < distance2) {
				movements.add(nextMovementX);
				startPointTmp = nextPointInX;
			} else {
				movements.add(nextMovementY);
				startPointTmp = nextPointInY;
			}

			distanceX = finalPoint.x - startPointTmp.x;
			distanceY = finalPoint.y - startPointTmp.y;
		}

		return movements;
	}

	private static Point findPrincessPoint(String[] grid) {

		int p;
		for (int i = 0; i < grid.length; i++) {
			p = grid[i].indexOf('p');
			if (p >= 0) {
				return new Point(i, p);
			}
		}
		return new Point();
	}

	@Test
	void botTheSameAsPrincess() {

		assertIterableEquals(List.of(), findNextMove(new Point(1, 1), new Point(1, 1)));
	}

	@Test
	void trappThePrincessTest_dim3() {

		assertIterableEquals(List.of(Movements.LEFT), findNextMove(new Point(1, 1), new Point(1, 0)));
	}

	@Test
	void trappThePrincessTest_dim4() {

		assertIterableEquals(List.of(Movements.RIGHT), findNextMove(new Point(1, 1), new Point(0, 4)));
	}

	@Test
	void trappThePrincessTest_dim3_2() {

		assertIterableEquals(List.of(Movements.UP), findNextMove(new Point(2, 2), new Point(0, 0)));
	}

	@Test
	void trappThePrincessTest_dim3_3() {

		assertIterableEquals(List.of(Movements.DOWN), findNextMove(new Point(1, 1), new Point(4, 2)));
	}

	private enum Movements {
		LEFT, RIGHT, UP, DOWN
	}

}
