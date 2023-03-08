# README

This is a demo for a board game implemented in Java. It contains a `Board` class that represents the game board and a `Boardable` interface that represents the game pieces. The `Board` class has a nested `Cel` class to represent each cell on the board.

The `Board` class has the following methods:

`Board(int height, int width)`: a constructor that initializes a new board with the given height and width.
`move(Direction dir, Boardable elem)`: a method that moves a Boardable element in the given direction on the board.
`removeElement(Boardable elem)`: a method that removes a Boardable element from the board.
`getRow(Boardable elem)`: a method that returns the row index of a Boardable element on the board.
`getColumn(Boardable elem)`: a method that returns the column index of a Boardable element on the board.
`setHugged(boolean hugged)`: a method that sets the hugged instance variable to the given boolean value.
`beenHugged()`: a method that returns whether or not the hugged instance variable has been set to true.
`placeElement(Boardable elem, int row, int col)`: a method that places a Boardable element on the board at the given row and column indices.
`printBoard()`: a method that prints the current state of the board.

The `Boardable` interface has the following methods:

`char getSymbol()`: a method that returns the symbol of the Boardable element.
`void setSymbol(char symbol)`: a method that sets the symbol of the Boardable element.
`void setBoard(Board board)`: a method that sets the board for the Boardable element.
`void setRow(int row)`: a method that sets the row index of the Boardable element on the board.
`void setColumn(int col)`: a method that sets the column index of the Boardable element on the board.
`void move(Direction direction)`: a method that moves the Boardable element in the given direction on the board.

The `Direction` enum represents the eight possible directions that a `Boardable` element can move on the board.


This demo also includes a `Main` class that sets up a sample game on the board and moves the elements around to demonstrate the functionality of the `Board` and `Boardable` classes.

Note: This demo is for illustrative purposes only and is not a complete or production-ready implementation of a board game. It is intended to demonstrate basic concepts and practices in Java programming.
