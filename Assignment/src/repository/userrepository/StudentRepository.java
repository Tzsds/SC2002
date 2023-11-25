package repository.userrepository;

import java.util.ArrayList;

import entity.Student;

/**
 * Represents a repository for storing and managing student records
 * This class provides methods to retrieve, add, remove and query student information stored in the repository
 * 
 * @author SCSZ Group 4
 * @version 1.0 
 * @since 25/11/2023
 */
public class StudentRepository {
    
    /** The list of students stored in the repository */
    private static ArrayList<Student> listOfStudents = new ArrayList<Student>();

    /**
     * Gets the list of students stored in the repository
     *  
     * @return The list of students
     */
    public static ArrayList<Student> getListOfStudents(){
        return listOfStudents;
    }

    /**
     * Adds a new student to the repository
     * 
     * @param s The student to be added
     */
    public static void addStudent(Student s){
        listOfStudents.add(s);
    }

    /**
     * Removes a student from the repository
     * 
     * @param s The student to be removed
     */
    public static void removeStudent(Student s){
        listOfStudents.remove(s);
    }

    /**
     * Gets a student by their ID
     * 
     * @param id The ID of the student
     * @return The student with the specified ID, or null if not found
     */
    public static Student getStudentByID(String id){
        for (Student s : listOfStudents){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
