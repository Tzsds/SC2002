package controller.report;
import java.io.FileWriter;
import java.io.IOException;
import controller.utils.InputScanner;

/**
 * This is the superclass of the current CampReport, EnquiryReport, and PerformanceReport
 * report contains content and text file can be generated
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class Report {

    /**
     * content refers to the string content to be printed in the report
     */
    protected String content;
    /**
     * filePath refers to the filePath where report is generated
     * filePath is to be updated or overriden
     */
    protected String filePath = "Assignment/output/";

    /**
     * - generate function will generate a text file in the file path
     * with content attribute as its content
     * - print the content in the terminal
     */

    public void generate() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println("Report generated successfully!");
            printInTerminal();
        } catch (IOException e) {
            System.out.println("An error occurred while generating the report: " + e.getMessage());
        }
    }

    /**
     * this function prints the content in the terminal without creating a text file
     */

    public void printInTerminal() {
        System.out.println();
        System.out.println(content);
    }

    /**
     * this function sets the content attribute
     * @param content - the content to be set
     */

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * the function gets the content attribute
     * @return content
     */

    public String getContent() {
        return content;
    }

    /**
     * this function will prompt the user to:
     * - input for their filename
     * - set the filename
     */
    
    public void inputAndSetFilePath() {
        String filename = InputScanner.promptForString("Input your filename: ");
        filePath += filename + ".txt";
    }
}