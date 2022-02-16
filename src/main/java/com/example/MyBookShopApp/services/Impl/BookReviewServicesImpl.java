package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.BookReviewEntityRepository;
import com.example.MyBookShopApp.services.BookReviewServices;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookReviewServicesImpl implements BookReviewServices {

    private BookReviewEntityRepository bookReviewEntityRepository;
    private BookRepository bookRepository;


    @Autowired
    private BookReviewServicesImpl(BookRepository bookRepository, BookReviewEntityRepository bookReviewEntityRepository) {
        this.bookReviewEntityRepository = bookReviewEntityRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public boolean saveReview(String text, String slug) {
        BookReviewEntity bookReviewEntity = new BookReviewEntity();
        Book book= this.bookRepository.findBookBySlug(slug).get();
        bookReviewEntity.setText(text);
        bookReviewEntity.setBook(book);
        bookReviewEntity.setTime(LocalDateTime.now());
        bookReviewEntityRepository.save(bookReviewEntity);
        return false;
    }

    
    @Override
    public boolean saveLikeReview(String text, String slug,int value) {
        BookReviewEntity bookReviewEntity = new BookReviewEntity();
        Book book= this.bookRepository.findBookBySlug(slug).get();
        bookReviewEntity.setText(text);
        bookReviewEntity.setBook(book);
        bookReviewEntity.setTime(LocalDateTime.now());
        bookReviewEntityRepository.save(bookReviewEntity);
        return false;
    }

}
