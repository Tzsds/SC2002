package controller.file.camp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entity.Camp;
import entity.CampCommittee;
import entity.Student;
import repository.CampRepository;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StudentRepository;

/**
 * Utility class for reading and parsing student camp details from a csv file
 * Provides methods to read camp details
 * 
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class ReadCampStudentList {
    
    /** The file path to the camp student list CSV file */
    private static String campStudentListCSV = "Assignment/database/camp_student_list.csv";

    /**
     * Reads camp details from the CSV file without resetting its content
     * Updates the CampRepository and StaffRepository with the retrieved
     * information
     */
    public static void readCampStudentListWithoutReset() {
        try (BufferedReader br = new BufferedReader(new FileReader(campStudentListCSV))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] words = line.split(",");
                if (words.length >= 3) {
                    String campName = words[0].trim();
                    String userID = words[1].trim();
                    String role = words[2].trim();

                    Camp camp = CampRepository.getCampByCampName(campName);
                    Student student = StudentRepository.getStudentByID(userID);
                    if (student == null) {
                        student = CampCommitteeRepository.getCommitteeByID(userID);
                    }
                    student.addRegisteredCamp(camp);

                    if (role.equals("CampCommittee")) {
                        camp.addCampCommittee(student);
                        CampCommittee committee = CampCommitteeRepository.getCommitteeByID(userID);
                        committee.setCommitteeOf(camp);
                        // System.out.println(student.getUserID() + " add to Camp Comm");
                    } else if (role.equals("Withdrawn")) {
                        camp.addWithdrawnStudent(student);
                        // System.out.println(student.getUserID() + " add to Withdrawn");
                    } else {
                        camp.addParticipants(student);
                        // System.out.println(student.getUserID() + " add to Participants");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the content of the camp details CSV file by overwriting it with a
     * header line
     */
    public static void readCampStudentListWithReset() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campStudentListCSV))) {
            String header = "campName,userID,role\n";
            writer.write(header);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}