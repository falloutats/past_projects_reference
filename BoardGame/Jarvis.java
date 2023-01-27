import java.util.Random;

/**
 * Jarvis has move and other methods to lay trap.
 * 
 * @author Mayank
 *
 */
public class Jarvis extends Mobile {
    /**
     * Counter for moves made.
     */
    private int moves = 0;

    /**
     * @param board the instace of Board class.
     */
    public Jarvis(Board board) {
        super(board);
    }

    /**
     * Method lays the trap.
     */
    private void layTrap() {

        boolean successful = false;
        int tries = 0;
        // Keep trying.
        while (!successful && tries < 8) {
            // Randomly pick one place.
            int direction = new Random().nextInt(8);

            int x = board.getColumn(this);
            int y = board.getRow(this);

            switch (direction) {
            case 0:
                y = y - 1;
                break;
            case 1:
                y = y + 1;
                break;
            case 2:
                x = x + 1;
                break;
            case 3:
                x = x - 1;
                break;
            case 4:
                x = x - 1;
                y = y - 1;
                break;
            case 5:
                x = x + 1;
                y = y - 1;
                break;
            case 6:
                x = x - 1;
                y = y + 1;
                break;
            case 7:
                x = x + 1;
                y = y + 1;
                break;
            }

            successful = board.placeElement(new HomeworkTrap(board), x, y);
            tries++;
        }
    }

    /**
     * Move method has Thread sleep and
     */
    protected void move() {
        // Every 500 mill(s).
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean successful = false;
        int tries = 0;

        // If he is unable to move in the chosen direction, he should choose the other
        // way.
        while (!successful && tries < 8) {
            // Jarvis should pick some random direction.
            int direction = new Random().nextInt(8);
            switch (direction) {
            case 0:
                successful = board.move(Direction.UP, this);
                break;
            case 1:
                successful = board.move(Direction.DOWN, this);
                break;
            case 2:
                successful = board.move(Direction.LEFT, this);
                break;
            case 3:
                successful = board.move(Direction.UP_LEFT, this);
                break;
            case 4:
                successful = board.move(Direction.DOWN_LEFT, this);
                break;
            case 5:
                successful = board.move(Direction.UP_RIGHT, this);
                break;
            case 6:
                successful = board.move(Direction.DOWN_RIGHT, this);
                break;
            case 7:
                successful = board.move(Direction.RIGHT, this);
                break;
            default:
                break;
            }
            tries++;
        }
        this.moves++;

        // Call after every 6 moves.
        if (this.moves % 6 == 0) {
            layTrap();
        }
    }

    /**
     * Method Calls move.
     */
    public void run() {
        move();
    }

    /**
     * Method checks for the instance.
     * 
     * @return boolean value.
     */
    public boolean share(Boardable elem) {
        // Jarvis can share his cell with anything of type Mobile
        if (elem instanceof Mobile) {

            // If he shares with a Player
            if (elem instanceof Player) {
                // gets a hug and game ends.
                this.board.setHugged(true);

                // End
                System.out.println("Thank you for soothing old Dr. Jarvis");
            }
            return true;
        }
        return false;
    }

    /**
     * Jarvis not visible.
     * 
     * @return always false;
     */
    @Override
    public boolean isVisible() {
        // Jarvis should not be visible
        return false;
    }

    /**
     * toString method that returns string for jarvis.
     * 
     * @return Jarvis symbol.
     */
    @Override
    public String toString() {
        return "?";
    }

}
