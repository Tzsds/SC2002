package Repository;
import java.util.ArrayList;

import Entity.CampCommittee;
import Entity.Staff;
import Entity.User;

public class UserRepository {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();

    //private static ArrayList<Staff> listOfStaffs = new ArrayList<Staff>();
    //private static ArrayList<CampCommittee> listOfCampCommittees = new ArrayList<CampCommittee>();
    //private static ArrayList<Student> listOfStudents = new ArrayList<Student>();

    public static ArrayList<User> getListofUsers() {
        return listOfUsers;
    }

    public static int getSizeOfUsers() {
        return listOfUsers.size();
    }

    public static void addUser(User u) {
        listOfUsers.add(u);
    }

    public static void replaceUser(User u, int i) {
        listOfUsers.set(i, u);
    }

    public static User get(int i) {
        return listOfUsers.get(i);
    }

    public static String getUserRole(String userID) {
        for (User user : listOfUsers) {
            if (user.getUserID().equals(userID)) {
                if (user instanceof CampCommittee) {
                    return "CampCommittee";
                } else if (user instanceof Staff) {
                    return "Staff";
                } else {
                   return "Student"; // as of now default is student
                }
            }
        }
        return null; // Return null if the user is not found
    }

    public static User getUserByUserID(String userID) {
        for (int i = 0; i < getSizeOfUsers(); i++) {
            User temp = get(i);
            String tempID = temp.getUserID();
            if (userID.equals(tempID)) {
                return temp;
            }
        }
        return null;
    }
}
