package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ScannerIOTest {

    //	https://www.hackerrank.com/challenges/30-hello-world/tutorial

    public static void main(String[] args) {

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
}
