package com.example.MyBookShopApp.dtoModel.page;

import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

import java.util.List;

public class BooksPageDtoModel {

    private Integer count;
    private List<BookDtoModel> books;

    public BooksPageDtoModel(List<BookDtoModel> books) {
        this.books = books;
        this.count = books.size();
    }

    public BooksPageDtoModel() {

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<BookDtoModel> getBooks() {
        return books;
    }

    public void setBooks(List<BookDtoModel> books) {
        this.books = books;
    }
}
