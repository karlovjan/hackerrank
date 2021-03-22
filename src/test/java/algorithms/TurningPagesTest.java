package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/drawing-book/problem
 * <p>
 * A teacher asks the class to open their books to a page number. A student can
 * either start turning pages from the front of the book or from the back of the book.
 * They always turn pages one at a time.
 * When they open the book, page 1 is always on the right side:
 */
public class TurningPagesTest {

	/**
	 * Given n and p, find and print the minimum number of pages that must be turned in order to arrive at page p.
	 *
	 * @param n the number of pages in the book. n = {1, 100000}
	 * @param p the page to turn to. p is in {1, n}
	 * @return min pages to the page p
	 */
	int pageCount(int n, int p) {

/*
		int half = n / 2;

		if(half >= p) {
			//start from beginning
			return p / 2;
		} else {
			// start turning pages from end the book
//			return (n - p) / 2;
			return n / 2 - p / 2;
		}

 */
		//neprojde v jednom testu, posledni test 26; kvuli tomu ze (n - p) / 2 != n/2 - p/2

		/*

podle matematiky se to rovna
https://matematika.cz/zlomky
		(n - p) / 2 = n/2 - p/2

		asi nejaky zaokrouhlovaci problem

		 */

		return Math.min(p / 2, n / 2 - p / 2);
	}

	@Test
	void pageCountTest() {

		assertEquals(0, pageCount(1, 1));
		assertEquals(0, pageCount(100000, 100000));
		assertEquals(0, pageCount(100000, 1));

		assertEquals(2, pageCount(10, 5)); //zleva i zprava je to stejne
		assertEquals(1, pageCount(5, 3)); //zleva i zprava je to stejne

		assertEquals(1, pageCount(6, 2));
		assertEquals(0, pageCount(9, 8)); //[01 23 45 67 89 1011 1213]
		assertEquals(2, pageCount(13, 5)); //[01 23 45 67 89 1011 1213]

		assertEquals(1, pageCount(9, 6));
		assertEquals(2, pageCount(12, 8));
		//[01 23 45 67 89 1011 1213 1415 1617 1819]
		assertEquals(4, pageCount(19, 10));
		//[01 23 45 67 89 1011 1213 1415 1617 1819 2021 22]
	}
}
