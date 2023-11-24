package controllers.report;

import java.io.FileWriter;
import java.io.IOException;

import controllers.utils.InputScanner;

public class Report {
    protected String content;
    protected String filePath = "Assignment/output/";

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

    public void printInTerminal() {
        System.out.println();
        System.out.println(content);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    
    public void inputAndSetFilePath() {
        String filename = InputScanner.promptForString("Input your filename: ");
        filePath += filename + ".txt";
    }
}