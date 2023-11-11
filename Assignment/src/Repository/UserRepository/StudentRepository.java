package Repository.UserRepository;

import java.util.ArrayList;

import Entity.Student;

public class StudentRepository {
    private static ArrayList<Student> listOfStudents = new ArrayList<Student>();

    public static ArrayList<Student> getListOfStudents(){
        return listOfStudents;
    }

    public static void addStudent(Student s){
        listOfStudents.add(s);
    }

    public static void removeStudent(Student s){
        listOfStudents.remove(s);
    }

    public static Student getStudentByID(String id){
        for (Student s : listOfStudents){
            if (s.getUserID().equals(id)){
                return s;
            }
        }
        return null;
    }
}
