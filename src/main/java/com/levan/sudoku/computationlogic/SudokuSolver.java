package com.levan.sudoku.computationlogic;

import java.util.Arrays;
import java.util.List;

import com.levan.sudoku.problemdomain.Coordinates;

import static com.levan.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;

import java.util.ArrayList;


public class SudokuSolver {

    public static boolean row[][] = new boolean[GRID_BOUNDARY][GRID_BOUNDARY];
    public static boolean col[][] = new boolean[GRID_BOUNDARY][GRID_BOUNDARY];
    public static boolean cell[][] = new boolean[GRID_BOUNDARY][GRID_BOUNDARY];
    public static List<Coordinates> emptyCells = new ArrayList<>();

    public static boolean valid = false;

    public static void dfs(int[][] puzzle, int u) {
        if (u == emptyCells.size()) {
            valid = true;
            return;
        }

        Coordinates coordinates = emptyCells.get(u);
        for (int i = 0; i < GRID_BOUNDARY && !valid; i++) {
            int xIndex = coordinates.getX();
            int yIndex = coordinates.getY();
            if (row[xIndex][i] != false || col[yIndex][i] != false
                    || cell[xIndex / 3 * 3 + yIndex / 3][i] != false) {
                row[xIndex][i] = col[yIndex][i] = cell[xIndex / 3 * 3 + yIndex / 3][i] = true;
                puzzle[xIndex][yIndex] = i + 1;
                dfs(puzzle, u + 1);
                row[xIndex][i] = col[yIndex][i] = cell[xIndex / 3 * 3 + yIndex / 3][i] = false;
            }
        }
    }

    public static boolean puzzleIsSolvable(int[][] puzzle) {
        for (int i = 0; i < cell.length; i++) {
            Arrays.fill(row[i], false);
            Arrays.fill(col[i], false);
            Arrays.fill(cell[i], false);
        }
        for (int i = 0; i < GRID_BOUNDARY; i++) {
            for (int j = 0; j < GRID_BOUNDARY; j++) {
                if (puzzle[i][j] == 0) {
                    emptyCells.add(new Coordinates(i, j));
                } else {
                    int digit = puzzle[i][j];
                    row[i][digit] = col[j][digit] = cell[i / 3 * 3 + j / 3][i] = true;
                }
            }
        }
        dfs(puzzle, 0);
        return valid;
    }
}
