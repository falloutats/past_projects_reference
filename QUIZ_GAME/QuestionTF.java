
import java.util.ArrayList;

/**
 * Question TF is a class with attributes for True || False type question(s).
 * 
 * @author Mayank
 *
 */
public class QuestionTF implements Question {
    /**
     * Refers to the instance variable question of type String.
     */
    private String question;
    /**
     * Refers to the instance variable answer of type String.
     */
    private String answer;
    /**
     * Refers to the instance variable points of each question.
     */
    private int points;

    /**
     * @param question is holder for question.
     * @param answer   is holder for answer.
     * @param points   is holder for points.
     */
    public QuestionTF(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    /**
     * Returns question of type String.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Boolean based check to see if answer is correct.
     */
    public boolean isCorrect(String answers) {
        if (answer.equalsIgnoreCase(answers)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns answer as a String.
     */
    public String getCorrectAns() {
        return answer;
    }

    /**
     * Returns points as an int.
     */
    public int getPoints() {
        return points;
    }

    /**
     * ArrayList method to get all possible answers of type String.
     */
    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(answer);
        return answers;
    }
}