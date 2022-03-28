package com.company;

public class Main {

    public static void main(String[] args) {
        Library lib1 = new Library();
        Book book1 = new Book("String author", "String title",
                    "String genre", "String description", 2002 , 5, 145);
        lib1.addBook(book1);
        Book search = lib1.getBookByIsbn(145);
        System.out.println(search.getIsbn_value());

    }
}
