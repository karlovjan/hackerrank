package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class InterfaceDivisorTest {

	interface AdvancedArithmetic {
		int divisorSum(int a);
	}

	class Divisor implements AdvancedArithmetic {
		/*
		https://www2.karlin.mff.cuni.cz/~portal/mocniny/?page=Delitelnost



		Uvažujme čísla a, b∈N.
		Číslo a je násobkem čísla b (číslo b je dělitelem čísla a) právě tehdy,
		když existuje přirozené číslo n tak, že a=n⋅b.

přirozené číslo
http://ksp.mff.cuni.cz/forum/topic_show.pl?tid=1581
Kladne cislo vetsi nez nula.

		slozene cislo
		16=1⋅16		16=2⋅8	16=4⋅4
Množina všech dělitelů čísla 16: D(16)={1;2;4;8;16}

6=1⋅6		6=2⋅3 konec, dal uz se to opakuje obracene	6=3⋅2
Množina všech dělitelů čísla 6: D(6)={1;2;3;6}

9=1*9   9=3*3
D(9)={1;3;9}

10=1*10	10=2*5
D(9)={1;2;5;10}

12=1*12	(12/2=6) 12=2*6 (6/2=3) 12=3*4 {4 % 3 != 0} -> {3+1=4 == 4} -> konec
D(9)={1;2;3;4;6;12}


14=1*14	14=2*7 {7 % 2 != 0} -> konec
D(9)={1;2;5;10}

Prvocislo
17=1⋅17
Množina všech dělitelů čísla 17: D(17)={1;17}

Prvocislem neni cislo 1

Prvociselny rozklad cisla: 126


16 = 2*8 (8/2=4) 2*4 (4/2=2) 2*2
16 = 2*2*2*2

126 = 2*63, 3*21, 3*7
126 = 2*3*3*7
		 */

		public int divisorSumStream(int a) {

			if (a == 1) {
				return 1;
			}

			Stream.Builder<Integer> builder = Stream.builder();

			builder.accept(1);
			builder.accept(a);

			int max = a / 2; //deleno prvnim prvocislem
			//			n = 2 prvni prvocislo
			for (int n = 2; n <= max; n++) {
				if (a % n == 0) {
					builder.accept(a / n);
				}
			}

			return builder.build().mapToInt(Integer::intValue).sum();
		}

		public int divisorSum(int a) {

			if (a == 1) {
				return 1;
			}

			List<Integer> builder = new ArrayList<>();

			builder.add(1);
			builder.add(a);

			int max = a / 2; //deleno prvnim prvocislem
			//			n = 2 prvni prvocislo
			for (int n = 2; n <= max; n++) {
				if (a % n == 0) {
					builder.add(a / n);
				}
			}

			return builder.stream().mapToInt(Integer::intValue).sum();
		}
	}

	@Test
	void divisorOneTest() {

		AdvancedArithmetic calculator = new Divisor();

		assertEquals(1, calculator.divisorSum(1));
		assertEquals(3, calculator.divisorSum(2));
	}

	@Test
	void divisorTest() {

		AdvancedArithmetic calculator = new Divisor();

		assertEquals(12, calculator.divisorSum(6));
		assertEquals(15, calculator.divisorSum(8));
		assertEquals(31, calculator.divisorSum(16)); //D(16)={1;2;4;8;16}
	}

	@Test
	void divisorStreamTest() {

		Divisor calculator = new Divisor();

		assertEquals(12, calculator.divisorSumStream(6));
		assertEquals(15, calculator.divisorSumStream(8));
		assertEquals(31, calculator.divisorSumStream(16)); //D(16)={1;2;4;8;16}
	}
}
