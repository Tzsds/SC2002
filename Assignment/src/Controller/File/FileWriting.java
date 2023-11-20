package Controller.File;

import Controller.File.Camp.WriteCampDetails;
import Controller.File.Camp.WriteCampStudentList;
import Controller.File.Suggestion.WriteSuggestion;
import Controller.File.User.WriteUser;


public class FileWriting {

    public static void FileWriteUser() {
        WriteUser.FileWriteCampCommittee();
        WriteUser.FileWriteStudent();
        WriteUser.FileWriteStaff();
    }

    public static void FileWriteSuggestion() {
        WriteSuggestion.writeSuggestion();
    }

    public static void FileWriteCampDetails(){
        WriteCampDetails.WriteCamp();
    }

    public static void FileWriteCampStudentList(){
        WriteCampStudentList.WriteStudentList();
    }
}