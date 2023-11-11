package Controller.Account;

import Entity.Camp;
import Entity.CampCommittee;
import Entity.Staff;
import Entity.Student;
import Entity.User;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StaffRepository;
import Repository.UserRepository.StudentRepository;
import UI.AccountInput;

public class LoginManager {

    private static User currentUser; //Current user which is in the system
    private static String role; //Role of current user in the system

    public static void Login(){
        LoginManager.checkID();
        LoginManager.checkPassword(currentUser);
        System.out.println(currentUser.getName() + " logged in!");
    }

    private static void checkID(){
        User temp;
        while (true){
            String id = AccountInput.inputID();
    
            for (Student s : StudentRepository.getListOfStudents()){
                temp = s;
                if (temp.getUserID().equals(id)){
                    currentUser = s;
                    role = "Student";
                    return;
                }
            }
            for (Staff s : StaffRepository.getListOfStaff()){
                temp = s;
                if (temp.getUserID().equals(id)){
                    currentUser = s;
                    role = "Staff";
                    return;
                }
            }
            for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
                temp = s;
                if (temp.getUserID().equals(id)){
                    currentUser = s;
                    role = "CampCommittee";
                    return;
                }
            }
            
            System.out.println("Invalid User ID!");
        }
    }

    private static void checkPassword(User person){
        while (true){
            String pw = AccountInput.inputPW();
            if (pw.equals(person.getPassword())){
                return;
            }
            else{
                System.out.println("Wrong password entered!");
            }
        }
    }

    public static User getCurrentUser(){
        return currentUser;
    }

    public static String getUserRole(){
        return role;
    }
}
