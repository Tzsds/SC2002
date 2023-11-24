package Controller.File;

import Controller.File.camps.WriteCampDetails;
import Controller.File.camps.WriteCampStudentList;
import Controller.File.suggestions.WriteSuggestion;
import Controller.File.users.WriteUser;


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