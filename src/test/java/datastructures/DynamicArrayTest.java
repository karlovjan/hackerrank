package datastructures;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class DynamicArrayTest {

	static List<Integer> dynamicArray(int n, List<List<Integer>> queries) {
		// Write your code here

		List<Integer> lastQueryList = new ArrayList<>();

		//generate n sequencies
		List<List<Integer>> seqList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			seqList.add(new ArrayList<>());
		}

		int lastAnswer = 0;

		for (List<Integer> query : queries) {

			if (Integer.valueOf(1).equals(query.get(0))) {
				//first query type
				seqList.get((query.get(1) ^ lastAnswer) % n).add(query.get(2));
			} else {
				//second query type
				List<Integer> seq = seqList.get((query.get(1) ^ lastAnswer) % n);
				lastAnswer = seq.get(query.get(2) % seq.size());
				lastQueryList.add(lastAnswer);
			}
		}

		return lastQueryList;
	}

	@Test
	void dynamicArrayTest() {

		List<List<Integer>> queries = new ArrayList<>();

		List<Integer> query = new ArrayList<>();
		query.add(1);
		query.add(0);
		query.add(5);
		queries.add(query);
		query = new ArrayList<>();
		query.add(1);
		query.add(1);
		query.add(7);
		queries.add(query);
		query = new ArrayList<>();
		query.add(1);
		query.add(0);
		query.add(3);
		queries.add(query);
		query = new ArrayList<>();
		query.add(2);
		query.add(1);
		query.add(0);
		queries.add(query);
		query = new ArrayList<>();
		query.add(2);
		query.add(1);
		query.add(1);
		queries.add(query);

		List<Integer> result = dynamicArray(2, queries);
		assertEquals(2, result.size(), "length");
		assertEquals(7, result.get(0), "prvni");
		assertEquals(3, result.get(1), "druhy");
	}
}
