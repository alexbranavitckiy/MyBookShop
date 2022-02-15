package com.example.MyBookShopApp.dtoModel.page;

import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

import java.util.List;

public class BooksPageDtoModel {

    private Integer count;
    private List<BookDtoModel> books;
    private long totalElement;


    public BooksPageDtoModel(List<BookDtoModel> books) {
        this.books = books;
        this.count = books.size();
    }

    public BooksPageDtoModel() {

    }

    public BooksPageDtoModel(Integer count, List<BookDtoModel> books) {
        this.count = count;
        this.books = books;
    }


    public BooksPageDtoModel(Integer count, List<BookDtoModel> books, long totalElement) {
        this.count = count;
        this.books = books;
        this.totalElement = totalElement;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
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
