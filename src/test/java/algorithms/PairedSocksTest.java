package algorithms;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.StreamUtil.groupByAndCountInt;

/**
 * https://www.hackerrank.com/challenges/sock-merchant/problem
 */
public class PairedSocksTest {


    /**
     * @param ar the colors of the socks in the pile
     * @return the number of pairs
     */
    int countPairedSock(int[] ar) {

        Map<Integer, Long> pairedSockMap;

        pairedSockMap = groupByAndCountInt(ar);
        //loop groups
        return pairedSockMap.values().stream().mapToInt(Long::intValue).map(this::mapPairCount).sum();
    }


    /**
     * @param ar the colors of the socks in the pile
     * @return the number of pairs
     */
    int countPairedSock_old(int[] ar) {

        Map<Integer, Integer> pairedSockMap = new HashMap<>();

        int paireCount;
        //group socks
        for (int item : ar) {
            if (pairedSockMap.containsKey(item)) {
                paireCount = pairedSockMap.get(item);
                pairedSockMap.put(item, ++paireCount);
            } else {
                pairedSockMap.put(item, 1);
            }
        }

        //loop groups
        return pairedSockMap.values().stream().mapToInt(this::mapPairCount).sum();
    }

    private int mapPairCount(int sockCount) {
        if (sockCount == 1) {
            return 0;
        }
        if (sockCount % 2 == 0) {
            return sockCount / 2;
        }

        //for odd number
        if ((sockCount - 1) % 2 == 0) {
            return (sockCount - 1) / 2;
        }

        return 0;
    }

    @Test
    void countPairedSockTest() {

        assertEquals(3, countPairedSock(new int[]{10, 20, 20, 10, 10, 30, 50, 10, 20}));
        assertEquals(2, countPairedSock(new int[]{1, 2, 1, 2, 1, 2, 3}));

    }
}
