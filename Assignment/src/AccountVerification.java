//import java.util.ArrayList;


public class AccountVerification {

    private static int index;
    private static User currentUser;

    public static void Login(){
        User temp = AccountVerification.getID();
        AccountVerification.checkPassword(temp);
        System.out.println(currentUser.getName() + " logged in!");
        //return temp;
    }

    private static User getID(){
        User temp;
        while (true){
            String id = AccountInput.inputID();
            for (int i=0; i<UserRepository.getSizeOfUsers(); i++){
                temp = UserRepository.get(i);
                index = i;
                if (temp.getUserID().equals(id)){
                    index = i;
                    currentUser = temp;
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

    public static User getCurrentUser(){
        return currentUser;
    }
}
