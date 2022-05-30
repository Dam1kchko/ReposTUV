package com.company;

public interface findByAttributes {
    public String findByIsbnValue(int isbn_value);
    public void findBy(String[] commands);
    public void findByTitle(String criteria);
    public void findByAuthor(String criteria);
    public void findByKeyWords(String criteria);
}
