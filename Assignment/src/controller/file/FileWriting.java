package controller.file;

import controller.file.camp.WriteCampDetails;
import controller.file.camp.WriteCampStudentList;
import controller.file.suggestion.WriteSuggestion;
import controller.file.user.WriteUser;

/**
 * The FileWriting class provides methods to write data to various CSV files related to the application
 * Includes methods for writing user-related data (CampCommittee, Student and Staff),
 * suggestions, camp details and camp student lists to their respective CSV files
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class FileWriting {

    /**
     * Writes user-related data to the corresponding CSV files
     * Calls method from the WriteUser class to write CampCommittee, Student and Staff data
     */
    public static void FileWriteUser() {
        WriteUser.FileWriteCampCommittee();
        WriteUser.FileWriteStudent();
        WriteUser.FileWriteStaff();
    }
    
    /**
     * Writes suggestions data to the suggestions CSV file
     * Calls the writeSuggestion method from the WriteSuggestion class
     */
    public static void FileWriteSuggestion() {
        WriteSuggestion.writeSuggestion();
    }

    /**
     * Writes camp details data to the camp details CSV file
     * Calls the WriteCamp method from the WriteCampDetails class
     */
    public static void FileWriteCampDetails(){
        WriteCampDetails.WriteCamp();
    }

    /**
     * Writes camp student list data to the camp student list CSV file
     * Calls the WriteStudentList method from the WriteCampStudentList class
     */
    public static void FileWriteCampStudentList(){
        WriteCampStudentList.WriteStudentList();
    }
}