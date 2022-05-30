package com.company;

import java.util.Comparator;

public class YearComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        String year1 = String.valueOf(o1.getYearOfPublishing());
        String year2 = String.valueOf(o2.getYearOfPublishing());
        return year1.compareTo(year2);
    }
}
