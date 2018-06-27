package fr.Users;

import java.util.ArrayList;

public class Userlist {

    private static Userlist userList;
    private ArrayList<User> userCatalog;


    private Userlist() {
        userCatalog = new ArrayList<>(20);
    }

    public static synchronized Userlist getInstance() {
        if (userList == null) {
            userList = new Userlist();
        }
        return userList;
    }

    public ArrayList<User> getUserCatalog() {
        return userCatalog;
    }
}
