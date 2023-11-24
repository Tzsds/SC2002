package Controller.File;

import Controller.File.Camp.ReadCampDetails;
import Controller.File.Camp.ReadCampStudentList;
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
        ReadCampDetails.readCampWithReset();
        ReadCampStudentList.readCampStudentListWithReset();
        ReadSuggestion.readWithReset(); //reset suggestions.csv file
        ReadEnquiry.readWithReset();
    }

    private static void readWithoutReset(){ 
        if (!FileCheck.FileExist()){
            System.out.println("First time entering the system. Using reset instead");
            readWithReset();
            return;
        }
        ReadUser.readUserWithoutReset();
        ReadCampDetails.readCampWithoutReset();
        ReadCampStudentList.readCampStudentListWithoutReset();
        ReadSuggestion.readWithoutReset(); //clear the suggestions.csv file
        ReadEnquiry.readWithoutReset();
    }
}
