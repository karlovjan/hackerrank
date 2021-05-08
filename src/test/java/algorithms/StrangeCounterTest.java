package algorithms;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * https://www.hackerrank.com/challenges/strange-code/problem
 */
public class StrangeCounterTest {

    /**
     * At the first second, it displays the number 3.
     * Each second, the number displayed by decrements by 1 until it reaches 1.
     * In next second, the timer resets to 2 x the initial nymber for the prior cycle and continues counting down.
     * Find and print the value displayed by the counter at time t.
     *
     * @param t time
     * @return the value displayed at time
     */
    public static long strangeCounter_old(long t) {
        // 1 2 3 -4- 5 6 7 8 9 -10- 11 12 13 14 15 16 17 18 19 20 21 -22
        // 3 2 1 -6- 5 4 3 2 1 -12- 11 10 9  8   7  6  5  4  3  2  1 -24

        //v = n - ( t - 1 )
        //v t == n -> v = 1, tkn = (tkn-1 - tkn-2) x 2 + tkn-1

        long tk = 3;
        long tk1 = 0;
        long tk2;
        while (t > tk) {
            tk2 = tk1;
            tk1 = tk;
            tk = (tk1 - tk2) * 2 + tk1;
        }

        long n = tk - tk1;
        long t0 = tk1 + 1;

        long v = n - (t - t0);

        return v;
    }

    long strangeCounter_1(long t) {
        long n = 2;
        while (3 * (n - 1) < t) {
            n = 2 * n;
        }
        return (3 * (n - 1) - t + 1);
    }

    long strangeCounter_2(long t) {
        long rem = 3;
        while (t > rem) {
            t = t - rem;
            rem *= 2;
        }

        return rem - t + 1;
    }

    long strangeCounter_3(long t) {
        long v = 4;

        while (v <= t) {
            v = v * 2 + 2;
        }
        return v - t;
    }

    long strangeCounter_4(long t) {
        long x = (t + 2) / 3;
        long b = Long.highestOneBit(x);
        long tb = 3 * b;
        return tb - (t - (tb - 2));
    }

    long strangeCounter(long t) {
        /*
        Starting time of each cycle is froming a sequence and starting value of each cycle is forming a G.P.
Starting value G.P: => 3, 6, 12, 24, 48, 96, 192 => 3, 3*2^1, 3*2^2, 3*2^3 ...... 3*(2)^(N-1)
and Starting times of N-th cycle will be 3*(2)^(N-1)-2
And 'N' (cycle to which 'T' belongs) can be calculated by, N = log2((T+2)/3) Simple O(1) Java Solution:
         */
        long cycle = (long) (Math.log((t + 2) / 3) / Math.log(2)) + 1;
        long startTime = 3L * (long) Math.pow(2, cycle - 1) - 2;
        long startValue = startTime + 2;
        return (startValue) - (t - startTime);
    }


    @Test
    void test1() {
        Assertions.assertEquals(6, strangeCounter(4));
        Assertions.assertEquals(3, strangeCounter(1));
        Assertions.assertEquals(1, strangeCounter(3));

        Assertions.assertEquals(5, strangeCounter(5));
        Assertions.assertEquals(4, strangeCounter(6));
        Assertions.assertEquals(1, strangeCounter(9));
        Assertions.assertEquals(12, strangeCounter(10));
        Assertions.assertEquals(1, strangeCounter(21));
        Assertions.assertEquals(24, strangeCounter(22));

    }

    @Test
    void test2() {
        Assertions.assertEquals(610612734, strangeCounter(1000000000));
    }
}
