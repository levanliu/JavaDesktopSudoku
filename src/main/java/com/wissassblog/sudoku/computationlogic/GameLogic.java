package com.wissassblog.sudoku.computationlogic;

import com.wissassblog.sudoku.constants.GameState;
import com.wissassblog.sudoku.problemdomain.SudokuGame;


import static com.wissassblog.sudoku.problemdomain.SudokuGame.GRID_BOUNDARY;
import java.util.Arrays;


/**
 * Q: Why isn't this a class hidden behind an interface? A: It requires no external libraries, nor
 * do I ever plan to switch to using external libraries.
 */
public class GameLogic {

    public static SudokuGame getNewGame() {
        return new SudokuGame(GameState.NEW, GameGenerator.getNewGameGrid());
    }

    public static GameState checkForCompletion(int[][] grid) {
        if (tilesAreNotFilled(grid))
            return GameState.ACTIVE;
        if (!isValidSudo(grid))
            return GameState.ERROR;
        return GameState.COMPLETE;
    }

    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0)
                    return true;
            }
        }
        return false;
    }


    public static boolean isValidSudo(int[][] grid) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] cell = new boolean[9][9];

        for (int i = 0; i < GRID_BOUNDARY; i++) {
            Arrays.fill(row[i], false);
            Arrays.fill(col[i], false);
            Arrays.fill(cell[i], false);
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    continue;
                } else {
                    int digits = grid[i][j];
                    if (!row[i][digits] && !col[j][digits] && !cell[i / 3 * 3 + j / 3][digits]) {
                        return false;
                    }
                    row[i][digits] = col[j][digits] = cell[i / 3 * 3 + j / 3][digits] = true;
                }
            }
        }
        return true;
    }
}

