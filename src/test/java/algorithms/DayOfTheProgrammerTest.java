package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/day-of-the-programmer/problem
 * <p>
 * https://en.wikipedia.org/wiki/Day_of_the_Programmer
 */
public class DayOfTheProgrammerTest {

	static final String PROGRAMMER_DAY_FORMAT = "%d.%s.%d";

	static class JulianCalendarInRussia {
		static final int FROM_YEAR = 1700;
		static final int TO_YEAR = 1917;
	}

	static class GregorianCalendarInRussia {
		static final int FROM_YEAR = 1918;
		static final int TO_YEAR = 2700;
	}

	static final int PROGRAMMER_DAY_TRANSITION_YEAR = 12;
	static final int PROGRAMMER_DAY = 13;
	static final String PROGRAMMER_MONTH = "09";
	static final int TRANSITION_YEAR_BETWEEN_JULIAN_GREGORIAN_CALENDAR = 1918;
	static final int DAYS_IN_JANUARY_AND_FEBRUARY_IN_TRANSITION_YEAR =
			31 + 28 - 14; //this means that in 1918, February 14th was the 32nd day of the year in Russia.

	static boolean isTransitionYearInJulianCalendar(int year) {
		return year % 4 == 0;
	}

	static boolean isTransitionYearInGregorianCalendar(int year) {
		return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
	}

	/**
	 * Given a year, find the date of the 256th day of that year
	 * according to the official Russian calendar during that year
	 *
	 * @param year during a year in the inclusive range from 1700 to 2700.
	 * @return The programmer day in the format dd.mm.yyyy,
	 */
	static String dayOfProgrammer(int year) {
		if (year >= JulianCalendarInRussia.FROM_YEAR && year <= JulianCalendarInRussia.TO_YEAR) {
			if (isTransitionYearInJulianCalendar(year)) {
				return String.format(PROGRAMMER_DAY_FORMAT, PROGRAMMER_DAY_TRANSITION_YEAR, PROGRAMMER_MONTH, year);
			}
			return String.format(PROGRAMMER_DAY_FORMAT, PROGRAMMER_DAY, PROGRAMMER_MONTH, year);
		}

		//all others count in Gregorian calendar system

		if (isTransitionYearInGregorianCalendar(year)) {
			return String.format(PROGRAMMER_DAY_FORMAT, PROGRAMMER_DAY_TRANSITION_YEAR, PROGRAMMER_MONTH, year);
		}

		if (year == TRANSITION_YEAR_BETWEEN_JULIAN_GREGORIAN_CALENDAR) {
			//rok 1918 neni prestupny, mel bybyt 13. den ale 14. unora byl 32. den v roce, ale mel by byt 31 + 14 = 45
			return String.format(PROGRAMMER_DAY_FORMAT, 13 + (31 + 14 - 32), PROGRAMMER_MONTH, year);
		}

		return String.format(PROGRAMMER_DAY_FORMAT, PROGRAMMER_DAY, PROGRAMMER_MONTH, year);

	}

	static String dayOfProgrammer2(int year) {

		return java.time.LocalDate.of(year, 1, 1).plusDays(256 - 1)
				.format(DateTimeFormatter.ofPattern("dd.MM.yyyy", new Locale("ru", "RU")));

		/*
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Moscow"), new Locale("ru", "RU"));

		calendar.set(year, 0, 1, 10, 0);
		calendar.add(Calendar.DAY_OF_YEAR, 256 - 1);

		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		return format.format(calendar.getTime());

		 */
	}

	@Test
	void dayOfProgrammerTest_1() {

		assertEquals("13.09.2017", dayOfProgrammer(2017));
		assertEquals("13.09.2017", dayOfProgrammer2(2017));

		assertEquals("12.09.2016", dayOfProgrammer(2016));
		assertEquals("12.09.2016", dayOfProgrammer2(2016));

		assertEquals("12.09.1800", dayOfProgrammer(1800));
		//		assertEquals("12.09.1800", dayOfProgrammer2(1800));

		assertEquals("26.09.1918", dayOfProgrammer(1918));
		//		assertEquals("12.09.1918", dayOfProgrammer2(1918));

	}
}
