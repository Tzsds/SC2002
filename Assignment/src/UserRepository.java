import java.util.ArrayList;

public class UserRepository {
    private static ArrayList<User> listOfUsers = new ArrayList<User>();
    
    public static ArrayList<User> getListofUsers(){
        return listOfUsers;
    }

    public static int getSizeOfUsers(){
        return listOfUsers.size();
    }

    public static void addUser(User u){
        listOfUsers.add(u);
    }

    public static void replaceUser(User u, int i){
        listOfUsers.set(i, u);
    }

    public static User get(int i){
        return listOfUsers.get(i);
    }

}
