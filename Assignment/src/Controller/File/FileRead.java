package Controller.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Entity.CampCommittee;
import Entity.Staff;
import Entity.Student;
import Repository.UserRepository;

public class FileRead {

    public static void read(int reset){
        if (reset == 1){
            readWithReset();
        }
        else{
            readWithoutReset();
        }
    }

    private static void readWithReset(){
        String studentCSV = "Assignment/database/student_list.csv";
        String staffCSV = "Assignment/database/staff_list.csv";

        //Reading in the studentCSV
        try (BufferedReader br = new BufferedReader(new FileReader(studentCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String name = words[0].trim();
                    String email = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = email.substring(0, email.indexOf("@"));
                    UserRepository.addUser(new Student(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Reading in the staffCSV
        try (BufferedReader br = new BufferedReader(new FileReader(staffCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 3){
                    String name = words[0].trim();
                    String email = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = email.substring(0, email.indexOf("@"));
                    UserRepository.addUser(new Staff(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        FileWriting.FileWriteUser(); //create a new users csv file
    
    }

    private static void readWithoutReset(){
        String allUserCSV = "Assignment/database/users.csv";

        //Reading in the CSV that has all the updated passwords
        try (BufferedReader br = new BufferedReader(new FileReader(allUserCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 5){
                    String name = words[0].trim();
                    String role = words[1].trim();
                    String faculty = words[2].trim();
                    String userID = words[3].trim();
                    String password = words[4].trim();
                    if (role.equals("Staff")){
                        UserRepository.addUser(new Staff(userID, name, faculty, password));
                    }
                    else if (role.equals("CampCommittee")){
                        UserRepository.addUser(new CampCommittee(userID, name, faculty, password));
                    }
                    else{
                        UserRepository.addUser(new Student(userID, name, faculty, password));
                    }
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
