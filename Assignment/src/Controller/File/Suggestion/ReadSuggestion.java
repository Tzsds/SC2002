package Controller.File.Suggestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Entity.CampCommittee;
import Entity.Suggestion;
import Entity.Suggestion.Status;
import Repository.SuggestionRepository;
import Repository.UserRepository.CampCommitteeRepository;

public class ReadSuggestion {

    private static String path = "Assignment/database/suggestions.csv";
    
    public static void readWithoutReset(){
        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine(); // skip the header
            String line;
            while ((line = br.readLine()) != null){
                String[] words = line.split(",");
                if (words.length >= 3){
                    String senderID = words[0].trim();
                    String content = words[1].trim();
                    Status x = Status.valueOf(words[2].trim());
                    CampCommittee user = CampCommitteeRepository.getCommitteeByID(senderID);
                    Suggestion s1 = new Suggestion(user, content);
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
