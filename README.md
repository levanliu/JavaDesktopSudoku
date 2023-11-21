### Java sudoku 

Java GUI exercise

At the core of this project is the Sudoku solver which uses the DFS algorithm to find all the solutions to Sudoku.

It generates some random positions and numbers, as initializing the Sodoku table. Then uses a solver to solve it and randomly sets 40 grids to zero.

As a whole, the project uses MVC architecture. The persistence layer is a txt file for persistent storage of Sudoku state.
The View layer uses JavaFX to draw and teh controller layer is used to process input logic.
When the form is filled, the detector checks whether the current form is valid.
