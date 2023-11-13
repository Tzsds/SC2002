package Controller.File;

import Controller.File.Camp.ReadCampDetails;
import Controller.File.Enquiry.ReadEnquiry;
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
        ReadEnquiry.readWithReset();
        ReadCampDetails.readCampWithReset();
        

        // Need to reset Camps, Suggestion CSV file if we choose to reset
        // To be implemented in the future
    
    }

    private static void readWithoutReset(){
        ReadUser.readUserWithoutReset();
        ReadCampDetails.readCampWithoutReset();
        ReadSuggestion.readWithoutReset(); //clear the suggestions.csv file
        ReadEnquiry.readWithoutReset();
        // ReadCampDetails.readCampDetailsWithoutReset();
        // ReadCampStudentList.ReadCampStudentListWithoutReset();
        
        // Need to read in Camp, Student
        // To be implemented in the future
    }
}
