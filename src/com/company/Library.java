package com.company;

import java.util.ArrayList;
import java.util.List;

public class Library {
    List<Book> booksList = new ArrayList<>();


    public List<Book> addBook(Book new_book) {
        booksList.add(new_book);
        return booksList;
    }

    public Book getBookByIsbn(int isbn_value) {
        int i;
        for ( i = 0 ; i < booksList.size(); i++) {
            if (booksList.get(i).getIsbn_value() == isbn_value){
                break;
            }
        }
        return booksList.get(i);
    }
}
