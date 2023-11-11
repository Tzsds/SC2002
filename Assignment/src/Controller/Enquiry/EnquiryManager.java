package Controller.Enquiry;

import java.util.List;

import Controller.Account.LoginManager;
import Controller.File.FileWriting;
import Entity.CampDetails;
import Entity.Enquiry;
import Entity.Student;
import Entity.User;
import Repository.EnquiryRepository;

import UI.InputScanner;

public class EnquiryManager {
    public static Enquiry createEnquiry(Student student) {
        System.out.println("Which Camp do you want to enquiry on?");
        Student.viewAvailableCamps();
        InputScanner.promptForString("Choose a camp(Enter Camp Name)");
        String campChoice = InputScanner.waitForUserInputString();
        // Validate if the chosen camp name exists in the available camps
        if (!CampDetails.campExists(campChoice)) {
            System.out.println("Invalid camp name. Please choose a valid camp.");
            return null; // Return null to indicate an error
        }
        String content = InputScanner.promptForString("What enquiries do you have?");
        String sender = LoginManager.getCurrentUser().getUserID();
        System.out.println("This is the content written: " + content);
        return new Enquiry(sender, content, campChoice);
    }

    public static void addEnquiry() {
        Student User = (Student) LoginManager.getCurrentUser();
        Enquiry enquiry = createEnquiry(User);
        User.getEnquiries().add(enquiry);
        EnquiryRepository.addEnquiryToRepo(enquiry);

        // Write the new enquiry to CSV
        FileWriting.FileWriteEnquiry(enquiry);
        System.out.println("Enquiry added successfully!");
    }

    public static void editEnquiry(Enquiry enquiry) {
        String content = InputScanner.promptForString("What is your editted Enquiry?: ");
        enquiry.setContent(content);
    }

    public static void viewStudentEnquiries(User currentUser) {
        List<Enquiry> studentEnquiries = EnquiryRepository.getEnquiriesBySender(currentUser.getUserID());
        for (Enquiry enquiry : studentEnquiries) {
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
        }
    }

    // View all enquiries for staff
    public static void viewAllEnquiries() {
        List<Enquiry> allEnquiries = EnquiryRepository.getListOfEnquiries();
        System.out.println("Number of enquiries: " + allEnquiries.size());
        for (Enquiry enquiry : allEnquiries) {
            System.out.println("Camp: " + enquiry.getCampName());
            System.out.println("Sender: " + enquiry.getSender());
            System.out.println("Content: " + enquiry.getContent());
            System.out.println("Status: " + enquiry.getStatus());
            System.out.println("------------------------------");
        }
    }
}
