package ai;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SavePrincessTest {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        List<List<String>> grid = null;
        try {
//            grid = generateGridFromScanner();
            grid = generateGridFromIO();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        if (grid != null && grid.size() >= 3 && grid.size() < 100) {
            grid.forEach(r -> {
                r.forEach(System.out::print);
                System.out.println();
            });
        } else {
            System.err.println("Grid has not size 3 <= N < 100");
        }
    }

    private static List<List<String>> generateGridFromScanner() throws Exception {
        int dim = 0;

        List<List<String>> grid = null;
        List<String> row;

        try (Scanner scan = new Scanner(System.in)) {

            dim = scan.nextInt();

            grid = new ArrayList<>(dim);

            scan.nextLine();

            Point bot = null;
            Point princess = null;

            char nextChar;
            char[] rowChars;

            for (int i = 0; i < dim; i++) {
                row = new ArrayList<>(dim);
                rowChars = scan.next().toCharArray();
                for (int j = 0; j < dim; j++) {

                    nextChar = rowChars[j];
                    switch (nextChar) {
                        case 'm':
                            if (bot != null) {
                                throw new Exception("Bot is already set!");
                            }

                            bot = new Point(i, j);
                            break;
                        case 'p':
                            if (princess != null) {
                                throw new Exception("Princess is already set!");
                            }

                            princess = new Point(i, j);
                            break;
                        case '-':
                            row.add(String.valueOf(nextChar));
                            break;
                        default:
                            j--;//stay at the same index

                            break;
                    }

                }

                scan.nextLine();

                if (row.size() != dim) {
                    throw new Exception(String.format("Row %d has not size %d of set grid row %d", i, row.size(), dim));
                }

                grid.add(row);

            }
        }

        return grid;
    }

    private static List<List<String>> generateGridFromIO() throws Exception {
        int dim = 0;

        List<List<String>> grid = null;
        List<String> row;

        byte[] dimBytes = System.in.readNBytes(1);
        dim = Integer.valueOf(new String(dimBytes, StandardCharsets.UTF_8));

        grid = new ArrayList<>(dim);

        //read a new line
//        System.in.read();
        System.in.skip(1);

        Point bot = null;
        Point princess = null;

        char nextChar;
        char[] rowChars;

        for (int i = 0; i < dim; i++) {
            row = new ArrayList<>(dim);
            //dim + 1 = escape new line char
            rowChars = new String(System.in.readNBytes(dim ), StandardCharsets.UTF_8).toCharArray();
            //read a new line
//            System.in.read();

            if(rowChars.length < dim){
                throw new Exception("You have set less columns then " + dim);
            }
            for (int j = 0; j < dim; j++) {

                nextChar = rowChars[j];
                switch (nextChar) {
                    case 'm':
                        if (bot != null) {
                            throw new Exception("Bot is already set!");
                        }

                        bot = new Point(i, j);
                        break;
                    case 'p':
                        if (princess != null) {
                            throw new Exception("Princess is already set!");
                        }

                        princess = new Point(i, j);
                        break;
                    case '-':
                        row.add(String.valueOf(nextChar));
                        break;
                    default:
                        throw new Exception("Wrong char! You can set only -, 1xm, 1xp!");
                }

            }


            if (row.size() != dim) {
                throw new Exception(String.format("Row %d has not size %d of set grid row %d", i, row.size(), dim));
            }

            grid.add(row);

        }

        return grid;
    }
}
