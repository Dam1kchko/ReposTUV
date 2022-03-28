package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    protected String libName;
    List<Book> booksList = new ArrayList<>();

    public Library(String libName) {
        this.libName = libName;
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
            return booksList.get(i).toString();
        } else {
            return " No book with that isbn '" + isbn_value + "' in the library " + this.libName+ " found";
        }
    }

    @Override
    public String toString() {
        return "\n Library " + this.libName +
                " has those books in store : "+ booksList;
    }
}
