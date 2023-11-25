package controller.user;

import java.util.ArrayList;

import controller.account.LoginManager;
import controller.file.user.WriteUser;
import entity.Camp;
import entity.CampCommittee;
import entity.Enquiry;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StudentRepository;

/**
 * This class manages the coversion of a Student to a CampCommittee member
 * Provides a method to create a CampCommittee member based on a given Student 
 * and perform necessary updates
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class CampCommitteeManager {

    /**
     * Converts a Student to a CampCommittee member and performs necessary updates
     * 
     * @param s The student to be converted to a CampCommittee member
     * @return The newly created CampCommittee member
     */
    public static CampCommittee toCampCommittee(Student s){
        // Extract information from the student
        String name = s.getName();
        String userID = s.getUserID();
        String faculty = s.getFaculty();
        String password = s.getPassword();
        ArrayList<Enquiry> listOfEnquiry = s.getEnquiries();
        ArrayList<Camp> listOfCamp = s.getRegisteredCamps();

        // Create a new CampCommittee member with extracted information
        CampCommittee tempStudent = new CampCommittee(userID, name, faculty, password);
        tempStudent.setEnquiries(listOfEnquiry);
        tempStudent.setRegisteredCamps(listOfCamp);

        // Update the participants list in registered camps
        for (Camp c : listOfCamp){
            ArrayList <Student> listOfParticipants = c.getParticipants();
            listOfParticipants.remove(s);
            listOfParticipants.add(tempStudent);
        }

        // Change the role of current user to CampCommittee
        LoginManager.setUser(tempStudent);
        LoginManager.setCurrentRole("CampCommittee");

        // Remove the student from the StudentRepository and add the student to CampCommittee repository
        StudentRepository.removeStudent(s);
        CampCommitteeRepository.addCampCommittee(tempStudent);

        // Write updated data to files
        WriteUser.FileWriteStudent();
        WriteUser.FileWriteCampCommittee();
        return tempStudent;
    }
}
