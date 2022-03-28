package com.company;

public class Main {

    public static void main(String[] args) {
        Library lib1 = new Library("Number1");
        Book book1 = new Book("Shekspir", "Romeo i Julieta",
                    "tragedy", "some shit happened", 2002 , 5, 155);
        lib1.addBook(book1);
        lib1.addBook(book1);
        lib1.addBook(book1);

        System.out.println(lib1.getBookByIsbn(145));
    }
}
