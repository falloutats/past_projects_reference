import java.util.ArrayList;
import java.util.HashMap;

/**
 * The board class has a nested class named Cell. Other than that, it has
 * methods to set the board with its limits and possible actions.
 *
 * @author Mayank
 *
 */
public class Board {

    /**
     * Instance variable for 2D array.
     */
    private Cell[][] board;

    /**
     * Instance variable for height of the board.
     */
    private int height;

    /**
     * Instance variable for width of the board.
     */
    private int width;
    /**
     * Instance variable for state hugged.
     */
    private boolean hugged = false;

    /**
     * An instance variable of type Boardable and Cell class.
     */
    private HashMap<Boardable, Cell> elementPlace;

    /**
     * Constructor of class Board.
     *
     * @param height of the board for index.
     * @param width  of the board for index.
     */
    public Board(int height, int width) {
        if (height <= 0 || height > 100 || width <= 0 || width > 100) {
            throw new IllegalArgumentException("Value not in range");
        }
        this.height = height;
        this.width = width;
        // Board of type cell 2D array.
        board = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = new Cell(i, j);
            }
        }

        elementPlace = new HashMap<Boardable, Cell>();
    }

    /**
     * Move method defines all the possible moves.
     *
     * @param dir  enum directoin.
     * @param elem represents a boardable object.
     * @return boolean value (Elem placed or not).
     */
    public synchronized boolean move(Direction dir, Boardable elem) {
        Cell cell = elementPlace.get(elem);
        if (cell == null) {
            throw new IllegalArgumentException("Not on board.");
        }
        // referencing to cell (col & row).
        int x = cell.col;
        int y = cell.row;
        // Switch case defining directions.
        switch (dir) {
        case UP:
            y = y - 1;
            break;
        case DOWN:
            y = y + 1;
            break;
        case RIGHT:
            x = x + 1;
            break;
        case LEFT:
            x = x - 1;
            break;
        case UP_LEFT:
            x = x - 1;
            y = y - 1;
            break;
        case UP_RIGHT:
            x = x + 1;
            y = y - 1;
            break;
        case DOWN_LEFT:
            x = x - 1;
            y = y + 1;
            break;
        case DOWN_RIGHT:
            x = x + 1;
            y = y + 1;
            break;
        }
        // Check if the new cell is valid
        if (y >= 0 && y < height && x >= 0 && x < width) {
            // Remove pen from prev elem.
            board[cell.row][cell.col].removeElement(elem);
            // Add elem back to the new X Y position.
            placeElement(elem, y, x);
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param elem represents a boardable object.
     * @return if not null true else false.
     */
    public synchronized boolean removeElement(Boardable elem) {
        if (elementPlace.remove(elem) != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method is a getter for Row.
     * 
     * @param elem represents a boardable object.
     * @return value of y.
     */
    public int getRow(Boardable elem) {
        Cell cell = elementPlace.get(elem);
        if (cell == null)
            throw new IllegalArgumentException("Element not found");
        int y = cell.row;
        return y;
    }

    /**
     * Method is a getter for Column.
     * 
     * @param elem represents a boardable object.
     * @return value of x.
     */
    public int getColumn(Boardable elem) {
        Cell cell = elementPlace.get(elem);
        if (cell == null)
            throw new IllegalArgumentException("Element not found");
        int x = cell.col;
        return x;
    }

    /**
     * Method sets the value of hugged.
     * 
     * @param hugged is condition hugged or not.
     */
    public void setHugged(boolean hugged) {
        this.hugged = hugged;
    }

    /**
     * Method returns true or false.
     * 
     * @return boolean value hugged or not.
     */
    public boolean beenHugged() {
        return hugged;
    }

    /**
     * Element placed onto the board, within a hasmap.
     *
     * @param elem represents a boardable object.
     * @param row  index.
     * @param col  index.
     * @return boolean if valid & elem placed.
     */
    public synchronized boolean placeElement(Boardable elem, int row, int col) {
        if (row >= 0 && row < height && col >= 0 && col < width) {
            elementPlace.put(elem, board[row][col]);
            board[row][col].addElement(elem);
            return true;
        } else {
            return false;

        }
    }

    /**
     * Method prints the Board.
     */
    public synchronized void printBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Cell class has elements that have methods to add and remove items, other than
     * that it has parameters to set the cells within the board. And a final
     * toStrinmg method to return pound or space(isVisible).
     *
     * @author Mayank
     *
     */
    private class Cell {

        /**
         * Instance variable for row index.
         */
        private int row;

        /**
         * Instance variable for col index.
         */
        private int col;

        /**
         * Instance variable for isVisible element.
         */
        private boolean isVisible;

        /**
         * ArrayList for bordable elements.
         */
        private ArrayList<Boardable> elements;

        /**
         * The cell is a constructor that has two parameters and has a new instance of
         * ArrayList.
         *
         * @param row is an index of the grid.
         * @param col is an index of the grid.
         */
        public Cell(int row, int col) {
            this.row = row;
            this.col = col;
            elements = new ArrayList<Boardable>();
        }

        /**
         * Add the elem to the board method.
         * 
         * @param elem represents a boardable object.
         */
        public void addElement(Boardable elem) {

            // If a Cell already contains an element A
            if (elements.size() > 0) {
                // with the argument B
                for (Boardable existing : elements) {

                    // then it should call A --- share(B)
                    if (existing.share(elem)) {

                        // A Cell becomes visible if any element is added to it.
                        if (elem.isVisible()) {
                            isVisible = true;
                        }
                        elements.add(elem);
                        break;
                    }
                }
            } else {
                elements.add(elem);

                // A Cell becomes visible if any element is added to it that is visible
                if (elem.isVisible()) {
                    isVisible = true;
                }
            }
        }

        /**
         * Method returns false -- element removed.
         *
         * @param elem represents a boardable object.
         * @return boolean value not true.
         */
        public boolean removeElement(Boardable elem) {
            return !elements.remove(elem);
        }

        /**
         * toString method to return boardable elements.
         *
         * @return pound or space based on move type.
         */
        @Override
        public String toString() {

            if (!isVisible) {
                return "#";
            } else if (elements.size() == 0) {
                return " ";
            } else {
                // toString of the last element that was added to it
                return elements.get(elements.size() - 1).toString();
            }
        }
    }
}
