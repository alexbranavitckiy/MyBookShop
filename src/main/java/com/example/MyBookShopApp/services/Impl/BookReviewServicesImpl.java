package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.BookReviewEntityRepository;
import com.example.MyBookShopApp.repository.BookReviewLikeEntityRepository;
import com.example.MyBookShopApp.services.BookReviewServices;
import org.hibernate.type.LocalDateTimeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookReviewServicesImpl implements BookReviewServices {

    private final BookReviewEntityRepository bookReviewEntityRepository;
    private final BookRepository bookRepository;
    private final BookReviewLikeEntityRepository bookReviewLikeEntityRepository;


    @Autowired
    private BookReviewServicesImpl(BookReviewLikeEntityRepository bookReviewLikeEntityRepository, BookRepository bookRepository, BookReviewEntityRepository bookReviewEntityRepository) {
        this.bookReviewEntityRepository = bookReviewEntityRepository;
        this.bookReviewLikeEntityRepository = bookReviewLikeEntityRepository;
        this.bookRepository = bookRepository;
    }


    public void getSumLikeReview(int IdReview) {
        BookReviewEntity bookReviewEntity = this.bookReviewEntityRepository.getOne(IdReview);
        int like = bookReviewEntity.getBookReviewLikeEntity().stream().filter(x -> x.getValue() > 0).mapToInt(BookReviewLikeEntity::getValue).sum();
        int notLike = bookReviewEntity.getBookReviewLikeEntity().stream().filter(x -> x.getValue() < 0).mapToInt(BookReviewLikeEntity::getValue).sum();
        System.out.println(like);
        System.out.println(notLike);
    }


    @Override
    public boolean saveReview(String text, String slug) {
        BookReviewEntity bookReviewEntity = new BookReviewEntity();
        Book book = this.bookRepository.findBookBySlug(slug).get();
        bookReviewEntity.setText(text);
        bookReviewEntity.setBook(book);
        bookReviewEntity.setTime(LocalDateTime.now());
        BookReviewLikeEntity bookReviewLikeEntity = new BookReviewLikeEntity();
        bookReviewLikeEntity.setValue((short) 0);
        bookReviewLikeEntity.setTime(LocalDateTime.now());
        bookReviewLikeEntity.setBookReviewEntity(bookReviewEntity);
        bookReviewEntityRepository.save(bookReviewEntity);
        bookReviewLikeEntityRepository.save(bookReviewLikeEntity);
        return false;
    }


    @Override
    public boolean saveLikeReview(int value, String reviewId) {//reviewId id отзыва
        BookReviewEntity bookReviewEntity = bookReviewEntityRepository.getOne(Integer.parseInt(reviewId));
        BookReviewLikeEntity bookReviewLikeEntity = new BookReviewLikeEntity();
        bookReviewLikeEntity.setValue((short) value);
        bookReviewLikeEntity.setTime(LocalDateTime.now());
        bookReviewLikeEntity.setBookReviewEntity(bookReviewEntity);
        bookReviewLikeEntityRepository.save(bookReviewLikeEntity);
        getSumLikeReview(Integer.parseInt(reviewId));
        return false;
    }

}
