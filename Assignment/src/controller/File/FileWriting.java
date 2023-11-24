package controller.File;

import controller.File.camp.WriteCampDetails;
import controller.File.camp.WriteCampStudentList;
import controller.File.suggestion.WriteSuggestion;
import controller.File.user.WriteUser;


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