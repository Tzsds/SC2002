package controller.file;

import controller.file.camp.ReadCampDetails;
import controller.file.camp.ReadCampStudentList;
import controller.file.enquiry.ReadEnquiry;
import controller.file.suggestion.ReadSuggestion;
import controller.file.user.ReadUser;

/**
 * The FileRead class provides methods for reading data from CSV files into the application 
 * It supports reading user-related data (CampCommittee, Student, Staff), camp details
 * camp student lists, suggestions and enquiries from their respective CSV files
 * The class includes a method to conditionally reset the data by reading from default CSV files
 * when specified by the user
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class FileRead {

    /**
     * Reads data from CSV files based on the specified reset option
     * If the reset option is 1, it calls the method to read data with reset
     * If the reset option is 2, it calls the method to read data without reset
     * 
     * @param reset An integer indicating whether to rest the data or not
     *              If reset is 1, the data will be reset
     *              If reset is 2, the data will not be reset
     */
    public static void read(int reset){
        if (reset == 1){
            readWithReset();
        }
        else{
            readWithoutReset();
        }
    }
    
    /**
     * Reads data with a reset from default CSV files
     * Calls methods from ReadUser, ReadCampDetails, ReadCampStudentList,
     * ReadSuggestion and ReadEnquiry classes to read data with a reset
     * and then writes the reset data to the corresponding CSV files using FileWriting
     */
    private static void readWithReset(){
        ReadUser.readUserWithReset();
        FileWriting.FileWriteUser(); //reset staff,student,camp comm csv file
        ReadCampDetails.readCampWithReset();
        ReadCampStudentList.readCampStudentListWithReset();
        ReadSuggestion.readWithReset(); //reset suggestions.csv file
        ReadEnquiry.readWithReset();
    }

    /**
     * Reads data without a reset from exisiting CSV files
     * If the CSV files do not exist, it prints a message indicating that it is the first time
     * entering the system and uses the reset method instead
     * Calls methods from ReadUser, ReadCampDetails, ReadCampStudentList,
     * ReadSuggestion and ReadEnquiry classes to read data without a reset
     */
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
