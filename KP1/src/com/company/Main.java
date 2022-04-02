package com.company;

import java.util.Scanner;

public class Main {
    //public static Library lib1 = new Library("Garden");
    public static UsersData users = new UsersData();
    public static boolean open_flag = false;
    public static boolean login_flag = false;
    public static boolean admin_flag = false;
    public static User currentUser;
    public static void main(String[] args){
        users.addUser("peter","123");
        String userSelected;
        do {
            userSelected = MenuData();
            String[] commands = userSelected.split(" ");
            switch (commands[0]) {
                case "open": {
                    open_flag = true;
                    break;
                }
                case "close": {
                    open_flag = false;
                    break;
                }
                case "save": {
                    if (open_flag) {
                        System.out.println("saved.");
                        break;
                    } else {
                        break;
                    }
                }
                case "saveas": {
                    if (open_flag) {
                        System.out.println("saved as.");
                        break;
                    } else {
                        break;
                    }
                }
                case "help": {
                    if (open_flag) {
                        System.out.println("helped.");
                        break;
                    } else {
                        break;
                    }
                }
                case "exit": {
                    if (open_flag) {
                        System.out.println("exiting...");
                        break;
                    } else {
                        break;
                    }
                }
                case "login": {
                    //if( open_flag ) {
                        login();
                    //} else {
                        // System.out.println("Currently not supported command.");
                    //}
                    break;
                }
            }
        } while (userSelected != "exit") ;
    }

    public static String MenuData(){
        String selection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Type command from available options: ");
        System.out.println("open <file> ===> opens <file>");
        if( open_flag ){
            System.out.println("close ===> closes currently opened file");
            System.out.println("save ===> saves the currently open file");
            System.out.println("saveas <file_path> ===> saves the currently open file in <file>");
            System.out.println("help ===> prints this information");
            System.out.println("exit ===> exists the program");
            if( login_flag ){ // if logged in
                System.out.println("logout ===> logging out of the system.");
            } else { // if logged out
                System.out.println("login ===> logging in in the system");
            }
            if( admin_flag ){

            }
        }
        System.out.println("Your selected option is: ");
        selection = sc.next();
        return selection;
    }

    public static void login(){
        String username,password;
        Scanner logging_In = new Scanner(System.in);
        System.out.println("Please enter your username:");
        username = logging_In.next();
        System.out.println("Please enter your password:");
        password = logging_In.next();
        //  System.out.println(username + " / " + password);
        User loggingUser = users.loginUser(username,password);
        if( loggingUser instanceof User ){
            currentUser = loggingUser;
            login_flag = true;
            System.out.println("The user '" + username + "' was successfully logged in.");
        }

    }
}