package com.company;

public interface findByAttributes {
    public String findByIsbnValue(int isbn_value);
    public void findBy(String[] commands);
    public String findByTitle(String criteria);
    public String findByAuthor(String criteria);
    public void findByKeyWords(String criteria);
}
