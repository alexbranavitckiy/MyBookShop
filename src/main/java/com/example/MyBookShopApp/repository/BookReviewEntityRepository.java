package com.example.MyBookShopApp.repository;
import com.example.MyBookShopApp.data.book.review.BookReviewEntity;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface BookReviewEntityRepository  extends JpaRepository<BookReviewEntity, Integer>, PagingAndSortingRepository<BookReviewEntity, Integer> {
}
