package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A palindrome is a word, phrase, number, or other sequence of characters which reads the same backwards and forwards. Can you determine if a given string, s, is a palindrome?
 */
class StackQueuePalindromeTest {

    /*

Write the following declarations and implementations:

Two instance variables: one for your stack, and one for your queue.
A void pushCharacter(char ch) method that pushes a character onto a stack.
A void enqueueCharacter(char ch) method that enqueues a character in the queue instance variable.
A char popCharacter() method that pops and returns the character at the top of the stack instance variable.
A char dequeueCharacter() method that dequeues and returns the first character in the queue instance variable.

     */

    @Test
    void palindromeTest() {

        // Convert input String to an array of characters:
        char[] s = "racecar".toCharArray();

        // Create a Solution object:
        Palindrome p = new Palindrome();

        // Enqueue/Push all chars to their respective data structures:
        for (char c : s) {
            p.pushCharacter(c);
            p.enqueueCharacter(c);
        }

        // Pop/Dequeue the chars at the head of both data structures and compare them:
        boolean isPalindrome = true;
        //iteruju prvni polovinu prvku ze stacku a prvni polovinu prvku z fronty,
        // jestli ze jsou z obou stran stejne znaky jedna se o palindrom, slovo se cte z obou stran stejne
        for (int i = 0; i < s.length / 2; i++) {
            if (p.popCharacter() != p.dequeueCharacter()) {
                isPalindrome = false;
                break;
            }
        }

        assertTrue(isPalindrome);
    }

    static class Palindrome {
        private final Stack<Character> stack = new Stack<>();
        private final Queue<Character> queue = new LinkedList<>();

//        Deque<Character> oboustranaFronta = new ArrayDeque<>();

        /**
         * method that pushes a character onto a stack.
         *
         * @param ch a pushed character
         */
        void pushCharacter(char ch) {
            stack.push(ch);
        }

        /**
         * method that enqueues a character in the queue instance variable.
         *
         * @param ch the enqueueing character
         */
        void enqueueCharacter(char ch) {
            queue.add(ch);
        }

        /**
         * method that pops and returns the character at the top of the stack instance variable.
         *
         * @return a poped character
         */
        char popCharacter() {
            return stack.pop();
        }

        /**
         * method that dequeues and returns the first character in the queue instance variable.
         *
         * @return a dequeued character
         */
        char dequeueCharacter() {
            Character ch = queue.poll();
            return ch != null ? ch : ' ';
        }
    }
}
