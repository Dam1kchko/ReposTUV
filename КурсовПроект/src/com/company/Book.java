package com.company;

import java.util.Arrays;

public class Book {
    //●  заглавие
    private String title;
    //●  автор
    private String author;
    //●  жанр
    private String genre;
    //●  кратко описание
    private String description;
    //●  година на издаване
    private int yearOfPublishing;
    //●  ключови думи
    private String[] keyWords;
    //●  рейтинг
    private double rating;
    //●  уникален номер за библиотеката
    private int isbn_value;

    // Book Constructor
    public Book(String title, String author, String genre, String description,
                int yearOfPublishing, String keyWords, double rating, int isbn_value) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.yearOfPublishing = yearOfPublishing;
        this.keyWords = keyWords.split(",");
        this.rating = rating;
        this.isbn_value = isbn_value;
    }

    public String displayWords(){
        String fullWords = "", currWord = "";
        for( int i = 0 ; i < Arrays.stream(keyWords).count() ; i++){
            currWord = keyWords[i];
            fullWords = String.join(" ", fullWords, currWord);
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


    public String[] getKeyWords(){
        return this.keyWords;
    }


    public double getRating() {
        return rating;
    }

    public int getIsbn_value() {
        return isbn_value;
    }



    public String keyWordsString() {
        String keys = "";
        int i = 0;
        do {
            keys += this.keyWords[i] + " ";
            i++;
        } while (i < keyWords.length);
        return keys;
    }

    public String yearForFile(){
        String year = String.valueOf(this.yearOfPublishing);
        return year;
    }

    public String ratingForFile(){
        String year = String.valueOf(this.rating);
        return year;
    }

    public String isbnForFile(){
        String isbn = String.valueOf(this.isbn_value);
        return isbn;
    }

    public String keyWordsForFile(){
        return this.displayWords();
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

