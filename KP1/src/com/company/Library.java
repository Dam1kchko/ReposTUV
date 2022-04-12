package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    protected String libName;
    List<Book> booksList = new ArrayList<>();

    public List<Book> getBooksList() {
        return booksList;
    }

    public Library(String libName) {
        this.libName = libName;
        this.booksList.add(new Book("Andrew and the Shadow", "Bochko Eha" , "anime", "A rich story of a poor boy", 2012,
                "fantasy,romance", 5.4, 1002));
        this.booksList.add(new Book("Back","Connie", "comedy","A short story for a darkness sword",1945,
                                        "fantasy,comedy,action,supernatural",3.4, 1232 ));

    }
    public void addBook(Book new_book) {
        booksList.add(new_book);
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
        int yearOfPublishing = book.getYearOfPublishing();
        String words = book.getKeyWords();
        double rating = book.getRating();
        int isbn_value = book.getIsbn_value();

        return "The [" + genre +  "]  [" + title + "] written by [" + author +
                "] was published [" + yearOfPublishing + "] with the short description: \n["
                + desc + "] with following keyWords:[" + words + "], and was rated with '"
                + rating + "/10' and is currently in our library as [" + isbn_value +  "]\n";
    }
    public void displayAllBooks(){
        for( int i = 0 ; i< booksList.size(); i++) {
            System.out.println(displayABook(booksList.get(i)));
        }
    } public void displayAllFullBooks(){
        for( int i = 0 ; i< booksList.size(); i++) {
            System.out.println(displayFullBook(booksList.get(i)));
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
        // find at least 1 criteria match and display it
            // - create the supporting variables
        String collectiveString = "";
        String[] criteriaWords = criteria.split(" "); // [ fantasy, ....]
        boolean fullMatch_flag = true;
        for (Book book : booksList) {
            String currWordsString = book.getKeyWords(); // a string of "function,fantasy,romance..."
            /*
            String[] currBookWords = currWordsString.split(" "); // [function, fantasy, romance]
            for( int j = 0 ; j< criteriaWords.length; j++){
                System.out.println(" Results for " + criteriaWords[j]);
                for( int l = 0 ; l < currBookWords.length ; l++){
                    if(criteriaWords[j].equals(currBookWords[l])){
                        collectiveString = String.join(" ", displayABook(booksList.get(i)));
                        System.out.println(collectiveString);
                    }

                }
            }
            */
            for (String criteriaWord : criteriaWords) {
                if (currWordsString.toLowerCase().contains(criteriaWord.toLowerCase())) {
                    collectiveString = String.join("\n", collectiveString, displayABook(book) );
                } else {
                    fullMatch_flag = false;
                    break;
                }
            }
            if( fullMatch_flag ) {
                System.out.println(displayABook(book));
            }
        }
    }

}
