package controller.file.suggestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entity.Camp;
import entity.CampCommittee;
import entity.Suggestion;
import entity.Suggestion.SuggestionStatus;
import repository.SuggestionRepository;
import repository.userrepository.CampCommitteeRepository;

/**
 * The ReadSuggestion class provides methods for reading and processing suggestions from a CSV file
 * Includes methods to read suggestions from the CSV file with or without reset
 * and update the internal repositories accordingly
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class ReadSuggestion {

    /** The file path to the suggestions CSV file */
    private static String path = "Assignment/database/suggestions.csv";
    
    /**
     * Reads suggestions from the CSV file without resetting
     * Parses each line of the file, creates Suggestion objects and updates repository
     * If the suggestion status is PENDING, adds it to the corresponding camp's list of suggestions
     */
    public static void readWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine(); // skip the header
            String line;
            while ((line = br.readLine()) != null){
                String[] words = line.split(",");
                if (words.length >= 3){
                    String senderID = words[0].trim();
                    String content = words[1].trim();
                    SuggestionStatus x = SuggestionStatus.valueOf(words[2].trim());
                    CampCommittee user = CampCommitteeRepository.getCommitteeByID(senderID);
                    Suggestion s1 = new Suggestion(user, content);
                    Camp camp = user.getCommitteeOf();
                    if (x == SuggestionStatus.PENDING){
                        camp.getListOfSuggestions().add(s1);
                    }
                    s1.setStatus(x);
                    user.addSuggestion(s1);
                    SuggestionRepository.addSuggestionToRepo(s1);
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Resets the content of the suggestions CSV file by overwriting it with a new header
     * This method is used when a reset of the suggestions data is required
     */
    public static void readWithReset(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String header = "senderID,content,STATUS\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
