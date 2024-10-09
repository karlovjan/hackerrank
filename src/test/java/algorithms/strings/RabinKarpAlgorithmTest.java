package algorithms.strings;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * edX.com
 * GTx CS1332xIV
 * Data Structures & Algorithms IV: Pattern Matching, Dijkstra's, MST, and
 * Dynamic Programming Algorithms
 */
public class RabinKarpAlgorithmTest {

    List<Integer> rubinKarp(String text, String pattern) {
      var foundList = new ArrayList<>();
      
      
    }


    int getHash(String text, int base){
        int textLength = text.length();
        int hash = 0;
        for (int i = 0; i < textLength; i++) {
            hash += ((int)text.charAt(i))*Math.pow(base, textLength - (i + 1));
        }

        return hash;
    }

}
