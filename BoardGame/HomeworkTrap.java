
/**
 * Homework Trap has methods that make the trap !Visible and check for other
 * conditions.
 * 
 * @author Mayank
 *
 */
public class HomeworkTrap implements Boardable {
    /**
     * Instance of class Board.
     */
    private Board board;

    /**
     * Constructor for home work trap.
     * 
     * @param board instance.
     */
    public HomeworkTrap(Board board) {
        this.board = board;
    }

    /**
     * @param elem represents a boardable object.
     */
    public boolean share(Boardable elem) {
        // It can share its spot with any Mobile
        if (elem instanceof Mobile) {
            // If it shares its location with a Player
            if (elem instanceof Player) {
                // the trap should set the delay time for the player to 5000
                ((Player) elem).setDelay(5000);

                // and then remove itself from the board
                board.removeElement(this);

                // It should also print something explaining what has happened
                System.out.println("A trap has been sprung");
            }
            return true;
        }
        return false;
    }

    /**
     * Method makes homework trap !visible.
     * 
     * @return always false.
     */
    @Override
    public boolean isVisible() {
        // The traps are not visible
        return false;
    }

    /**
     * @return space " " as homework trap.
     */
    @Override
    public String toString() {
        return " ";
    }

}
