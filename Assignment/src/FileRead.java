import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileRead {

    public ArrayList<User> readWithReset(){
        ArrayList <User> entity = new ArrayList<User>();
        String studentCSV = "database/student_list.csv";
        String staffCSV = "database/staff_list.csv";

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
                    entity.add(new Student(userID, name, faculty, "password"));
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
                    entity.add(new Staff(userID, name, faculty, "password"));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return entity;
    
    }

    public ArrayList<User> readWithoutReset(){
        ArrayList <User> entity = new ArrayList<User>();
        String allUserCSV = "database/users.csv";

        //Reading in the CSV that has all the updated passwords
        try (BufferedReader br = new BufferedReader(new FileReader(allUserCSV))){
            String line;
            br.readLine();
            while ((line = br.readLine()) != null){
                String [] words = line.split(",");
                if (words.length >= 4){
                    String name = words[0].trim();
                    String faculty = words[1].trim();
                    String userID = words[2].trim();
                    String password = words[3].trim();
                    entity.add(new Staff(userID, name, faculty, password));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return entity;

    }
}
