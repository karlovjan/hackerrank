package ai;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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


        //nejkratsi cesta
        //the shortest distance to the princess
        if (princessMatrix != null) {
            findShortestWay_myAlgorithm(princessMatrix).forEach(System.out::println);
        }
    }

    @Test
    void trappThePrincessTest_BotAndPrincessOntheSamePoint() throws Exception {

        assertEquals(0, findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(0, 0), new Point(0, 0))).size());
        assertEquals(0, findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(2, 2), new Point(2, 2))).size());
    }

    @Test
    void trappThePrincessTest_BotAndPrincessOnX() throws Exception {

        assertIterableEquals(List.of(Movements.LEFT, Movements.LEFT), findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(2, 2), new Point(2, 0))));
        assertIterableEquals(List.of(Movements.RIGHT, Movements.RIGHT), findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(0, 0), new Point(0, 2))));
    }

    @Test
    void trappThePrincessTest_BotAndPrincessOnY() throws Exception {

        assertIterableEquals(List.of(Movements.DOWN, Movements.DOWN), findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(0, 0), new Point(2, 0))));
        assertIterableEquals(List.of(Movements.UP, Movements.UP), findShortestWay_myAlgorithm(new PrincessMatrix(3, new Point(2, 2), new Point(0, 2))));
    }

    @Test
    void trappThePrincessTest() throws Exception {

        PrincessMatrix princessMatrix = new PrincessMatrix(3);
        princessMatrix.addMatrixRow(new char[]{'-', '-', '-'});
        princessMatrix.addMatrixRow(new char[]{'-', 'm', '-'});
        princessMatrix.addMatrixRow(new char[]{'p', '-', '-'});

        assertIterableEquals(List.of(Movements.LEFT, Movements.DOWN), findShortestWay_myAlgorithm(princessMatrix));
    }

    @Test
    void trappThePrincessTest_dim4() throws Exception {

        PrincessMatrix princessMatrix = new PrincessMatrix(4);
        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-'});
        princessMatrix.addMatrixRow(new char[]{'m', '-', '-', '-'});
        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', 'p'});
        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-'});

        assertIterableEquals(List.of(Movements.RIGHT, Movements.RIGHT, Movements.RIGHT, Movements.DOWN), findShortestWay_myAlgorithm(princessMatrix));
    }

    private enum Movements {
        LEFT, RIGHT, UP, DOWN
    }

    private static List<Movements> findShortestWay_myAlgorithm(PrincessMatrix princessMatrix) {


        //make sub-matrix - one vertex is bot and secont is the princess
        //start and end indexies for bot and princess in the grid
        int startPointX = princessMatrix.getBotPoint().x;
        int startPointY = princessMatrix.getBotPoint().y;
        int endPointX = princessMatrix.getPrincessPoint().x;
        int endPointY = princessMatrix.getPrincessPoint().y;

        int distanceX = endPointX - startPointX;
        int distanceY = endPointY - startPointY;

        //is princess on the same palce as a bot?
        if (distanceX == 0 && distanceY == 0) {
            return new ArrayList<>(); //return an empty movement list
        }

        //is princess on the same y-axis on the same vertical axis, on the same vertical line
        if (distanceY == 0) {
            //is the princess under the bot?
            if (distanceX > 0) {
                return move(startPointX, endPointX, Movements.DOWN);
            } else {
                //the princess is above the bot
                return move(endPointX, startPointX, Movements.UP);
            }
        }

        //is princess on the same x-axis, on the same horizontal axis, on the same horizontal line
        if (distanceX == 0) {
            //is the princess on the right side from the bot?
            if (distanceY > 0) {
                return move(startPointY, endPointY, Movements.RIGHT);
            } else {
                //the princess is on the left side from the bot
                return move(endPointY, startPointY, Movements.LEFT);
            }
        }

        //movements to the princess is in two directions, in x and y axis
        //there are two possible ways how to get to the princess
        //whenn the bot starts in X axis or Y axis
        //I alwasy starts on X axis
        List<Movements> movementsX;
        List<Movements> movementsY;

        //is the princess on the right side from the bot?
        if (distanceY > 0) {
            movementsX = move(startPointY, endPointY, Movements.RIGHT);
        } else {
            //the princess is on the left side from the bot
            movementsX = move(endPointY, startPointY, Movements.LEFT);
        }
        //is the princess under the bot?
        if (distanceX > 0) {
            movementsY = move(startPointX, endPointX, Movements.DOWN);
        } else {
            //the princess is above the bot
            movementsY = move(endPointX, startPointX, Movements.UP);
        }

        List<Movements> movementsToThePprincess = new ArrayList<>(movementsX.size() + movementsY.size());
        movementsToThePprincess.addAll(movementsX);
        movementsToThePprincess.addAll(movementsY);

        return movementsToThePprincess;
    }

    private static List<Movements> move(int startPoint, int endPoint, Movements movements) {
        List<Movements> movementsToThePrincess = new ArrayList<>();
        for (int i = startPoint; i < endPoint; i++) {
            movementsToThePrincess.add(movements);
        }

        return movementsToThePrincess;
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

        public PrincessMatrix(int matrixDimension, Point bot, Point princess) throws Exception {
            this.matrixDimension = matrixDimension;
            this.botPoint = bot;
            this.princessPoint = princess;
            matrix = new ArrayList<>(matrixDimension);
            List<Character> charsRow;
            //rows
            for (int i = 0; i < matrixDimension; i++) {
                charsRow = new ArrayList<>(matrixDimension);
                //columns
                for (int j = 0; j < matrixDimension; j++) {
                    if (i == botPoint.x && j == botPoint.y) {
                        //add the bot
                        charsRow.add('m');
                    } else if (i == princessPoint.x && j == princessPoint.y) {
                        //add the princess
                        charsRow.add('p');
                    } else {
                        charsRow.add('-');
                    }
                }
                this.matrix.add(Collections.unmodifiableList(charsRow));
            }


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
