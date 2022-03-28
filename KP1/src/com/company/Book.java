package com.company;

import java.util.ArrayList;

public class Book {
    //●  автор
    protected String author;
    //●  заглавие
    protected String title;
    //●  жанр
    protected String genre;
    //●  кратко описание
    protected String description;
    //●  година на издаване
    protected int yearOfPublishing;
    //●  ключови думи
    protected ArrayList<String> keyWords;
    //●  рейтинг
    protected double rating;
    //●  уникален номер за библиотеката
    protected int isbn_value;

    // Book Constructor

    public Book(String author,String title, String genre, String description, int yearOfPublishing, double rating, int isbn_value) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.yearOfPublishing = yearOfPublishing;
        this.rating = rating;
        this.isbn_value = isbn_value;
    }

    public int getIsbn_value() {
        return isbn_value;
    }

    @Override
    public String toString() {
        return "\n Title:" + this.title + ". Author: " + this.author + ", Genre: " + this.genre + ", Isbn: " + this.isbn_value;
    }
}

