package controller.account;

import controller.files.FileWriting;
import controller.utils.InputScanner;
import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import entity.User;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;
import repository.userrepository.StudentRepository;
import ui.Display;

public class LoginManager {

    private static User currentUser; //Current user which is in the system
    private static String role; //Role of current user in the system

    public static void Login(){
        LoginManager.checkID();
        LoginManager.checkPassword(currentUser);
        if (currentUser.getPassword().equals("password")){
            System.out.println("First time in the system, please change your password.");
            ChangeAccountPassword.changePassword();
            Display.firstTimeLogin();
            FileWriting.FileWriteUser();
            Login();
        }
    }

    private static void checkID(){
        User temp;
        while (true){
            String id = InputScanner.promptForString("Enter your User ID: ");
            
            for (CampCommittee s : CampCommitteeRepository.getListOfCampCommittee()){
                temp = s;
                if (temp.getUserID().equals(id)){
                    currentUser = s;
                    role = "CampCommittee";
                    return;
                }
            }
    
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
            System.out.println("Invalid User ID!");
        }
    }

    private static void checkPassword(User person){
        while (true){
            String pw = InputScanner.promptForString("Enter your password: ");
            if (pw.equals(person.getPassword())){
                return;
            }
            else{
                System.out.println("Wrong password entered!");
            }
        }
    }

    public static void setUser(User newUser){
        currentUser = newUser;
    }

    public static void setCurrentRole(String newRole){
        role = newRole;
    }
    
    public static User getCurrentUser(){
        return currentUser;
    }

    public static String getUserRole(){
        return role;
    }
}
