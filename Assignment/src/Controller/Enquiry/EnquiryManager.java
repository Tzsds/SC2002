package Controller.Enquiry;

import Controller.Account.LoginManager;
import Controller.File.FileWriting;
import Entity.Enquiry;
import Entity.Student;
import Repository.EnquiryRepository;

import UI.InputScanner;

public class EnquiryManager {
    public static Enquiry createEnquiry() {
        InputScanner.promptForString("What enquiries do you have?");
        String content = InputScanner.waitForUserInput();
        String sender = LoginManager.getCurrentUser().getUserID();
        System.out.println("This is the content written: " + content);
        return new Enquiry(sender, content);
    }

    public static void addEnquiry() {
        Student User = (Student) LoginManager.getCurrentUser();
        Enquiry enquiry = createEnquiry();
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
}
