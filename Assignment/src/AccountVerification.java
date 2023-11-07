import java.util.ArrayList;


public class AccountVerification {

    private static int index;

    public static User Login(ArrayList<User> users){
        User temp = AccountVerification.getID(users);
        AccountVerification.checkPassword(temp);
        return temp;
    }

    private static User getID(ArrayList<User> users){
        User temp;
        while (true){
            String id = AccountInput.inputID();
            for (int i=0; i<users.size(); i++){
                temp = users.get(i);
                index = i;
                if (temp.getUserID().equals(id)){
                    return temp;
                }
            }
            System.out.println("Invalid User ID!");
        }
    }

    private static void checkPassword(User person){
        while (true){
            String pw = AccountInput.inputPW();
            if (pw.equals(person.getPassword())){
                return;
            }
            else{
                System.out.println("Wrong password entered!");
            }
        }
    }

    public static int getIndex(){
        return index;
    }
}
