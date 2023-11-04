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
    public static List<int[][]> solutions = new ArrayList<>();

    public static void dfs(int[][] puzzle, int u) {
        if (u == emptyCells.size()) {
            valid = true;

            int[][] solution = new int[GRID_BOUNDARY][GRID_BOUNDARY];
            for (int i = 0; i < GRID_BOUNDARY; i++) {
                for (int j = 0; j < GRID_BOUNDARY; j++) {
                    solution[i][j] = puzzle[i][j];
                }
            }
            solutions.add(solution);
            return;
        }

        Coordinates coordinates = emptyCells.get(u);
        int i = coordinates.getX();
        int j = coordinates.getY();
        for (int k = 0; k < GRID_BOUNDARY && !valid; k++) {
            if (!row[i][k] && !col[j][k] && !cell[i / 3 * 3 + j / 3][k]) {
                row[i][k] = col[j][k] = cell[i / 3 * 3 + j / 3][k] = true;
                puzzle[i][j] =  k + 1;
                dfs(puzzle, u + 1);
                row[i][k] = col[j][k] = cell[i / 3 * 3 + j / 3][k] = false;
            }
        }
    }

    public static boolean puzzleIsSolvable(int[][] puzzle) {
        for (int i = 0; i < GRID_BOUNDARY; i++) {
            Arrays.fill(row[i], false);
            Arrays.fill(col[i], false);
            Arrays.fill(cell[i], false);
        }
        for (int i = 0; i < GRID_BOUNDARY; i++) {
            for (int j = 0; j < GRID_BOUNDARY; j++) {
                if (puzzle[i][j] == 0) {
                    emptyCells.add(new Coordinates(i, j));
                } else {
                    int digit = puzzle[i][j]-1;
                    row[i][digit] = col[j][digit] = cell[i / 3 * 3 + j / 3][digit] = true;
                }
            }
        }
        dfs(puzzle, 0);
        return valid;
    }


    public static void printPuzzle(int[][] puzzle) {
        for (int i = 0; i < GRID_BOUNDARY; i++) {
            for (int j = 0; j < GRID_BOUNDARY; j++) {
                System.out.print(puzzle[i][j] + " ");
            }
            System.out.println();
        }
    }


//    public static void main(String[] args) {
//
//        int[][] puzzle = {
//            {5, 3, 0, 0, 7, 0, 0, 0, 0},
//            {6, 0, 0, 1, 9, 5, 0, 0, 0},
//            {0, 9, 8, 0, 0, 0, 0, 6, 0},
//            {8, 0, 0, 0, 6, 0, 0, 0, 3},
//            {4, 0, 0, 8, 0, 3, 0, 0, 1},
//            {7, 0, 0, 0, 2, 0, 0, 0, 6},
//            {0, 6, 0, 0, 0, 0, 2, 8, 0},
//            {0, 0, 0, 4, 1, 9, 0, 0, 5},
//            {0, 0, 0, 0, 8, 0, 0, 7, 9}
//        };
//
//        boolean solvable = puzzleIsSolvable(puzzle);
//        if (solvable) {
//            System.out.println("数独有解，解的个数为" + solutions.size());
//            for (int i = 0; i < solutions.size(); i++) {
//                System.out.println((i + 1) + "个解");
//                printPuzzle(solutions.get(i));
//            }
//        } else {
//            System.out.println("数独无解");
//        }
//    }
}
