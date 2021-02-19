package algorithms;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/grading/problem
 */
public class GradingStudentsTest {

	public static List<Integer> gradingStudents(List<Integer> grades) {

		return grades.stream().filter(Objects::nonNull).map(GradingStudentsTest::roundTheGrade)
				.collect(Collectors.toList());

	}

	private static Integer roundTheGrade(Integer grade) {
		if (grade < 38) {
			return grade;
		}

		int nextMultipleFive = findNextMultipleFive(grade);

		if (nextMultipleFive - grade < 3) {
			return nextMultipleFive;
		}

		return grade;
	}

	private static int findNextMultipleFive(int grade) {

		return Stream.iterate(++grade, i -> ++i).limit(5).mapToInt(Integer::intValue).filter(g -> g % 5 == 0)
				.findFirst().orElse(grade);
	}

	@Test
	void gradingStudentsTest() {

		assertIterableEquals(List.of(75, 67, 40, 33, 0, 100), gradingStudents(List.of(73, 67, 38, 33, 0, 100)));
	}
}
