import java.io.IOException;
import java.util.Scanner;

/**
 * Player class takes the input and extends mobile. Has methods that make move
 * happen and run the game until condition achieved.
 * 
 * @author Mayank
 *
 */
public class Player extends Mobile implements Boardable {

    /**
     * Instance for board class.
     */
    private Board board;
    /**
     * Instance for scanner input.
     */
    private Scanner input;
    /**
     * Instance for time delay.
     */
    private long delayTime = 0L;

    /**
     * Player constructor.
     * 
     * @param board instance.
     */
    public Player(Board board) {
        super(board);
        this.board = board;
        input = new Scanner(System.in);
        // Place player at (0,0).
        this.board.placeElement(this, 0, 0);
    }

    /**
     * Move method has switch case which defines move.
     */
    protected void move() {

        // Call delay first.
        delay();
        // Printing board.
        board.printBoard();
        System.out.println("Enter a move direction: ");

        // for interfacing with the user
        String line = input.nextLine();
        if (line.length() != 1) {
            return;
        }
        char move = line.charAt(0);
        switch (move) {
        case 'w':
            board.move(Direction.UP, this);
            break;
        case 'x':
            board.move(Direction.DOWN, this);
            break;
        case 'a':
            board.move(Direction.LEFT, this);
            break;
        case 'q':
            board.move(Direction.UP_LEFT, this);
            break;
        case 'z':
            board.move(Direction.DOWN_LEFT, this);
            break;
        case 'e':
            board.move(Direction.UP_RIGHT, this);
            break;
        case 'c':
            board.move(Direction.DOWN_RIGHT, this);
            break;
        case 'd':
            board.move(Direction.RIGHT, this);
            break;
        default:
            break;
        }

        // Printing board.
        // board.printBoard();
    }

    /**
     * Calls move while(!hugged).
     */
    public void run() {
        // call move until Jarvis has been hugged.
        while (!board.beenHugged())

            move();
    }

    /**
     * Method makes the player visible on board.
     * 
     * @return always true.
     */
    public boolean isVisible() {
        return true;
    }

    /**
     * toString method returns user elem symbol.
     */
    @Override
    public String toString() {
        return "*";
    }

    /**
     * The setDelay should set delayTime.
     * 
     * @param time
     */
    public void setDelay(long time) {
        this.delayTime = time;
    }

    /**
     * When trap is sprung, stop the user from doing anything.
     */
    private void delay() {
        try {
            Thread.sleep(delayTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            while (System.in.available() > 0) {
                int buffer = System.in.available();
                byte x[] = new byte[buffer];
                System.in.read(x);
            }
        } catch (IOException e) {

        }

        // Last thing -- Delay to zero.
        setDelay(0L);
    }

    /**
     * If player first elem in cell it cannot share spot, should always return
     * false.
     * 
     * @return always false.
     */
    @Override
    public boolean share(Boardable elem) {

        return false;
    }

}
