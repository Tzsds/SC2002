package Controller.File;

import Controller.File.Suggestion.ReadSuggestion;
import Controller.File.User.ReadUser;

public class FileRead {

    public static void read(int reset){
        if (reset == 1){
            readWithReset();
        }
        else{
            readWithoutReset();
        }
    }

    private static void readWithReset(){
        ReadUser.readUserWithReset();
        FileWriting.FileWriteUser(); //reset staff,student,camp comm csv file

        ReadSuggestion.readWithReset(); //reset suggestions.csv file

        // Need to reset Camps, Suggestion, Enquiries CSV file if we choose to reset
        // To be implemented in the future
    
    }

    private static void readWithoutReset(){
        ReadUser.readUserWithoutReset();
        ReadSuggestion.readWithoutReset(); //clear the suggestions.csv file
        
        // Need to read in Camp, Enquiries, Student
        // To be implemented in the future
    }
}
