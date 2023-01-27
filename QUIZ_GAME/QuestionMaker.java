
import java.util.ArrayList;

/**
 * Question Maker class is responsible for invoking file reader and formatting
 * the question into parts using supporting arguments and methods.
 * 
 * @author Mayank
 */
public class QuestionMaker {

    /**
     * The method holds and uses if-else statements to catch the correct information
     * and then apply appropriate formatting steps to get the desired I/O ready for
     * Study and other classes.
     * 
     * @return the question of type Question(Interface).
     */
    public ArrayList<Question> getQuestions() {
        // To hold the questions of type Question Interface.

        ArrayList<Question> questions = new ArrayList<>();
        FileReader filer = new FileReader();
        // Till file has nextLine keep looping.
        while (true) {
            if (filer.fileHasNextLine() == false) {
                break;
            }
            String atline = filer.getNextLineOfFile();
            // Split at ";".
            String[] parts = atline.split(";");
            // At [0] check for the question type.
            if (parts[0].equals("MC")) {
                String question = parts[1];
                // Correct index for the correct answer.
                int num = Integer.parseInt(parts[2]);
                // List of options (Limit is 3).
                ArrayList<String> answers = new ArrayList<String>();
                for (int ans = 0; ans < num; ans++) {
                    answers.add(parts[3 + ans]);
                }
                String correctAns = parts[3 + num];
                char ch = correctAns.charAt(0);
                ch = Character.toUpperCase(ch);
                int pos = ch - 'A';
                int indexCorrect = pos;
                // Points of the question.
                int points = Integer.parseInt(parts[4 + num]);

                QuestionMC q = new QuestionMC(question, answers, indexCorrect, points);
                questions.add(q);
            }
            // At [0] check for the question type.
            if (parts[0].equals("TF")) {
                String question = parts[1];
                // Points of the questoin from the file.
                int points = Integer.parseInt(parts[3]);
                // Correct answer as a String.
                String answer = parts[2];
                QuestionTF q = new QuestionTF(question, answer, points);
                questions.add(q);
            }
            // At [0] check for the question type.
            if (parts[0].equals("FB")) {
                // Read the questoin at [1].
                String question = parts[1];
                // Points of the questoin from the file at [3].
                int points = Integer.parseInt(parts[3]);
                // Get answer at [2].
                String answer = parts[2];
                QuestionFB q = new QuestionFB(question, answer, points);
                questions.add(q);
            }

        }
        return questions;
    }
}