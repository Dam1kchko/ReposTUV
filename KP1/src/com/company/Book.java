package com.company;

import java.util.Arrays;

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
    protected String[] keyWords;
    //●  рейтинг
    protected double rating;
    //●  уникален номер за библиотеката
    protected int isbn_value;

    // Book Constructor

    public Book(String author, String title, String genre, String description, int yearOfPublishing, String keyWords, double rating, int isbn_value) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.yearOfPublishing = yearOfPublishing;
        this.keyWords = keyWords.split(" ");
        this.rating = rating;
        this.isbn_value = isbn_value;
    }

    public String displayWords(){
        String fullWords = "", currWord;
        for( int i = 0 ; i < Arrays.stream(keyWords).count() ; i++){
            currWord = keyWords[i];
            fullWords += "," + currWord;
        }
        return fullWords;
    }

    public int getIsbn_value() {
        return isbn_value;
    }

    @Override
    public String toString() {
        return "Book: " +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                ", keyWords=" + displayWords() +
                ", rating=" + rating +
                ", isbn_value=" + isbn_value +
                "}\n ";
    }
}

