package UI;

public class Display {
    public static void welcomeMessage(){
        System.out.println("Welcome to NTU CAMS System!");
    }

    public static void exitMessage(){
        System.out.println("Thanks for using NTU CAMS System.");
        System.out.println("Exiting Program ...");
    }

    public static void LogOut(){
        System.out.println("Logging out ...");
    }

    public static void changePassword(){
        System.out.println("Initiating password change process...");
    }
    
    public static void firstTimeLogin(){
        System.out.println("Please log in again");
    }
    
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }
}
