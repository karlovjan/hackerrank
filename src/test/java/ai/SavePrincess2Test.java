package ai;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

/**
 * Princess Peach is trapped in one of the four corners of a square grid.
 * You are in the center of the grid and can move one step at a time in any of the four directions.
 * Can you rescue the princess?
 */
class SavePrincess2Test {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

/*
The first line contains an odd integer N (3 <= N < 100) denoting the size of the grid.
This is followed by an NxN grid. Each cell is denoted by '-' (ascii value: 45).
The bot position is denoted by 'm' and the princess position is denoted by 'p'.
 */
        int dim;
        char[][] grid;

        try (Scanner scan = new Scanner(System.in)) {

            dim = scan.nextInt();

            if(dim < 3 || dim >= 100){
                System.err.println("The grid dimension is not a number from 3 to 99!!!");
                return;
            }
            //dim must be an odd integer
            if(dim % 2 == 0){
                System.err.println("The grid dimension is not an odd number!!!");
                return;
            }

            grid = new char[dim][dim];

            scan.nextLine();

            for (int i = 0; i < dim; i++) {

                grid[i] = scan.next().toCharArray();

                //skip last new line
                if(i < dim -1){
                    scan.nextLine();
                }

            }

        }



        //nejkratsi cesta
        //the shortest distance to the princess
        displayPathtoPrincess(dim, grid);
    }

    /**
     * Print out the moves you will take to rescue the princess in one go. The moves must be separated by '\n', a newline. The valid moves are LEFT or RIGHT or UP or DOWN.
     * @param N an odd integer N (3 <= N < 100) denoting the size of the grid.
     * @param grid an NxN grid.
     */
    static void displayPathtoPrincess(int N, char[][] grid) {

        Point botPoint = getBotPoint(N);

        if(grid[botPoint.x][botPoint.y] != 'm'){
            System.err.println("The bot is not in the center of the grid!!!");
            return;
        }

        Point princessPoint = findPrincessPoint(grid);

        if(princessPoint == null){
            System.err.println("Princess is not in one of four corners!!!");
            return;
        }

        List<Movements> movements = findPath(botPoint, princessPoint);


        movements.forEach(System.out::println);

    }

    private static List<Movements> findPath(Point startPoint, Point finalPoint) {
        List<Movements> movements = new ArrayList<>();
        int distanceX = finalPoint.x - startPoint.x;
        int distanceY = finalPoint.y - startPoint.y;


        int maxMovements = Math.abs(distanceX) + Math.abs(distanceY);

        //look at two next bot vertex of the grid
        //is the princess under the bot?
        Point nextPointInX;
        //is the princess on the right side from the bot?
        Point nextPointInY;
        Point startPointTmp = startPoint;

        Movements nextMovementX;
        Movements nextMovementY;

        double distance1;
        double distance2;

        for (int i = 0; i < maxMovements; i++) {

            //is the princess on the right side from the bot?
            if (distanceY > 0) {
                nextMovementX = Movements.RIGHT;
                nextPointInX = new Point(startPointTmp.x, startPointTmp.y + 1);
            } else {
                //the princess is on the left side from the bot
                nextMovementX = Movements.LEFT;
                nextPointInX = new Point(startPointTmp.x, startPointTmp.y - 1);
            }
            //is the princess under the bot?
            if (distanceX > 0) {
                nextMovementY = Movements.DOWN;
                nextPointInY = new Point(startPointTmp.x + 1, startPointTmp.y);
            } else {
                //the princess is above the bot
                nextMovementY = Movements.UP;
                nextPointInY = new Point(startPointTmp.x - 1, startPointTmp.y);
            }

            if (distanceX == 0) {
                //finish
                movements.add(nextMovementX);
                break;
            }
            if (distanceY == 0) {
                //finish
                movements.add(nextMovementY);
                break;
            }

            distance1 = nextPointInX.distance(finalPoint);
            distance2 = nextPointInY.distance(finalPoint);


            if (distance1 < distance2) {
                movements.add(nextMovementX);
                startPointTmp = nextPointInX;
            } else {
                movements.add(nextMovementY);
                startPointTmp = nextPointInY;
            }

            distanceX = finalPoint.x - startPointTmp.x;
            distanceY = finalPoint.y - startPointTmp.y;
        }

        return movements;
    }

    /**
     * Princess Peach is trapped in one of the four corners of a square grid.
     * @param grid grid
     * @return Point of princess
     */
    private static Point findPrincessPoint(char[][] grid) {

        if(grid[0][0] == 'p'){
            return new Point(0, 0);
        }

        if(grid[0][grid.length - 1] == 'p'){
            return new Point(0, grid.length - 1);
        }

        if(grid[grid.length - 1][0] == 'p'){
            return new Point(grid.length - 1, 0);
        }

        if(grid[grid.length - 1][grid.length - 1] == 'p'){
            return new Point(grid.length - 1, grid.length - 1);
        }

        return null;
    }

    private static Point getBotPoint(int gridDimension) {
        int gridCenterIndex = (gridDimension - 1) / 2;
        return new Point(gridCenterIndex, gridCenterIndex);
    }

    @Test
    void trappThePrincessTest_dim3() {

//        princessMatrix.addMatrixRow(new char[]{'-', '-', '-'});
//        princessMatrix.addMatrixRow(new char[]{'-', 'm', '-'});
//        princessMatrix.addMatrixRow(new char[]{'p', '-', '-'});

        assertIterableEquals(List.of(Movements.DOWN, Movements.LEFT), findPath(new Point(1, 1), new Point(2, 0)));
    }

    @Test
    void trappThePrincessTest_dim4() {

//        PrincessMatrix princessMatrix = new PrincessMatrix(4);
//        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-', 'p'});
//        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-', '-'});
//        princessMatrix.addMatrixRow(new char[]{'-', '-', 'm', '-', '-'});
//        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-', '-'});
//        princessMatrix.addMatrixRow(new char[]{'-', '-', '-', '-', '-'});

        assertIterableEquals(List.of(Movements.UP, Movements.RIGHT, Movements.UP, Movements.RIGHT), findPath(new Point(2, 2), new Point(0, 4)));
    }

    @Test
    void trappThePrincessTest_dim3_2() {

        assertIterableEquals(List.of(Movements.UP, Movements.LEFT), findPath(new Point(1, 1), new Point(0, 0)));
    }

    @Test
    void trappThePrincessTest_dim3_3() {

        assertIterableEquals(List.of(Movements.UP, Movements.RIGHT), findPath(new Point(1, 1), new Point(0, 2)));
    }

    @Test
    void trappThePrincessTest_dim3_4() {

        assertIterableEquals(List.of(Movements.DOWN, Movements.RIGHT), findPath(new Point(1, 1), new Point(2, 2)));
    }

    private enum Movements {
        LEFT, RIGHT, UP, DOWN
    }

}
