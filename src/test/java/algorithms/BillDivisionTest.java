package algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.StreamUtil.filterStreamByIndex;

/**
 * https://www.hackerrank.com/challenges/bon-appetit/problem
 *
 */
public class BillDivisionTest {


    /**
     *
     * @param bill an array of integers representing the cost of each item ordered
     * @param k an integer representing the zero-based index of the item Anna doesn't eat
     * @param b the amount of money that Anna contributed to the bill
     * @return overcharged value
     */
    int splitTheBill (List<Integer> bill, int k, int b){
//        bill.remove(k);

        return b - filterStreamByIndex(bill.stream(), k).mapToInt(Integer::intValue).sum() / 2;
    }


    boolean isTheBillFairlySplit (int overcharged){
        return overcharged == 0;
    }

    void bonAppetit(int overcharged) {

        if(isTheBillFairlySplit(overcharged)) {
            System.out.print("Bon Appetit");
        } else {
            System.out.print(overcharged);
        }
    }

    @Test
    void splitthebillyest() {

        assertEquals(5, splitTheBill(List.of(3,10,2,9), 1, 12));
        assertEquals(0, splitTheBill(List.of(3,10,2,9), 1, 7));
    }
}
