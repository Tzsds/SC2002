package controller.file;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The FileCheck class provides method to check if essential CSV files exist in the application
 * It includes file paths for student, staff, camp commmittee, suggestion, enquiry, camp details and camp student list CSV files
 * The main purpose of this class is to determine whether these files exist, which is crucial for proper functioning
 * when reading or writing data to and from these files
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class FileCheck {
    
    /** File path to student CSV file */
    private static String studentCSV = "Assignment/database/student.csv";
    /** File path to staff CSV file */
    private static String staffCSV = "Assignment/database/staff.csv";
    /** File path to camp committee CSV file */
    private static String campCommitteeCSV = "Assignment/database/camp_committee.csv";
    /** File path to suggestions CSV file */
    private static String suggestionCSV = "Assignment/database/suggestions.csv";
    /** File path to enquiries CSV file */
    private static String enquiryCSV = "Assignment/database/enquiries.csv";
    /** File path to camp details CSV file */
    private static String campDetailCSV = "Assignment/database/camp_details.csv";
    /** File path to camp student list CSV file */
    private static String campStudentListCSV = "Assignment/database/camp_student_list.csv";

    /**
     * Checks if essential CSV files exist
     * Iterates through the array of file paths and check if each file exists
     * 
     * @return True if all files exist, False if any file is missing
     */
    public static boolean FileExist(){
        String [] files = {studentCSV, staffCSV, campCommitteeCSV, suggestionCSV, enquiryCSV,
                            campDetailCSV, campStudentListCSV};
        for (String s : files){
            Path path = Paths.get(s);
            if (Files.exists(path)){
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }
}
