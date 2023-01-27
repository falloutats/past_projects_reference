
import java.util.ArrayList;

/**
 * Questoin FB is a class with attributes of Fill in the blanks type question(s).
 * 
 * @author Mayank
 */
public class QuestionFB implements Question {
    /**
     * Refers to the instance variable question.
     */
    private String question;
    /**
     * Refers to the instance variable answer.
     */
    private String answer;
    /**
     * Refers to the instance variable points of a question.
     */
    private int points;

    /**
     * @param question is holder for question.
     * @param answer   is the holder for right answer.
     * @param points   is the holder for points.
     */
    public QuestionFB(String question, String answer, int points) {
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    /**
     * Returns question as a String.
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
     * Returns answer.
     */
    public String getCorrectAns() {
        return answer;
    }

    /**
     * Return points of the questoin.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Possible answers in a form of a String.
     */
    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<String>();
        answers.add(answer);
        return answers;
    }
}