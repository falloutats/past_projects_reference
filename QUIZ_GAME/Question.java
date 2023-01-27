import java.util.ArrayList;

/**
 * This is the interface class and contains all the methods that are implemented
 * by class Question MC,FB AND TF.
 * 
 * @author Mayank
 */
public interface Question {
    public String getQuestion();

    public boolean isCorrect(String answers);

    public String getCorrectAns();

    public int getPoints();

    public ArrayList<String> getAnswers();
}
