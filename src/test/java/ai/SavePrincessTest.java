package ai;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class SavePrincessTest {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */


        PrincessMatrix princessMatrix = null;

        try {
            princessMatrix = generateGridFromScanner();
//            princessMatrix = generateGridFromIO();
        } catch (NumberFormatException e) {
            System.err.println("First set value must be a number from 3 to 99");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


        //immutable list!!!
        List<List<Character>> grid = princessMatrix != null ? princessMatrix.getMatrix() : null;

        if (grid != null && grid.size() >= 3 && grid.size() < 100) {
            grid.forEach(r -> {
                r.forEach(System.out::print);
                System.out.println();
            });

            System.out.println(princessMatrix.getBotPoint().toString());
            System.out.println(princessMatrix.getPrincessPoint().toString());
        } else {
            System.err.println("Grid has not size 3 <= N < 100");
        }
    }

    private static PrincessMatrix generateGridFromScanner() throws Exception {
        int dim;

        try (Scanner scan = new Scanner(System.in)) {

            dim = scan.nextInt();

            scan.nextLine();

            char[] rowChars;


            PrincessMatrix princessMatrix = new PrincessMatrix(dim);

            for (int i = 0; i < dim; i++) {
                rowChars = scan.next().toCharArray();

                princessMatrix.addMatrixRow(rowChars);

                scan.nextLine();


            }

            return princessMatrix;

        }

    }

    private static PrincessMatrix generateGridFromIO() throws Exception {
        int dim;

//        Console cnsl = System.console() je null


        byte[] dimBytes = System.in.readNBytes(1);
        dim = Integer.parseInt(new String(dimBytes, StandardCharsets.UTF_8));


        if (dim < 3 || dim >= 100) {
            throw new NumberFormatException();
        }
        //read a new line
        System.in.read();

        char[] rowChars;
        PrincessMatrix princessMatrix = new PrincessMatrix(dim);
        for (int i = 0; i < dim; i++) {
//            rowChars = new char[dim];
            rowChars = new String(System.in.readNBytes(dim), StandardCharsets.UTF_8).toCharArray();

            //read a new line
            clearInputBuffer();

            princessMatrix.addMatrixRow(rowChars);

        }

        return princessMatrix;
    }

    private static void clearInputBuffer() throws IOException {
        while (((char) System.in.read()) != '\n') {
//clearing rest of buffereeed inputs
        }
    }

    private static class PrincessMatrix {

        private Point botPoint, princessPoint;
        private final int matrixDimension;
        private final List<List<Character>> matrix;

        PrincessMatrix(int matrixDimension) {
            this.matrixDimension = matrixDimension;
            matrix = new ArrayList<>(matrixDimension);
        }

        void addMatrixRow(char[] input) throws Exception {
            parseSystemInput(input);
        }

        private void parseSystemInput(char[] input) throws Exception {
            if (input.length < matrixDimension) {
                throw new Exception("You have set less columns then " + matrixDimension);
            }
            char nextChar;
            List<Character> matrixRow = new ArrayList<>(matrixDimension);

            for (int j = 0; j < matrixDimension; j++) {

                //System.lineSeparator()
                nextChar = input[j];
                switch (nextChar) {
                    case 'm':
                        if (botPoint != null) {
                            throw new Exception("Bot is already set!");
                        }

                        botPoint = new Point(matrix.size(), matrixRow.size());
                        matrixRow.add(nextChar);
                        break;
                    case 'p':
                        if (princessPoint != null) {
                            throw new Exception("Princess is already set!");
                        }

                        princessPoint = new Point(matrix.size(), matrixRow.size());
                        matrixRow.add(nextChar);
                        break;
                    case '-':
                        matrixRow.add(nextChar);
                        break;
                    case '\n':
                    case '\r':
                    case ' ':
                        break;

                    default:
                        throw new Exception("Wrong char! You can set only '-', one time 'm' and 'p'!");
                }

            }


            if (matrixRow.size() != matrixDimension) {
                throw new Exception(String.format("Matrix Row %d has size %d but it should have a size %d", matrix.size() + 1, matrixRow.size(), matrixDimension));
            }

            matrix.add(Collections.unmodifiableList(matrixRow));
        }

        public Point getBotPoint() {
            return botPoint;
        }

        public Point getPrincessPoint() {
            return princessPoint;
        }

        public List<List<Character>> getMatrix() {
            return Collections.unmodifiableList(matrix);
        }
    }
}
