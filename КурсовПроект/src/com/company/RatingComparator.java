package com.company;

import java.util.Comparator;

public class RatingComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        String rating1 = String.valueOf(o1.getRating());
        String rating2 = String.valueOf(o2.getRating());
        return rating1.compareTo(rating2);
    }
}
