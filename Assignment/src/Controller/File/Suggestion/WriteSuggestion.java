package Controller.File.Suggestion;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Entity.Suggestion;
import Repository.SuggestionRepository;


public class WriteSuggestion {

    public static void writeSuggestion(){
        String path = "Assignment/database/suggestions.csv";
        String header = "senderID,content\n";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(header);
            for (Suggestion s : SuggestionRepository.getListOfSuggestions()){
                String senderID = s.getProposer().getUserID();
                String content = s.getContent();
                String data = senderID + "," + content + "\n";
                bw.write(data);
            }
        } 
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
