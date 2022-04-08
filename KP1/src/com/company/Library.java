package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    protected String libName;
    List<Book> booksList = new ArrayList<>();

    public Library(String libName) {
        this.libName = libName;
        this.booksList.add(new Book("a","b","c","d",1945,
                                        "eha ko",3.4, 1232 ));
    }

    public List<Book> addBook(Book new_book) {
        booksList.add(new_book);
        return booksList;
    }

    public String getBookByIsbn(int isbn_value) {
        int i = 0; boolean flag = false;
        for (i = 0 ; i < booksList.size(); i++) {
            if (booksList.get(i).getIsbn_value() == isbn_value){
                flag = true;
                break;
            }
        }

        if( flag ) {
            return displayABook(booksList.get(i));
        } else {
            return " No book with the isbn '" + isbn_value + "' in the library " + this.libName+ " was found.";
        }
    }

    @Override
    public String toString() {
        return "\n Library " + this.libName +
                " has those books in store : \n"+ booksList;
    }

    public String displayABook(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        int isbn_value = book.getIsbn_value();
        return title + " / " + author + " / " + genre + " / " + isbn_value + "\n";
    }

    public String displayFullBook(Book book){
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        String desc = book.getDescription();
        String[] keyWords = book.getKeyWords();
        int yearOfPublishing = book.getYearOfPublishing();
        double rating = book.getRating();
        int isbn_value = book.getIsbn_value();

        return "The [" + genre +  " ]  [ " + title + " ] written by [ " + author +
                " ] was published [ " + yearOfPublishing + " ] with the short description:[ "
                + desc + " ] with following keyWords:" + keyWords + ", and was rated with '"
                + rating + "/10' and is currently in our library as [ " + isbn_value +  " ]\n";
    }

    public void displayAllBooks(){
        for( int i = 0 ; i< booksList.size(); i++) {
            System.out.println(displayABook(booksList.get(i)));
        }
    }
    public void findBy(String[] command){
        String key = command[2]; // title,author,keys
        String criteria = "";
        for( int i = 0; i< command.length; i++){
            String construct = "";
            if( i > 2) {
                construct = criteria + " " +  command[i];
            }
            criteria = construct;
        }
        switch( key ){
            case "title": {
                System.out.println( findByTitle(criteria) );
                break;
            }
            case "author": {
                findByAuthor(criteria);
                break;
            }
            case "keys":{
                findByKeyWords(criteria);
                break;
            }
        }
    }
    public String findByTitle(String criteria) {
        int i; boolean found_flag = false;
        for( i = 0 ; i< booksList.size(); i++){
            String title = booksList.get(i).getTitle();
            criteria = criteria.trim();
            if( criteria.equals(title)) {
                found_flag = true;
                break;
            }
        }
        if( found_flag ){
            return "Searching with title '" + criteria + "': \n following books were found: \n" +
                    displayFullBook(booksList.get(i));
        } else {
            return "No book with such an author was found.";
        }
    }

    public String findByAuthor(String criteria) {
        int i; boolean found_flag = false;
        for( i = 0 ; i< booksList.size(); i++){
            String author = booksList.get(i).getAuthor();
            criteria = criteria.trim();
            if( criteria.equals(author)) {
               found_flag = true;
               break;
            }
        }
        if( found_flag ){
            return "Searching with author '" + criteria + "': \n following books were found: \n" +
                    displayFullBook(booksList.get(i));
        } else {
            return "No book with such an author was found.";
        }
    }

    public String findByKeyWords(String criteria) {
        return null;
    }
}
