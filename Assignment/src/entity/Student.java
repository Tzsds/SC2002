package entity;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.camp.CampManager;

/**
 * This class represents a student in the system, inheriting basic user attributes.
 * @author SCSZ Group 4
 * @version 1.0
 * @since 25/11/23
 */
public class Student extends User{
    /** The list of enquiries made by the student. */
    private ArrayList<Enquiry> enquiries = new ArrayList<>();
    /** The list of camps registered by the student. */
    private ArrayList<Camp> registeredCamps = new ArrayList<Camp>();
   
    /**
     * Constructs a new Student object with the specified user ID, name, faculty, and password.
     *
     * @param userID - the user ID of the student
     * @param name - the name of the student
     * @param faculty - the faculty of the student
     * @param password - the password of the student
     */
    public Student(String userID, String name, String faculty, String password){
        super(userID, name, faculty, password);
    }
    
    /**
     * Sets the list of enquiries for the student.
     * @param listOfEnquiry - the list of enquiries to set
     */
    public void setEnquiries(ArrayList<Enquiry> listOfEnquiry){
        enquiries = listOfEnquiry;
    }

    /**
     * Sets the list of registered camps for the student.
     * @param listOfCamp - the list of registered camps to set
     */
    public void setRegisteredCamps(ArrayList<Camp> listOfCamp){
        registeredCamps = listOfCamp;
    }

    /**
     * Retrieves the list of enquiries made by the student.
     * @return the list of enquiries
     */
    public ArrayList<Enquiry> getEnquiries(){
        return enquiries;
    }

    /**
     * Retrieves the list of camps registered by the student.
     * @return the list of registered camps
     */
    public ArrayList<Camp> getRegisteredCamps(){
        return registeredCamps;
    }

    /**
     * Adds a camp to the list of registered camps for the student.
     * @param camp - the camp to add to the list
     */
    public void addRegisteredCamp(Camp camp) {
        registeredCamps.add(camp);
    }

    /**
     * Removes a camp from the list of registered camps for the student.
     * @param camp - the camp to remove from the list
     */
    public void removeRegisteredCamp(Camp camp) {
        registeredCamps.remove(camp);
    }
 
    /**
     * Displays the list of registered camps for the student.
     * If there are no registered camps, a message is displayed.
     * The method interacts with the {@code CampManager} to print camp details.
     */
    public void viewRegisteredCamps() {
        if (registeredCamps.size() == 0) {
            System.out.println("You have not registered for any camps yet!");
        }
        else {
            System.out.println("Registered Camps:");
            System.out.println("====================================");
            for (Camp camps : registeredCamps){
                if(LocalDate.now().isAfter(camps.getCampDetails().getCloseDate())){

                }
                else{
                    CampManager.printCampDetails(camps.getCampDetails());
                    System.out.println("====================================");
                }
                
            }
        }
    }
}