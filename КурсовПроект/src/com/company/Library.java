package com.company;

import java.util.*;

public class Library implements DisplayingBook, findByAttributes {

    private List<Book> booksList = new ArrayList<>();

    public List<Book> getBooksList() {
        return booksList;
    }

    public Library() {
       //this.booksList.add(new Book("The SnowWhite","c","comedy","uha",2019,"romance,fantasy",3.4,1));
       //this.booksList.add(new Book("BlackWhite","j","theater","uha",1989,"fantasy,comedy",9.1,4));
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
        return title + " -> " + author + " -> " + genre + " -> " + isbn_value ;
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

        return title + " -> " + author + " -> " +  genre + " -> " + desc  + " -> " + yearOfPublishing + " -> " + words + " -> " + rating  + " -> " + isbn_value;
    }
    public void displayAllBooks(){
        if( booksList.size() == 0 ){
            System.out.println("The library is empty");
        } else {
            System.out.println("The following format is used here: " +
                    "\n Title -> Author -> Genre -> ISBN_value :-> ");
            for( int i = 0 ; i< booksList.size(); i++) {
                System.out.println(displayBook(booksList.get(i)));
            }
        }
    }
    public void displayAllFullBooks(){
        if( booksList.size() == 0 ){
            System.out.println("The library is empty");
        } else {
            System.out.println("The following format is used here: " +
                    "\n Title -> Author -> Genre -> Description -> Year -> keyWords -> rating -> isbn_value:-> ");
            for( int i = 0 ; i< booksList.size(); i++) {
                System.out.println(displayFullBook(booksList.get(i)));
            }
        }
    }
    public void displayIsbnNameBooks(){
        if( booksList.size() == 0 ){
            System.out.println("The library is empty");
        } else {
            for (int i = 0 ; i < booksList.size(); i++){
                System.out.println("ISBN:" + booksList.get(i).getIsbn_value() +
                        " & TITLE: " + booksList.get(i).getTitle());
            }
        }
    };
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
                System.out.println("The following books were found:");
                findByTitle(criteria);
                break;
            }
            case "author": {
                System.out.println("The following books were found:");
                findByAuthor(criteria);
                break;
            }
            case "keys":{
                System.out.println("The following books were found:");
                findByKeyWords(criteria);
                break;
            }
        }
    }
    public void findByTitle(String criteria) {
        int i; boolean found_flag = false;
        for( i = 0 ; i< booksList.size(); i++){
            String title = booksList.get(i).getTitle();
            criteria = criteria.trim();
            if( title.contains(criteria)) {
                found_flag = true;
                break;
            }
        }
        if( found_flag ){
            System.out.println("Searching with title....'" + criteria + "'\n following books were found: \n" +
                    displayFullBook(booksList.get(i)));
        } else {
            System.out.println("No book with such an author was found.");
        }
    }
    public void findByAuthor(String criteria) {
        int i; boolean found_flag = false;
        for( i = 0 ; i< booksList.size(); i++){
            String author = booksList.get(i).getAuthor();
            criteria = criteria.trim();
            if( author.contains(criteria) ) {
               found_flag = true;
               break;
            }
        }
        if( found_flag ){
            System.out.println( "Searching with author '" + criteria + "...' \n following books were found: \n" +
                    displayFullBook(booksList.get(i))) ;
        } else {
            System.out.println( "No book with such an author was found." );
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
    public String findByIsbnValue(int isbn_value) {
        int i = 0; boolean flag = false;
        for (i = 0 ; i < booksList.size(); i++) {
            if (booksList.get(i).getIsbn_value() == isbn_value){
                flag = true;
                break;
            }
        }

        if( flag ) {
            System.out.println("The following format is used here: " +
                    "\n Title -> Author -> Genre -> ISBN_value :-> ");
            return displayBook(booksList.get(i));
        } else {
            return " No book with the isbn '" + isbn_value + "' in the library was found.";
        }
    }

    public void deleteBookByIsbn(int target_isbn){
        for (int i = 0 ; i < booksList.size(); i++){
            Book currBook = booksList.get(i);
            if( target_isbn == currBook.getIsbn_value() ){
                booksList.remove(currBook);
                System.out.println("The book '" + currBook.getTitle() + "' and isbn number " + currBook.getIsbn_value() + " was successfully removed.");
            }
        }
    }

}
