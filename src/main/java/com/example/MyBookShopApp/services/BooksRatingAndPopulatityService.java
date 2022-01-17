package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.Book;

public interface BooksRatingAndPopulatityService {

    boolean setBookEventBy(Book book);

    boolean removeBookEventBy(Book book);

    boolean removeBookEventDelayed(Book book);

    boolean setBookEventDelayed(Book book);

    boolean setBookEventCart(Book book);

    boolean removeBookEventCart(Book book);

}
