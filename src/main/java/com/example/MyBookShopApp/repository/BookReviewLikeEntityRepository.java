package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.book.review.BookReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookReviewLikeEntityRepository extends JpaRepository<BookReviewLikeEntity, Integer>, PagingAndSortingRepository<BookReviewLikeEntity, Integer> {


}
