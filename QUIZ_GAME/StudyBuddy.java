
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the I/O of the program and builds QuestoinMaker. Along
 * with that, it has error handling, checking for user input, and contains
 * instances of MC, FB, and T/F.
 * 
 * @author Mayank
 */
public class StudyBuddy {

    /**
     * Study method has all the implications for input and navigation of the
     * program.
     */
    public void study() {
        Scanner sc = new Scanner(System.in);
        QuestionMaker qm = new QuestionMaker();
        ArrayList<Question> questions;
        // Getting questions here.
        questions = qm.getQuestions();
        // Total points variable.
        int totalPoints = 0;
        // Correct answer variable.
        int correctAnswer = 0;
        // Total questoins asnwered variable.
        int totalQuestion = 0;
        // While the exception is true keep asking.
        while (true) {
            try {
                System.out.println("How many questions do you want to answer ? : ");
                String input = sc.nextLine();
                //Scanning nextLine()
                totalQuestion = Integer.parseInt(input);
                // Check for requested number of questoins and available questions in the file.
                if (totalQuestion <= 0 || totalQuestion > questions.size()) {
                    throw new Exception();
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input!!" + " Available Quesitons --> " + questions.size());
            }
        }
        // Number of questions answered.
        int index = 0;
        // All marked false.
        boolean[] marked = new boolean[totalQuestion];
        int place = 0;
        int start = 0;
        while (index < totalQuestion) {
            String ans = null;
            // next question to answer
            for (int next = start; next < totalQuestion; next++) {
                if (!marked[next]) {
                    // change the value of place.
                    place = next;
                    break;
                }
            }

            if (questions.get(place) instanceof QuestionMC) {
                System.out.println("");
                Question object = (QuestionMC) questions.get(place);
                ArrayList<String> answers = new ArrayList<String>();
                System.out.println("Points " + object.getPoints());
                System.out.println("Multiple Choice: " + object.getQuestion());

                answers = object.getAnswers();

                char choice = 'A';
                // Print mc options.
                for (String str : answers) {
                    System.out.println(choice + ")" + str);
                    ++choice;
                }

                ans = sc.nextLine();
                // Check for input errors.
                while (!(ans.equalsIgnoreCase("pass") || ans.equalsIgnoreCase("delay")
                        || (ans.length() == 1 && (int) Character.toUpperCase(ans.charAt(0)) >= 65
                                && (int) Character.toUpperCase(ans.charAt(0)) < 65 + answers.size()))) {
                    System.out.println("Invalid input. Try again: ");
                    ans = sc.nextLine();

                }

                if (!ans.equalsIgnoreCase("pass") && !ans.equalsIgnoreCase("delay")) {

                    char c = ans.charAt(0);
                    c = Character.toUpperCase(c);
                    String CorrectAns = object.getCorrectAns();
                    // Keep in bounds.
                    int position = c - 'A';
                    try {
                        String in = answers.get(position);
                        if (object.isCorrect(in)) {
                            // Add points if answer is correct.
                            totalPoints = totalPoints + object.getPoints();
                            correctAnswer++;
                            System.out.println("Correct Answer");
                        } else {
                            throw new Exception();
                        }
                    } catch (Exception e) {
                        totalPoints = totalPoints - object.getPoints();
                        System.out.println("Incorrect, the answer was " + CorrectAns + " You lose " + object.getPoints()
                                + " points");
                    }
                }
            }
            if (questions.get(place) instanceof QuestionTF) {
                System.out.println("");
                Question obj = (QuestionTF) questions.get(place);
                // come back to this
                System.out.println("Points for this question: " + obj.getPoints());
                System.out.println("True/False: " + obj.getQuestion());

                ans = sc.nextLine();
                // Error check if input contains wrong input.
                while (!ans.equalsIgnoreCase("false") && !ans.equalsIgnoreCase("true") && !ans.equalsIgnoreCase("pass")
                        && !ans.equalsIgnoreCase("delay")) {
                    System.out.println("Invalid input. Try again: ");
                    ans = sc.nextLine();
                }
                if (!ans.equalsIgnoreCase("pass") && !ans.equalsIgnoreCase("delay")) {

                    String CorrectAns = obj.getCorrectAns().toUpperCase();
                    if (obj.isCorrect(ans)) {
                        totalPoints = totalPoints + obj.getPoints();
                        correctAnswer++;
                        System.out.println("Correct Answer");
                    } else {
                        totalPoints = totalPoints - obj.getPoints();

                        System.out.println(
                                "Incorrect, the answer was " + CorrectAns + " You lose " + obj.getPoints() + " points");
                    }
                }
            }
            // Fill in the blank chce
            if (questions.get(place) instanceof QuestionFB) {
                System.out.println("");
                Question obj = (QuestionFB) questions.get(place);
                System.out.println("Points " + obj.getPoints());
                System.out.println(" " + obj.getQuestion());

                ans = sc.nextLine();

                if (!ans.equalsIgnoreCase("pass") && !ans.equalsIgnoreCase("delay")) {
                    String CorrectAns = obj.getCorrectAns().toUpperCase();
                    // If correct then add score.
                    if (obj.isCorrect(ans)) {
                        totalPoints = totalPoints + obj.getPoints();
                        correctAnswer++;
                        System.out.println("Correct Answer");
                    } else {
                        // Minus points if not correct.
                        totalPoints = totalPoints - obj.getPoints();
                        System.out.println(
                                "Incorrect, the answer was " + CorrectAns + " You lose " + obj.getPoints() + " points");
                    }
                }
            }
            if (!ans.equalsIgnoreCase("delay")) {
                index++;
                // Change from false to true.
                marked[place] = true;
            }
            start++;
            // Reset here, and looks for the next in line question.
            if (start == totalQuestion) {
                start = 0;
            }
        }
        System.out.println("");
        System.out.println("Out of " + totalQuestion + " you got " + correctAnswer + " correct!");
        System.out.println("You got " + totalPoints);
        // Based on final score.
        if (((double) correctAnswer) / totalQuestion <= 0.5) {
            System.out.println("Better luck next time!");
        } else {
            System.out.println("Well Done!");
        }
        sc.close();
    }

    /**
     * Method builds StudyBuddy and calls method study.
     * 
     * @param args
     */
    public static void main(String[] args) {
        StudyBuddy st = new StudyBuddy();
        st.study();

    }
}
