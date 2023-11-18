package Controller.Users;

import java.util.ArrayList;

import Controller.Account.LoginManager;
import Controller.File.User.WriteUser;
import Entity.Camp;
import Entity.CampCommittee;
import Entity.Enquiry;
import Entity.Student;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StudentRepository;

public class CampCommitteeManager {
    public static CampCommittee toCampCommittee(Student s){
        String name = s.getName();
        String userID = s.getUserID();
        String faculty = s.getFaculty();
        String password = s.getPassword();
        ArrayList<Enquiry> listOfEnquiry = s.getEnquiries();
        ArrayList<Camp> listOfCamp = s.getRegisteredCamps();
        CampCommittee tempStudent = new CampCommittee(userID, name, faculty, password);
        tempStudent.setEnquiries(listOfEnquiry);
        tempStudent.setRegisteredCamps(listOfCamp);
        for (Camp c : listOfCamp){
            ArrayList <Student> listOfParticipants = c.getParticipants();
            listOfParticipants.remove(s);
            listOfParticipants.add(tempStudent);
        }
        LoginManager.setUser(tempStudent);
        LoginManager.setCurrentRole("CampCommittee");
        StudentRepository.removeStudent(s);
        CampCommitteeRepository.addCampCommittee(tempStudent);
        WriteUser.FileWriteStudent();
        WriteUser.FileWriteCampCommittee();
        return tempStudent;
    }
}
