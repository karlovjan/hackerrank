package algorithms;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

public class CompareTripletsTest {

	@Test
	void compareTriplets() {
		List<Integer> a = Arrays.asList(17, 28, 30);
		List<Integer> b = Arrays.asList(99, 16, 8);

		AtomicInteger scoreA = new AtomicInteger();
		AtomicInteger scoreB = new AtomicInteger();

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i) > b.get(i)) {
				scoreA.incrementAndGet();
			} else if (a.get(i) < b.get(i)) {
				scoreB.incrementAndGet();
			}
		}

		List<Integer> score = Arrays.asList(scoreA.get(), scoreB.get());

		assertEquals(2, score.size());
		assertEquals(2, score.get(0));
		assertEquals(1, score.get(1));

	}

}
