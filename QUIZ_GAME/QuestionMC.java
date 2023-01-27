
import java.util.ArrayList;

/**
 * Questoin MC is a class with atributes of Multiple Choice question(s).
 * 
 * @author Mayank
 *
 */
public class QuestionMC implements Question {
    /**
     * Refers to the instance variable question of type String.
     */
    private String question;
    /**
     * Refers to the instance variable ArrayList of answers of type String.
     */
    private ArrayList<String> answers;
    /**
     * Refers to the instance variable correct answer index within the available
     * number of choices.
     */
    private int correctAnsIndex;
    /**
     * Refers to the instance variable points of each question.
     */
    private int points;

    /**
     * @param question        is holder for question.
     * @param answers         is holder for answers.
     * @param correctAnsIndex is holder for correct answer Index.
     * @param points          is the holder for points of each question.
     */
    public QuestionMC(String question, ArrayList<String> answers, int correctAnsIndex, int points) {
        this.question = question;
        this.answers = answers;
        this.correctAnsIndex = correctAnsIndex;
        this.points = points;
    }

    /**
     * Returns quesiton as a String.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Boolean based check to see if answer is correct.
     */
    public boolean isCorrect(String answers) {
        // get correct index and then compare it.
        int index = this.answers.indexOf(answers);
        if (index == correctAnsIndex) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns correct answer Index.
     */
    public String getCorrectAns() {
        return answers.get(correctAnsIndex);
    }

    /**
     * Returns points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Returns possible answers as a String.
     */
    public ArrayList<String> getAnswers() {
        return answers;
    }
}