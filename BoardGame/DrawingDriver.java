import java.util.Scanner;

/**
 * Drawing Driver class has main method that runs the game.
 * 
 * @author Mayank
 */
public class DrawingDriver {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        boolean valid = false;

        int height = 0, width = 0;

        do {
            try {
                System.out.println("Enter Height here: ");
                height = Integer.parseInt(input.next());
                System.out.println("Enter Width here: ");
                width = Integer.parseInt(input.next());

                if (height <= 0 || height > 100 || width <= 0 || width > 100) {
                    throw new Exception();
                }
                valid = true;
            } catch (Exception e) {
                System.err.println("Invalid input");
            }
        } while (!valid);

        Board board = new Board(height, width);
        Player pen = new Player(board);
        Jarvis angry = new Jarvis(board);

        // Starting at the start of the thread.
        board.placeElement(angry, height / 2, width / 2);

        // these last two need to run in their own threads
        new Thread(new java.lang.Runnable() {
            /**
             * Run till not hugged.
             */
            @Override
            public void run() {
                while (!board.beenHugged()) {
                    angry.run();
                    // board.printBoard();
                }
            }
        }).start();

        pen.run();

        input.close();
    }
}