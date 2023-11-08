package Controller.Account;

import Entity.User;
import Repository.UserRepository;
import UI.AccountInput;

public class LoginManager {

    private static int index; //Index of the current user in the Repository
    private static User currentUser; //Current user which is in the system

    public static void Login(){
        LoginManager.getID();
        LoginManager.checkPassword(currentUser);
        System.out.println(currentUser.getName() + " logged in!");
    }

    private static void getID(){
        User temp;
        while (true){
            String id = AccountInput.inputID();
            for (int i=0; i<UserRepository.getSizeOfUsers(); i++){
                temp = UserRepository.get(i);
                index = i;
                if (temp.getUserID().equals(id)){
                    index = i;
                    currentUser = temp;
                    return;
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
