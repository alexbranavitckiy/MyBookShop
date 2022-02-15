package com.example.MyBookShopApp.dtoModel.page;

import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

import java.util.List;

public class RecommendedBooksPageBookDtoModel {

    private Integer count;
    private List<BookDtoModel> books;

    public RecommendedBooksPageBookDtoModel(List<BookDtoModel> books) {
        this.count = books.size();
        this.books = books;
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
