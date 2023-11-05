package com.levan.sudoku.buildlogic;

import java.io.IOException;

import com.levan.sudoku.computationlogic.GameLogic;
import com.levan.sudoku.persistence.LocalStorageImpl;
import com.levan.sudoku.problemdomain.IStorage;
import com.levan.sudoku.problemdomain.SudokuGame;
import com.levan.sudoku.userinterface.IUserInterfaceContract;
import com.levan.sudoku.userinterface.logic.ControlLogic;

public class SudokuBuildLogic {

    /**
     * This class takes in the uiImpl object which is tightly-coupled to the JavaFX
     * framework,
     * and binds that object to the various other objects necessary for the
     * application to function.
     */
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        // use txt to storage game.
        IStorage storage = new LocalStorageImpl();

        try {
            // will throw if no game data is found in local storage

            initialState = storage.getGameData();
            storage.updateGameData(initialState);
        } catch (IOException e) {

            initialState = GameLogic.getNewGame();
            // this method below will also throw an IOException
            // if we cannot update the game data. At this point
            // the application is considered unrecoverable
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);
        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
