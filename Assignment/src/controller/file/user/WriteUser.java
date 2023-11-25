package controller.file.user;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;
import repository.userrepository.StudentRepository;

/**
 * The WriteUser class provides methods to write user data (Staff, Student, CampCommittee) to respective CSV files
 * It also includes methods to update the CSV files with the current list of users in their respective repositories
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class WriteUser {
    
    //Header for the CSV files
    private static String header = "name,faculty,userID,password\n";
    
    /**
     * Writes staff data to the staff CSV file
     * Retrieves the list of staff members from the StaffRepository and updates the CSV file accordingly
     */
    public static void FileWriteStaff(){
        String staffCSV = "Assignment/database/staff.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(staffCSV))) {
            // Header of CSV File
            writer.write(header);

            for (Staff s : StaffRepository.getListOfStaff()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "\n";
                writer.write(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes student data to the student CSV file
     * Retrieves the list of student members from the StudentRepository and updates the CSV file accordingly
     */
    public static void FileWriteStudent(){
        String studentCSV = "Assignment/database/student.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentCSV))) {
            writer.write(header);

            for (Student s : StudentRepository.getListOfStudents()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "\n";
                writer.write(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes camp committee data to the camp committee CSV file
     * Retrieves the list of camp committee members from the CampCommitteeRepository and updates the CSV file accordingly
     */
    public static void FileWriteCampCommittee(){
        String campCommitteeCSV = "Assignment/database/camp_committee.csv";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campCommitteeCSV))) {
            writer.write("name,faculty,userID,password,points\n");

            for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
                String data = s.getName() + "," + s.getFaculty() + "," + s.getUserID() + ","
                        + s.getPassword() + "," + s.getPoints() + "\n";
                writer.write(data);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}