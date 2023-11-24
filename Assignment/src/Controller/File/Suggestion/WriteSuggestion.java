package Controller.File.Suggestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.Suggestion;
import repos.SuggestionRepository;


public class WriteSuggestion {

    public static void writeSuggestion(){
        String path = "Assignment/database/suggestions.csv";
        String header = "senderID,content,STATUS\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(header);
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
