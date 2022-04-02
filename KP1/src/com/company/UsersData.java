package com.company;

import java.util.ArrayList;

public class UsersData {
    protected ArrayList<User> listOfUsers = new ArrayList<>();

    public UsersData() {
        listOfUsers.add(new Admin("admin","123"));
    }

    public ArrayList<User> addUser(String username, String password){
        if( username == "admin"){
            System.out.println("The username is forbidden and used only by the administrators.");
            return listOfUsers;
        }
        User addedUser = new User(username,password);
        listOfUsers.add(addedUser);
        System.out.println("The user: '" + addedUser.getUsername() + "' was successfully added.");
        return listOfUsers;
    }
    public void removeUser(String username){
        boolean found_flag = false;
        for(int i = 0; i< listOfUsers.size() ; i++) {
            if( username == listOfUsers.get(i).getUsername() ){
                listOfUsers.remove(listOfUsers.get(i));
                System.out.println("The user: '" + username + "' was successfully removed.");
                found_flag = true;
            }
        }
        if( !(found_flag)) {
            System.out.println("The user: '" + username + "' was not found in the system.");
        }
    }
    public User loginUser(String username, String password){
        for(int i = 0; i < listOfUsers.size(); i++){
            if( listOfUsers.get(i).getUsername() == username && listOfUsers.get(i).getPassword() == password){
                System.out.println("The user '" + username + "' was successfully logged in as user.");
                return listOfUsers.get(i);
            }
        }
        System.out.println("Your username and/or password were invalid.");
        return null;
    }
}
