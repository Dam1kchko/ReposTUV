package com.company;

import java.util.Arrays;

public class Book {
    //●  заглавие
    protected String title;
    //●  автор
    protected String author;
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
        this.title = title;
        this.author = author;
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

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public String[] getKeyWords() {
        return keyWords;
    }

    public double getRating() {
        return rating;
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

