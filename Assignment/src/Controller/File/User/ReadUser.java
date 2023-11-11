package Controller.File.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Entity.CampCommittee;
import Entity.Staff;
import Entity.Student;
import Repository.UserRepository.CampCommitteeRepository;
import Repository.UserRepository.StaffRepository;
import Repository.UserRepository.StudentRepository;

public class ReadUser {
    public static void readUserWithReset(){
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
                    StudentRepository.addStudent(new Student(userID, name, faculty, "password"));
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
                    StaffRepository.addStaff(new Staff(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readUserWithoutReset(){
        String studentCSV = "Assignment/database/student.csv";
        String staffCSV = "Assignment/database/staff.csv";
        String campCommitteeCSV = "Assignment/database/campcommittee.csv";

        //Reading studentCSV
        try (BufferedReader br = new BufferedReader(new FileReader(studentCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 4){
                    String name = words[0].trim();
                    String faculty = words[1].trim();
                    String userID = words[2].trim();
                    String password = words[3].trim();
                    StudentRepository.addStudent(new Student(userID, name, faculty, password));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //Reading staffCSV
        try (BufferedReader br = new BufferedReader(new FileReader(staffCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 4){
                    String name = words[0].trim();
                    String faculty = words[1].trim();
                    String userID = words[2].trim();
                    String password = words[3].trim();
                    StaffRepository.addStaff(new Staff(userID, name, faculty, password));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

         try (BufferedReader br = new BufferedReader(new FileReader(campCommitteeCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 4){
                    String name = words[0].trim();
                    String faculty = words[1].trim();
                    String userID = words[2].trim();
                    String password = words[3].trim();
                    CampCommitteeRepository.addCampCommittee(new CampCommittee(userID, name, faculty, password));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
