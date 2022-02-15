package com.example.MyBookShopApp.dtoModel.other;

import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;

public class StatisticsDtoModel {


    private String slug;

    private BookDtoModel book;

    private Double averageValue;

    private Integer numberAverage;

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public BookDtoModel getBook() {
        return book;
    }

    public void setBook(BookDtoModel book) {
        this.book = book;
    }

    public Double getAverageValue() {
        return averageValue;
    }

    public void setAverageValue(Double averageValue) {
        this.averageValue = averageValue;
    }

    public Integer getNumberAverage() {
        return numberAverage;
    }

    public void setNumberAverage(Integer numberAverage) {
        this.numberAverage = numberAverage;
    }
}
