package com.company;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Library lib1 = new Library("Garden");
    public static UsersData Users = new UsersData();
    public static boolean open_flag = false;
    public static boolean login_flag = false;
    public static boolean admin_flag = false;
    public static Map.Entry<User, Boolean> currentUser;

    public static void main(String[] args){
        String userSelected;
        do {
            userSelected = MenuData();
            String[] commands = userSelected.split(" ");
            switch (commands[0]) {
                //                   Supportive or in-development Cases
                case "current": {
                    System.out.println(UsersData.displayEntry(currentUser));
                    break;
                }
                case "books": {
                    switch(commands[1]) {
                        case "all": {
                            lib1.displayAllBooks();
                            break;
                        }
                        case "info": {
                            int isbn = Integer.parseInt(commands[2]);
                            System.out.println( lib1.getBookByIsbn(isbn) );
                            break;
                        }
                        case "find": {
                            lib1.findBy(commands);
                            break;
                        }
                        case "sort": {
                            break;
                        }
                        case "view": {
                            break;
                        }
                    }
                }
                //                    File Functions
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
                                            // User interface Functions
                case "login": {
                    if( open_flag ) {
                        login();
                    } else {
                        System.out.println("Currently not supported command.");
                    }
                    break;
                }
                case "logout": {
                    if( open_flag ) {
                        login();
                    } else {
                        System.out.println("Currently not supported command.");
                    }
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
        // Supportive
        System.out.println(" ---------Supportive in-development Interface---------");
        System.out.println("current ===> display currentUser");
        if( open_flag ){
            System.out.println("close ===> closes currently opened file");
            System.out.println("save ===> saves the currently open file");
            System.out.println("saveas <file_path> ===> saves the currently open file in <file>");
            System.out.println("help ===> prints this information");
            System.out.println("exit ===> exists the program");
            if( login_flag ) {
                System.out.println(" ---------User Interface---------");
                System.out.println("logout ===> logging out of the system.");
            } else {
                System.out.println(" ---------User Interface---------");
                System.out.println("login ===> logging in in the system");
            }
            System.out.println(" ---------Library Manipulation Interface---------");
            System.out.println("books all ===> display all books");
            System.out.println("books info <_id> ===> find book by id");
            System.out.println("books find <option> <option_string> ===> find book by 'title,author,tag' ");
            System.out.println("books sort <asc,desc> ===> sort the books [asc,desc]");
            System.out.println("books view ===>  ? ? ? dunno what is supposed to do. ? ? ? ");
            if( admin_flag ){
                System.out.println(" ---------Admin Interface---------");
                System.out.println("user add ===> add an user");
                System.out.println("user remove ===> removes an user");
                System.out.println("books add ===> add a book in the library");
                System.out.println("books remove ===> remove a book in the library");
            }

        }
        System.out.println("Your selected option is: ");
        selection = sc.nextLine();
        return selection;
    }

    public static void login(){

        String username,password;
        Scanner logging_In = new Scanner(System.in);

        System.out.println("Please enter your username:");
        username = logging_In.next();

        System.out.println("Please enter your password:");
        password = logging_In.next();

        Map.Entry<User, Boolean> currTry = Users.loginUser(username,password);
        User loggingUser = new User(username,password);

        if( currTry != null){
            currentUser = currTry;
            if( currTry.getValue() == true){
                admin_flag = true;
                login_flag = true;
                System.out.println("The user '" + username + "' was successfully logged in as admin.");
            } else {
                login_flag = true;
                System.out.println("The user '" + username + "' was successfully logged in as user.");
            }
        }
    }
}