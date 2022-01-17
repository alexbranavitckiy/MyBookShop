package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.services.BooksRatingAndPopulatityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BooksRatingAndPopulatityServiceImpl implements BooksRatingAndPopulatityService {


    @Override
    public boolean setBookEventBy(Book book) {
        return false;
    }

    @Override
    public boolean removeBookEventBy(Book book) {
        return false;
    }

    @Override
    public boolean removeBookEventDelayed(Book book) {
        return false;
    }

    @Override
    public boolean setBookEventDelayed(Book book) {
        return false;
    }

    @Override
    public boolean setBookEventCart(Book book) {
        return false;
    }

    @Override
    public boolean removeBookEventCart(Book book) {
        return false;
    }
}
