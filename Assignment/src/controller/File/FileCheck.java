package controller.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileCheck {
    
    private static String studentCSV = "Assignment/database/student.csv";
    private static String staffCSV = "Assignment/database/staff.csv";
    private static String campCommitteeCSV = "Assignment/database/camp_committee.csv";
    private static String suggestionCSV = "Assignment/database/suggestions.csv";
    private static String enquiryCSV = "Assignment/database/enquiries.csv";
    private static String campDetailCSV = "Assignment/database/camp_details.csv";
    private static String campStudentListCSV = "Assignment/database/camp_student_list.csv";


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
