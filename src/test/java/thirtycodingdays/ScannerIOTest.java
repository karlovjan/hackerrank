package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerIOTest {

    //	https://www.hackerrank.com/challenges/30-hello-world/tutorial

    public static void main(String[] args) {

//        readLineByLine();
        readLineOfIntegers();
    }

    private static void readLineByLine() {
        int count;

        List<String> texts = new ArrayList<>();

        try (Scanner scan = new Scanner(System.in)) {

            count = scan.nextInt();

            scan.nextLine();

            for (int i = 0; i < count; i++) {

                texts.add(scan.nextLine());

            }
        }

        List<String> results = getEvenAndOddChars(count, texts);

        results.forEach(System.out::println);
    }

    static void readLineOfIntegers() {
        String[] arrActual;
        String[] arrExpect;
        try (final Scanner scanner = new Scanner(System.in)) {
            arrActual = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            arrExpect = scanner.nextLine().split(" ");
        }

        Stream.of(arrActual).mapToInt(Integer::valueOf).forEach(System.out::println);
        Stream.of(arrExpect).mapToInt(Integer::valueOf).forEach(System.out::println);

        List<Integer> expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());
    }

    private static List<String> getEvenAndOddChars(int count, List<String> inputStrings) {

        List<String> resultArray = new ArrayList<>(count);

        char[] chars;

        StringBuilder evenSb;
        StringBuilder oddSb;

        for (String inputText : inputStrings) {
            chars = inputText.toCharArray();

            evenSb = new StringBuilder();
            oddSb = new StringBuilder();

            for (int i = 0; i < chars.length; i++) {
                if (i % 2 == 0) {
                    //even char
                    evenSb.append(chars[i]);
                } else {
                    //even char
                    oddSb.append(chars[i]);
                }
            }

            resultArray.add(evenSb.toString() + " " + oddSb.toString());
        }

        return resultArray;
    }

    @Test
    void readFromOutputTest() {

        int count = 2;

        List<String> results = getEvenAndOddChars(count, List.of("Hacker", "Rank"));

        assertEquals(count, results.size());
        assertEquals("Hce akr", results.get(0));
        assertEquals("Rn ak", results.get(1));
    }

    /*
    https://www.hackerrank.com/challenges/30-nested-logic/problem

    Day 26: Nested Logic


If the book is returned on or before the expected return date, no fine will be charged (i.e.: FINE = 0.
If the book is returned after the expected return day but still within the same calendar month and year as the expected return date, FINE = 15.
If the book is returned after the expected return month but still within the same calendar year as the expected return date, the FINE = 500.
If the book is returned after the calendar year in which it was expected, there is a fixed fine of FINE = 10000.

     */

    private int calculateFine(Calendar expectedDate, Calendar actualDate) {

        if (expectedDate.get(Calendar.YEAR) < actualDate.get(Calendar.YEAR)) {
            return 10000;
        } else if (expectedDate.get(Calendar.YEAR) == actualDate.get(Calendar.YEAR) && expectedDate.get(Calendar.MONTH) < actualDate.get(Calendar.MONTH)) {
            return 500 * (actualDate.get(Calendar.MONTH) - expectedDate.get(Calendar.MONTH));
        } else if (expectedDate.get(Calendar.YEAR) == actualDate.get(Calendar.YEAR) && expectedDate.get(Calendar.MONTH) == actualDate.get(Calendar.MONTH) && expectedDate.get(Calendar.DATE) < actualDate.get(Calendar.DATE)) {
            return 15 * (actualDate.get(Calendar.DATE) - expectedDate.get(Calendar.DATE));
        }

        return 0;
    }

    private int calculateFine1(List<Integer> expectedDate, List<Integer> actualDate) {

        if (expectedDate.get(2) < actualDate.get(2)) {
            return 10000;
        } else if (expectedDate.get(2).equals(actualDate.get(2)) && expectedDate.get(1) < actualDate.get(1)) {
            return 500 * (actualDate.get(1) - expectedDate.get(1));
        } else if (expectedDate.get(2).equals(actualDate.get(2)) && expectedDate.get(1).equals(actualDate.get(1)) && expectedDate.get(0) < actualDate.get(0)) {
            return 15 * (actualDate.get(0) - expectedDate.get(0));
        }

        return 0;
    }

    @Test
    void calculateFineTest() {

        int[] actualDateNumbers = new int[]{9, 6, 2015};
        int[] expectedDateNumbers = new int[]{6, 6, 2015};

        Calendar actualDate = Calendar.getInstance();
        actualDate.set(actualDateNumbers[2], actualDateNumbers[1], actualDateNumbers[0]);
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(expectedDateNumbers[2], expectedDateNumbers[1], expectedDateNumbers[0]);

        assertEquals(45, calculateFine(expectedDate, actualDate));

        actualDateNumbers = new int[]{10, 4, 2015};
        expectedDateNumbers = new int[]{6, 6, 2015};

        actualDate = Calendar.getInstance();
        actualDate.set(actualDateNumbers[2], actualDateNumbers[1], actualDateNumbers[0]);
        expectedDate = Calendar.getInstance();
        expectedDate.set(expectedDateNumbers[2], expectedDateNumbers[1], expectedDateNumbers[0]);

        assertEquals(0, calculateFine(expectedDate, actualDate));


        actualDateNumbers = new int[]{10, 4, 2014};
        expectedDateNumbers = new int[]{6, 6, 2015};

        actualDate = Calendar.getInstance();
        actualDate.set(actualDateNumbers[2], actualDateNumbers[1], actualDateNumbers[0]);
        expectedDate = Calendar.getInstance();
        expectedDate.set(expectedDateNumbers[2], expectedDateNumbers[1], expectedDateNumbers[0]);

        assertEquals(0, calculateFine(expectedDate, actualDate));
    }

    @Test
    void calculateFine2Test() {

        String[] arrActual = new String[]{"9", "6", "2015"};
        String[] arrExpect = new String[]{"6", "6", "2015"};

        List<Integer> actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(45, calculateFine1(expected, actual));

        arrActual = new String[]{"9", "6", "2015"};
        arrExpect = new String[]{"6", "4", "2015"};

        actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(1000, calculateFine1(expected, actual));

        arrActual = new String[]{"9", "6", "2015"};
        arrExpect = new String[]{"6", "4", "2014"};

        actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(10000, calculateFine1(expected, actual));

        arrActual = new String[]{"9", "6", "2015"};
        arrExpect = new String[]{"9", "6", "2015"};

        actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(0, calculateFine1(expected, actual));

        arrActual = new String[]{"10", "5", "2015"};
        arrExpect = new String[]{"9", "6", "2015"};

        actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(0, calculateFine1(expected, actual));

        arrActual = new String[]{"8", "7", "2014"};
        arrExpect = new String[]{"9", "6", "2015"};

        actual = Stream.of(arrActual).map(Integer::valueOf).collect(Collectors.toList());
        expected = Stream.of(arrExpect).map(Integer::valueOf).collect(Collectors.toList());

        assertEquals(0, calculateFine1(expected, actual));
    }

}
