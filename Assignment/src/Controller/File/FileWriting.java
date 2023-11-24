package controller.file;

import controller.file.camp.WriteCampDetails;
import controller.file.camp.WriteCampStudentList;
import controller.file.suggestion.WriteSuggestion;
import controller.file.user.WriteUser;


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