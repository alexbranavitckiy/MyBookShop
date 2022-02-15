package com.example.MyBookShopApp.dtoModel;

import com.example.MyBookShopApp.data.book.Book;

import java.util.List;

public class BooksPageDtoError {

    private Integer count;
    private List<Book> books;

    public BooksPageDtoError(List<Book> books) {
        this.books = books;
        this.count = books.size();
    }

    public BooksPageDtoError() {

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
