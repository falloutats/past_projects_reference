import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;

/**
 * Reader class to launch the file.
 * 
 * @author
 */
public class FileReader {

    private Scanner fileScanner;

    /**
     * Launch and read the file, if not found throw and exception.
     */
    public FileReader() {
        JFileChooser chooser = new JFileChooser();

        try {
            if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
                throw new Error("Input file not selected");
            }
            File questionFile = chooser.getSelectedFile();
            fileScanner = new Scanner(questionFile);

        } catch (FileNotFoundException e) {
            System.err.println("Data file not found.");
        } catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
    }

    /**
     * If has next line boolean check.
     * 
     * @return the info.
     */
    public boolean fileHasNextLine() {
        return this.fileScanner.hasNextLine();
    }

    /**
     * Method to return file as a String.
     * 
     * @return info as String.
     */
    public String getNextLineOfFile() {
        return this.fileScanner.nextLine();
    }

    /**
     * Close scanner, and if file not found catch the exception.
     */
    @Override
    public void finalize() {
        try {
            this.fileScanner.close();
        } catch (Exception ex) {
            System.err.println("A mysterious error occurred on closing Scanner.");
            ex.printStackTrace(System.err);
        }
    }
}
