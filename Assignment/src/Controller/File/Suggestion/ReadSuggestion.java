package Controller.File.Suggestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Entity.CampCommittee;
import Entity.Suggestion;
import Repository.UserRepository.CampCommitteeRepository;

public class ReadSuggestion {

    private static String path = "Assignment/database/suggestions.csv";
    
    public static void readWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine(); // skip the header
            String line;
            while ((line = br.readLine()) != null){
                String[] words = line.split(",");
                if (words.length >= 2){
                    String senderID = words[0].trim();
                    String content = words[1].trim();
                    CampCommittee user = CampCommitteeRepository.getUserByID(senderID);
                    user.addSuggestion(new Suggestion(content));
                }
            }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void readWithReset(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            String header = "senderID,content\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
