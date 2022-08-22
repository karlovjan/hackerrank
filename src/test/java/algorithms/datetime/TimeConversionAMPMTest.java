package algorithms.datetime;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/one-week-preparation-kit-time-conversion/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=preparation-kits&playlist_slugs%5B%5D=one-week-preparation-kit&playlist_slugs%5B%5D=one-week-day-one
 */
public class TimeConversionAMPMTest {

	static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("hh:mm:ssa", Locale.ENGLISH);
	static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ENGLISH);

	/**
	 * Given a time in 12-hour AM/PM format, convert it to military (24-hour) time.
	 * <p>
	 * Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
	 * - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
	 *
	 * @param s a time in 12 hour format  (i.e.: hh:mm:ssAM or hh:mm:ssPM).
	 * @return It should return a new string representing the input time in 24 hour format.
	 */
	public static String timeConversion(String s) {

		return LocalTime.parse(s, inputFormatter).format(outputFormatter);
	}

	@Test
	void test_hackerrank() {
		Assertions.assertEquals("12:01:00", timeConversion("12:01:00PM"));
		Assertions.assertEquals("00:01:00", timeConversion("12:01:00AM"));
		Assertions.assertEquals("19:05:45", timeConversion("07:05:45PM"));
	}
}
