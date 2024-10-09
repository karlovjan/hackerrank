package algorithms.strings;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * edX.com
 * GTx CS1332xIV
 * Data Structures & Algorithms IV: Pattern Matching, Dijkstra's, MST, and
 * Dynamic Programming Algorithms
 */
public class RabinKarpAlgorithmTest {
    private static final int HASH_BASE = 1;

    List<Integer> rabinKarp(String text, String pattern) {
        if (text == null || pattern == null || text.isEmpty() || pattern.isEmpty() || pattern.length() > text.length()) {
            return List.of();
        }
        List<Integer> foundList = new ArrayList<>();
        int patternLength = pattern.length();
        int textLength = text.length();
        int patternHash = getHash(pattern);
        String textSubstring = text.substring(0, patternLength);
        int textHash = getHash(textSubstring);
        int i = 0;

        while (i <= textLength - patternLength) {
            if (patternHash == textHash) {
                int j = 0;
                while (j < patternLength && text.charAt(i + j) == pattern.charAt(j)) {
                    ++j;
                }

                if (j == patternLength) {
                    //match found at index j
                    foundList.add(i);
                }
            }
            if (i < textLength - patternLength) {
                textHash = roleTextHash(text, textHash, i, patternLength);
            }

            ++i;
        }

        return foundList;
    }

    private int roleTextHash(String text, int textHash, int startTextSubstring, int patternLength) {
        int oldCharHash = getHash(String.valueOf(text.charAt(startTextSubstring)));
        int newCharHash = getHash(String.valueOf(text.charAt(startTextSubstring + patternLength)));
        return (int) ((textHash - oldCharHash * Math.pow(HASH_BASE, patternLength - 1)) * HASH_BASE + newCharHash);
    }


    int getHash(String text) {
        int textLength = text.length();
        int hash = 0;
        for (int i = 0; i < textLength; i++) {
            hash += (int) (((int) text.charAt(i)) * Math.pow(HASH_BASE, textLength - (i + 1)));
        }

        return hash;
    }

    @Test
    void rabinKarp_empty_test() {
        Assertions.assertTrue(rabinKarp(null, null).isEmpty());
        Assertions.assertTrue(rabinKarp("", "").isEmpty());
        Assertions.assertTrue(rabinKarp("a", "").isEmpty());
        Assertions.assertTrue(rabinKarp("a", "b").isEmpty());
        Assertions.assertTrue(rabinKarp("a", "ab").isEmpty());
    }

    @Test
    void rabinKarp_one_char_test() {
        Assertions.assertIterableEquals(List.of(0), rabinKarp("a", "a"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("m", "m"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("z", "z"));
    }

    @Test
    void rabinKarp_two_chars_expect_zero_test() {
        Assertions.assertIterableEquals(List.of(0), rabinKarp("aa", "aa"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("zz", "zz"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("az", "az"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("cd", "c"));
        Assertions.assertIterableEquals(List.of(0), rabinKarp("cd", "cd"));
    }

    @Test
    void rabinKarp_two_chars_expect_non_zero_test() {
        Assertions.assertIterableEquals(List.of(1), rabinKarp("az", "z"));
        Assertions.assertIterableEquals(List.of(1), rabinKarp("cd", "d"));
    }

    @Test
    void rabinKarp_more_chars_expect_non_zero_test() {
        Assertions.assertIterableEquals(List.of(0, 2), rabinKarp("abab", "ab"));
        Assertions.assertIterableEquals(List.of(1), rabinKarp("cdz", "dz"));
        Assertions.assertIterableEquals(List.of(1, 3), rabinKarp("cdzdz", "dz"));
        Assertions.assertIterableEquals(List.of(35), rabinKarp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "aaaaab"));
        Assertions.assertIterableEquals(List.of(13), rabinKarp("datastructuresandalgo", "sand"));
    }

    @Test
    void rabinKarp_more_chars_expect_pattern_diff_test() {
        //worst case O(nm)
        Assertions.assertIterableEquals(List.of(), rabinKarp("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", "ccccccccccc"));
        Assertions.assertIterableEquals(List.of(), rabinKarp("datastructuresandalgo", "krldeda"));
    }

}
