package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static Library lib1 = new Library("Gradina");
    public static String fileOpened;
    private static boolean openFlag = false;
    public static void main(String[] args) throws IOException {
        String userSelected;
        do {
            userSelected = MenuData();
            switch (userSelected){
                case "open" : {
                    if( openFlag == false ){
                        ReadFromFile();
                        openFlag = true;
                    } else {
                        System.out.println("This file was already opened.");
                    }
                    break;
                }
                case "load": {
                    System.out.println(lib1);
                    break;
                }
                case "close": {
                    if( openFlag ){
                        System.out.println("The current file was closed.");
                        openFlag = false;
                    } else {
                        System.out.println("There's no opened file.");
                    }
                    break;
                }
            }
        } while (userSelected != "exit");
    }

    public static String MenuData(){
        String selection;
        Scanner sc = new Scanner(System.in);
        System.out.println("Type command : ");
        System.out.println("open <file name> => open file and put its book data");
        System.out.println("close => Close current file.");
        System.out.println("load => Load All Books in:");
        System.out.println("exit => Exit the program");
        if( openFlag ){
            System.out.println("book command => book-related commands, currently in process");
        }
        System.out.println("Your selected option is: ");
        selection = sc.next();
        return selection;
    }

    public static void ReadFromFile() throws IOException {
        String filePath;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter file path");
        filePath = input.nextLine();
        File file = new File(filePath);
        if( !(file.exists()) ) {
            System.out.println("Currently entered path doesn't exist.");
            System.out.println("Currenctly creating new file....\n The new file " + filePath + " was created.");
            file.createNewFile();
        } else {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while ( scanner.hasNext() ){
                scanner.useDelimiter("\\|");
                String author = scanner.next();
                String title = scanner.next();
                String genre = scanner.next();
                String description = scanner.next();
                int publishingYear = scanner.nextInt();
                String keyWords = scanner.next();
                double rating = scanner.nextDouble();
                int isbn_value = scanner.nextInt();
                lib1.addBook(new Book(author,title,genre,description,publishingYear,keyWords,rating,isbn_value));
            }
        }
    }
}