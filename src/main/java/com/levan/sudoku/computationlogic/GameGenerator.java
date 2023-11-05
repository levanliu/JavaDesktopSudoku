package com.levan.sudoku.computationlogic;

import static com.levan.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.levan.sudoku.computationlogic.GameLogic;
import com.levan.sudoku.problemdomain.Coordinates;

class GameGenerator {
    public static int[][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    /**
     * 1. Generate a new 9x9 2D Array. 2. For each value in the range 1..9, allocate
     * that value 9
     * times based on the following constraints: - A Random coordinate on the grid
     * is selected. If
     * it is empty, a Random value is allocated. - The resulting allocation must not
     * produce invalid
     * rows, columns, or squares. - If the allocation does produce an invalid game
     *
     * @return
     */
    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_BOUNDARY][GRID_BOUNDARY];
        int allocations = 0;
        while (allocations < GRID_BOUNDARY) {
            int xCoordinate = random.nextInt(GRID_BOUNDARY);
            int yCoordinate = random.nextInt(GRID_BOUNDARY);

            if (newGrid[xCoordinate][yCoordinate] == 0) {
                newGrid[xCoordinate][yCoordinate] = random.nextInt(GRID_BOUNDARY);
                if (GameLogic.isValidSudo(newGrid)) {
                    allocations++;
                } else {
                    newGrid[xCoordinate][yCoordinate] = 0;
                }
            }
        }

        if (SudokuSolver.puzzleIsSolvable(newGrid)) {
            SudokuSolver.puzzleSolve(newGrid);
            return newGrid;
        }
        return getSolvedGame();
    }

    /**
     * The purpose of this function is to take a game which has already been solved
     * (and thus proven
     * to be solvable), and randomly assign a certain number of tiles to be equal to
     * 0. It appears
     * that there is no straight forward way to check if a puzzle is still solvable
     * after removing
     * the tiles, beyond using another algorithm to attempt to re-solve the problem.
     * <p>
     * 1. Copy values of solvedGame to a new Array (make into a helper) 2. Remove 40
     * Values randomly
     * from the new Array. 3. Test the new Array for solvablility. 4a. Solveable ->
     * return new Array
     * 4b. return to step 1
     *
     * @param solvedGame
     * @return
     */
    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());

        boolean solvable = false;

        // note: not actually solvable until the algorithm below finishes!
        int[][] solvableArray = new int[GRID_BOUNDARY][GRID_BOUNDARY];

        while (solvable == false) {

            // Take values from solvedGame and write to new unsolved; i.e. reset to initial
            // state
            SudokuUtilities.copySudokuArrayValues(solvedGame, solvableArray);

            // remove 40 random numbers
            int index = 0;
            while (index < 40) {
                int xCoordinate = random.nextInt(GRID_BOUNDARY);
                int yCoordinate = random.nextInt(GRID_BOUNDARY);

                if (solvableArray[xCoordinate][yCoordinate] != 0) {
                    solvableArray[xCoordinate][yCoordinate] = 0;
                    index++;
                }
            }

            int[][] toBeSolved = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            SudokuUtilities.copySudokuArrayValues(solvableArray, toBeSolved);
            // check if result is solvable
            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);

            System.out.println(solvable);
        }

        return solvableArray;
    }

    private static void clearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }

}
