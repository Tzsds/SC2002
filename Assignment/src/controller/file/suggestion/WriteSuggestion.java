package controller.file.suggestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.Suggestion;
import repository.SuggestionRepository;

/**
 * The WriteSuggestion class provides a method to write suggestions to a CSV file
 * Suggestions are retrieved from the SuggestionRepository and formatted into a CSV format
 * which is then written to the specific file path
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class WriteSuggestion {

    /**
     * Writes the suggestions stored in the SuggestionRepository to a CSV file
     * Each suggestion is formatted as a CSV record with senderID, content and status
     * The CSV file is created or overwritten at the specific file path
     */
    public static void writeSuggestion(){
        /**  File path to store the suggestions CSV */
        String path = "Assignment/database/suggestions.csv";

        // CSV header containing column names
        String header = "senderID,content,STATUS\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(header);
            // Iterate through suggestions and write each one to the CSV file
            for (Suggestion s : SuggestionRepository.getListOfSuggestions()){
                String senderID = s.getProposer().getUserID();
                String content = s.getContent();
                String status = s.getStatus().name();
                String data = senderID + "," + content + "," + status + "\n";
                bw.write(data);
            }
        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
