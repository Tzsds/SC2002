package Controller.File;

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
        ReadUser.readUserWithReset();
        FileWriting.FileWriteUser(); //create a new users csv file

        // Need to reset Camps, Suggestion, Enquiries CSV file if we choose to reset
        // To be implemented in the future
    
    }

    private static void readWithoutReset(){
        ReadUser.readUserWithoutReset();
        // Need to read in Camp, Enquiries, Student
        // To be implemented in the future
    }
}
