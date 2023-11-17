package Controller.Report;

import java.io.FileWriter;
import java.io.IOException;

public class Report {
    protected String content;
    protected String filePath = "Assignment/output/";

    public void generate() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(content);
            fileWriter.close();
            System.out.println("Report generated successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while generating the report: " + e.getMessage());
        }
    }
}