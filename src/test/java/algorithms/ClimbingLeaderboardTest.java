package algorithms;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/climbing-the-leaderboard/problem
 * <p>
 * An arcade game player wants to climb to the top of the leaderboard and track their ranking. The game uses Dense Ranking,
 * so its leaderboard works like this:
 * <p>
 * The player with the highest score is ranked number  on the leaderboard.
 * Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately following ranking number.
 */
public class ClimbingLeaderboardTest {

	/**
	 * @param ranked the leaderboard scores in decreasing order.
	 * @param player The player's scores in ascending order.
	 * @return the player's rank after each new score
	 */
	public static List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {

		if (player.isEmpty()) {
			return new ArrayList<>();
		}

		//min score is grater than max rank in  leaderboard
		if (ranked.isEmpty() || player.get(0) >= ranked.get(0)) {
			return IntStream.generate(() -> 1).limit(player.size()).boxed().collect(Collectors.toList());
		}

		int r = 0;
		int p = player.size() - 1;
		int rankCounter = 0;
		int score, rank = 0;
		int lastRank;

		List<Integer> result = new ArrayList<>(player.size());
		for (; p >= 0 && r < ranked.size() && result.size() < player.size(); ++r) {
			lastRank = rank;
			score = player.get(p);
			if (lastRank > score || lastRank == 0) {
				rank = ranked.get(r);
			} else {
				--r;
			}

			if (rank != lastRank) {
				++rankCounter;
			}

			if (rank == score) {
				result.add(0, rankCounter);
				--p;
			} else if (rank < score) {
				result.add(0, rankCounter);
				--p;
			}
		}

		if (result.size() < player.size()) {
			int maxRankCounter = rankCounter;
			int lastScore;
			for (; p >= 0; --p) {
				lastScore = player.size() - 1 > p ? player.get(p + 1) : -1;
				score = player.get(p);
				if (score == rank || score == lastScore) {
					result.add(0, rankCounter);
				} else {
					if (rankCounter <= maxRankCounter) {
						++rankCounter;
					}
					result.add(0, rankCounter);
				}

			}
		}

		return result;

	}

	@Test
	void test1() {
		assertIterableEquals(List.of(1, 1, 1, 1, 1),
				climbingLeaderboard(List.of(5, 4, 3, 2, 1), List.of(6, 7, 8, 9, 10)));
		assertIterableEquals(List.of(1, 1, 1, 1, 1, 1),
				climbingLeaderboard(List.of(5, 4, 3, 2, 1), List.of(5, 5, 5, 8, 9, 10)));
		assertIterableEquals(List.of(1, 1, 1, 1, 1, 1),
				climbingLeaderboard(List.of(5, 5, 3, 3, 1), List.of(5, 5, 5, 8, 9, 10)));
	}

	@Test
	void test2() {
		assertIterableEquals(List.of(4, 4, 4, 3, 3), climbingLeaderboard(List.of(10, 8, 5), List.of(1, 1, 4, 5, 5)));
		assertIterableEquals(List.of(4, 4, 4, 3, 3),
				climbingLeaderboard(List.of(10, 8, 8, 5, 5), List.of(1, 1, 4, 5, 5)));
		assertIterableEquals(List.of(2, 2, 2, 2, 2),
				climbingLeaderboard(List.of(10, 10, 10, 10), List.of(1, 1, 4, 5, 5)));
	}

	@Test
	void test3() {
		assertIterableEquals(List.of(4, 3, 1), climbingLeaderboard(List.of(100, 90, 90, 80), List.of(70, 80, 105)));
		assertIterableEquals(List.of(4, 3, 2), climbingLeaderboard(List.of(100, 100, 90, 90, 80), List.of(70, 80, 90)));
		assertIterableEquals(List.of(6, 4, 2, 1),
				climbingLeaderboard(List.of(100, 100, 50, 40, 40, 20, 10), List.of(5, 25, 50, 120)));
		assertIterableEquals(List.of(6, 5, 4, 2, 1),
				climbingLeaderboard(List.of(100, 90, 90, 80, 75, 60), List.of(50, 65, 77, 90, 102)));
	}

	@Test
	void test4() {
		assertIterableEquals(List.of(), climbingLeaderboard(List.of(), List.of()));
		assertIterableEquals(List.of(), climbingLeaderboard(List.of(5, 4, 3, 2, 1), List.of()));
		assertIterableEquals(List.of(1, 1, 1, 1, 1, 1), climbingLeaderboard(List.of(), List.of(6, 6, 7, 8, 9, 10)));
	}

	@Test
	void test5() {
		assertIterableEquals(List.of(6, 5, 4, 2, 1),
				climbingLeaderboard(List.of(100, 90, 90, 80, 75, 60), List.of(50, 65, 77, 90, 102)));
	}

	@Test
	void test6() {
		assertIterableEquals(
				List.of(88, 88, 87, 85, 84, 84, 83, 82, 81, 81, 80, 80, 80, 80, 79, 79, 79, 79, 79, 79, 79, 79, 77, 75,
						75, 74, 73, 73, 73, 71, 71, 71, 71, 71, 71, 70, 70, 69, 69, 68, 68, 68, 68, 67, 67, 67, 66, 66,
						65, 65, 64, 64, 62, 61, 61, 60, 59, 59, 59, 59, 59, 59, 58, 57, 56, 56, 55, 55, 53, 52, 52, 51,
						51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 51, 50, 50, 50, 50, 49, 49, 48, 48, 47, 47, 47, 45,
						43, 42, 42, 41, 38, 36, 36, 36, 36, 35, 35, 35, 35, 35, 35, 34, 34, 34, 33, 33, 33, 33, 33, 32,
						30, 28, 28, 28, 28, 27, 27, 27, 26, 23, 23, 22, 22, 20, 20, 20, 18, 18, 15, 15, 14, 14, 13, 13,
						11, 11, 10, 10, 8, 8, 7, 6, 5, 4, 4, 4, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
						1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1), climbingLeaderboard(
						List.of(295, 294, 291, 287, 287, 285, 285, 284, 283, 279, 277, 274, 274, 271, 270, 268, 268,
								268, 264, 260, 259, 258, 257, 255, 252, 250, 244, 241, 240, 237, 236, 236, 231, 227,
								227, 227, 226, 225, 224, 223, 216, 212, 200, 197, 196, 194, 193, 189, 188, 187, 183,
								182, 178, 177, 173, 171, 169, 165, 143, 140, 137, 135, 133, 130, 130, 130, 128, 127,
								122, 120, 116, 114, 113, 109, 106, 103, 99, 92, 85, 81, 69, 68, 63, 63, 63, 61, 57, 51,
								47, 46, 38, 30, 28, 25, 22, 15, 14, 12, 6, 4),
						List.of(5, 5, 6, 14, 19, 20, 23, 25, 29, 29, 30, 30, 32, 37, 38, 38, 38, 41, 41, 44, 45, 45, 47,
								59, 59, 62, 63, 65, 67, 69, 70, 72, 72, 76, 79, 82, 83, 90, 91, 92, 93, 98, 98, 100,
								100, 102, 103, 105, 106, 107, 109, 112, 115, 118, 118, 121, 122, 122, 123, 125, 125,
								125, 127, 128, 131, 131, 133, 134, 139, 140, 141, 143, 144, 144, 144, 144, 147, 150,
								152, 155, 156, 160, 164, 164, 165, 165, 166, 168, 169, 170, 171, 172, 173, 174, 174,
								180, 184, 187, 187, 188, 194, 197, 197, 197, 198, 201, 202, 202, 207, 208, 211, 212,
								212, 214, 217, 219, 219, 220, 220, 223, 225, 227, 228, 229, 229, 233, 235, 235, 236,
								242, 242, 245, 246, 252, 253, 253, 257, 257, 260, 261, 266, 266, 268, 269, 271, 271,
								275, 276, 281, 282, 283, 284, 285, 287, 289, 289, 295, 296, 298, 300, 300, 301, 304,
								306, 308, 309, 310, 316, 318, 318, 324, 326, 329, 329, 329, 330, 330, 332, 337, 337,
								341, 341, 349, 351, 351, 354, 356, 357, 366, 369, 377, 379, 380, 382, 391, 391, 394,
								396, 396, 400)));
	}

	@Test
	void test7() {
		assertIterableEquals(List.of(4, 4, 3, 1, 1),
				climbingLeaderboard(List.of(14, 12, 6, 4), List.of(5, 5, 6, 14, 19)));
	}
}
