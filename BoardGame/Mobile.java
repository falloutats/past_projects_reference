
/**
 * Mobile is a abstract class that implements both Boardable and Runnable.
 * 
 * @author Mayank
 *
 */

public abstract class Mobile implements Boardable, Runnable {
    /**
     * Instance for Board class.
     */
    protected Board board;

    /**
     * @param board the instace of Board class.
     */
    public Mobile(Board board) {
        this.board = board;
    }

    /**
     * Abstract move method.
     */
    protected abstract void move();
}
