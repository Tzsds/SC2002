import java.util.ArrayList;

public class UserRepository {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();

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

}
