package controller.file.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import entity.CampCommittee;
import entity.Staff;
import entity.Student;
import repository.userrepository.CampCommitteeRepository;
import repository.userrepository.StaffRepository;
import repository.userrepository.StudentRepository;

/**
 * The class provides methods to read user information from CSV files, initialise
 * repositories, and reset user data if necessary. It is responsible for reading
 * student, staff and camp committee data from CSV files and populating
 * the respective repositories
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */

public class ReadUser {

    private static String studentListCSV = "Assignment/database/student_list.csv";
    private static String staffListCSV = "Assignment/database/staff_list.csv";
    private static String studentCSV = "Assignment/database/student.csv";
    private static String staffCSV = "Assignment/database/staff.csv";
    private static String campCommitteeCSV = "Assignment/database/camp_committee.csv";

    /**
     * Reads user information from CSV files, intialising repositories, and reset user data.
     * This method reads student and staff data from CSV files, adds them to the corresponding 
     * repositories, and create new CSV files for camp committees, student and staff
     */

    public static void readUserWithReset(){

        //Reading in the studentCSV
        try (BufferedReader br = new BufferedReader(new FileReader(studentListCSV))){
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
        try (BufferedReader br = new BufferedReader(new FileReader(staffListCSV))){
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

        // create new camp committee csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(campCommitteeCSV))) {
            String header = "name,faculty,userID,password,points\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // create new student csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentCSV))) {
            String header = "name,faculty,userID,password\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // create new staff csv
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(staffCSV))) {
            String header = "name,faculty,userID,password\n";
            writer.write(header);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Read user information from CSV files without resetting existing data in repositories.
     * This method reads student, staff and camp committee data from CSV files and add them to 
     * the corresponding repositories without resetting existing data.
     */

    public static void readUserWithoutReset(){
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

        //Reading campCommitteeCSV
         try (BufferedReader br = new BufferedReader(new FileReader(campCommitteeCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 5){
                    String name = words[0].trim();
                    String faculty = words[1].trim();
                    String userID = words[2].trim();
                    String password = words[3].trim();
                    int points = Integer.parseInt(words[4].trim());
                    CampCommitteeRepository.addCampCommittee(new CampCommittee(userID, name, faculty, password, points));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
