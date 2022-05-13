package com.company;

import java.util.*;

public class Library implements DisplayingBook, findByAttributes {

    private List<Book> booksList = new ArrayList<>();

    public List<Book> getBooksList() {
        return booksList;
    }

    public Library() {
       this.booksList.add(new Book("g","c","comedy","uha",2019,"romance,fantasy",3.4,3));
       this.booksList.add(new Book("d","j","theater","uha",1989,"fantasy,comedy",9.1,4));
       //this.booksList.add(new Book("f","b","action","uha",2010,"comedy,romance,action",4.1,2304));
       //this.booksList.add(new Book("a","z","psychological","uha",2022,"action,psychological",2.6,2304));
       //this.booksList.add(new Book("b","a","egocentric","uha",2001,"psychological,fantasy",3.1,2304));
}
    public void addBook(Book new_book) {
        booksList.add(new_book);
    }
    public void emptyTheLibrary(){
        this.booksList = new ArrayList<>();
    }
    public String findByIsbnValue(int isbn_value) {
        int i = 0; boolean flag = false;
        for (i = 0 ; i < booksList.size(); i++) {
            if (booksList.get(i).getIsbn_value() == isbn_value){
                flag = true;
                break;
            }
        }

        if( flag ) {
            return displayBook(booksList.get(i));
        } else {
            return " No book with the isbn '" + isbn_value + "' in the library was found.";
        }
    }
    @Override
    public String toString() {
        return "\n Library " +
                " has those books in store : \n"+ booksList;
    }
    //  Display ->
    public String displayBook(Book book){
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
        int yearOfPublishing = book.getYearOfPublishing();
        String words = book.keyWordsString();
        double rating = book.getRating();
        int isbn_value = book.getIsbn_value();

        return "The [" + genre +  "]  [" + title + "] written by [" + author +
                "] was published [" + yearOfPublishing + "] with the short description: \n["
                + desc + "] with following keyWords:[" + words + "], and was rated with '"
                + rating + "/10' and is currently in our library as [" + isbn_value +  "]\n";
    }
    public void displayAllBooks(){
        for( int i = 0 ; i< booksList.size(); i++) {
            System.out.println(displayBook(booksList.get(i)));
        }
    }
    public void displayAllFullBooks(){
        for( int i = 0 ; i< booksList.size(); i++) {
            System.out.println(displayFullBook(booksList.get(i)));
        }
    }
    //  Find ->
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
                System.out.println( findByAuthor(criteria) );
                break;
            }
            case "keys":{
                System.out.println("The following books were found:");
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
            return "Searching with title....'" + criteria + "'\n following books were found: \n" +
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
            return "Searching with author....'" + criteria + "' \n following books were found: \n" +
                    displayFullBook(booksList.get(i));
        } else {
            return "No book with such an author was found.";
        }
    }
    public void findByKeyWords(String criteria) {
        String[] criteriaWords = criteria.trim().split(" ");
        int matchCounter = 0;
        Set<Book> matchedBooks = new HashSet<>();
        for( Book book : booksList) {
            String[] currWords = book.getKeyWords();
            for( String currCriteria : criteriaWords) {
                for( String currWord : currWords){
                    if( currCriteria.equals(currWord) ){
                        matchCounter++;
                        matchedBooks.add(book);
                        break;
                    } else {
                        continue;
                    }
                }
            }
            if( matchCounter == criteriaWords.length ){
                for( Book currBook : matchedBooks ){
                    String result =  displayFullBook( currBook ) ;
                    System.out.println( result );
                }
            }
            matchCounter = 0;
            matchedBooks.clear();
        }
    }

}
