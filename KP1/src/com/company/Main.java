package com.company;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Main {

    public static Library lib1 = new Library("Garden");
    public static UsersData Users = new UsersData();
    public static boolean open_flag = true;
    public static boolean login_flag = true;
    public static boolean admin_flag = true;
    public static Map.Entry<User, Boolean> currentUser;

    public static void main(String[] args){
        String userSelected;
        do {
            userSelected = MenuData();
            switch (userSelected){

                //                   Supportive or in-development Cases
                case "current": {
                    out.println(UsersData.displayEntry(currentUser));
                    break;
                }
                case "books1": {
                    lib1.displayAllFullBooks();
                    break;
                }
                case "users": {
                    Users.displayAllUsers();
                    break;
                }
                case "remove book": {
                    int id;
                    Scanner newScan = new Scanner(System.in);
                    out.println("Enter id:");
                    id = newScan.nextInt();
                }
            }
            String[] commands = userSelected.split(" ");
            switch (commands[0]) {
                case "books": {
                    switch(commands[1]) {
                        case "all": {
                            lib1.displayAllBooks();
                            break;
                        }
                        case "info": {
                            int isbn = Integer.parseInt(commands[2]);
                            out.println( lib1.getBookByIsbn(isbn) );
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
                        case "add": {
                            if( admin_flag ){
                                addBook();
                            }else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                        case "remove": {
                            if( admin_flag) {

                            } else {
                                out.println("Currently not supported command.");
                            }
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
                        out.println("saved.");
                        break;
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "saveas": {
                    if (open_flag) {
                        out.println("saved as.");
                    }  else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "help": {
                    if (open_flag) {
                        out.println("helped.");
                        break;
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "exit": {
                    if (open_flag) {
                        out.println("exiting...");
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                                            // User interface Functions
                case "login": {
                    if( open_flag ) {
                        login();
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "logout": {
                    if( login_flag && open_flag ) {
                        if( currentUser.getValue() ){
                            admin_flag = false;
                        }
                        login_flag = false;
                        String currUsername = currentUser.getKey().getUsername();
                        out.println("The user: '" + currUsername + "' was logged out." );
                        currentUser = null;
                    } else {
                        out.println("Currently not supported command.");
                    }
                    break;
                }
                case "user": {
                    switch (commands[1]){
                        case "add": {
                            if( admin_flag ){
                                addUser();
                            }else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                        case "remove": {
                            if( admin_flag ){
                                Scanner scan = new Scanner(in);
                                String username;
                                out.println("Enter username for deleting.");
                                username = scan.next();
                                Users.removeUser(username);
                            } else {
                                out.println("Currently not supported command.");
                            }
                            break;
                        }
                    }
                }
            }
        } while (userSelected != "exit") ;
    }

    public static String MenuData(){
        String selection;
        Scanner sc = new Scanner(in);
        out.println("Type command from available options: ");
        out.println("open <file> ===> opens <file>");
        // Supportive
        out.println("\n ---------Supportive in-development Interface---------\n");
        out.println("current ===> display currentUser");
        out.println("users ===> display all users registered in the system.");
        out.println("books1 ===> display all books registered in the system.");
        if( open_flag ){
            out.println("\n  --------- File Operations Interface---------\n");
            out.println("close ===> closes currently opened file");
            out.println("save ===> saves the currently open file");
            out.println("saveas <file_path> ===> saves the currently open file in <file>");
            out.println("\n  --------- Support Operations Interface---------\n");
            out.println("help ===> prints this information");
            out.println("exit ===> exists the program");
            if( login_flag ) {
                out.println("\n  ---------User Interface---------\n");
                out.println("logout ===> logging out of the system.");
            } else {
                out.println(" ---------User Interface---------");
                out.println("login ===> logging in in the system");
            }
            out.println("\n  ---------Library Manipulation Interface---------\n");
            out.println("*done* books all ===> display all books");
            out.println("*done* books info <_id> ===> find book by id");
            out.println("*done* books find <option> <option_string> ===> find book by 'title,author,tag' ");
            out.println("books sort <asc,desc> ===> sort the books [asc,desc]");
            out.println("books view ===>  ? ? ? dunno what is supposed to do. ? ? ? ");
            if( admin_flag ){
                out.println("\n  ---------Admin Interface---------\n");
                out.println("*done* user add ===> add an user");
                out.println("*done* user remove ===> removes an user");
                out.println("*done w/o keywords* books add ===> add a book in the library");
                out.println("*no parameter* books remove ===> remove a book in the library \n");
            }

        }
        out.println("Your selected option is: ");
        selection = sc.nextLine();
        return selection;
    }

    public static void login(){

        String username,password;
        Scanner logging_In = new Scanner(in);

        out.println("Please enter your username:");
        username = logging_In.next();

        out.println("Please enter your password:");
        password = logging_In.next();
        Map.Entry<User, Boolean> currTry = Users.loginUser(username,password);
        User loggingUser = new User(username,password);

        if( currTry != null){
            currentUser = currTry;
            if( currTry.getValue() == true){
                admin_flag = true;
                login_flag = true;
                out.println("The user '" + username + "' was successfully logged in as admin.");
            } else {
                login_flag = true;
                out.println("The user '" + username + "' was successfully logged in as user.");
            }
        }
    }

    public static void addUser() {
        String username, password;
        Scanner scanner = new Scanner(in);

        do {
            out.println("Please enter your username:");
            username = scanner.next();
        } while ( Users.is_taken(username) );
        out.println("Please enter your password:");
        password = scanner.next();

        Users.addUser(username,password);


    }

    public static void addBook(){
        // initialise the variables and the scanner;
        Scanner scanner = new Scanner(in);
        String title,author,genre,desc,keys;
        int year,isbn;
        double rating;
        String year1;
            //●  заглавие
        out.println("Please enter book's title:");
        title = scanner.nextLine();
            //●  автор
        out.println("Please enter book's author:");
        author = scanner.nextLine();
            //●  жанр
        out.println("Please enter book's genre:");
        genre = scanner.nextLine();
            //●  кратко описание
        out.println("Please enter book's desc:");
        desc = scanner.nextLine();
            //●  година на издаване
        out.println("Please enter book's pubilishing year:");
        year = scanner.nextInt();
            //●  ключови думи
        // Currently not working keywords
        out.println("Please enter book's keywords (type each of them in format ( word1,word2 ) :");
        keys = scanner.nextLine();
            //●  рейтинг
        out.println("Please enter book's rating:");
        rating = scanner.nextDouble();
            //●  уникален номер за библиотеката
        out.println("Please enter book's isnb_value( 0 - 10000) ");
        isbn = scanner.nextInt();

        lib1.addBook(new Book(title,author,genre,desc,year,keys,rating, isbn));
    }
}