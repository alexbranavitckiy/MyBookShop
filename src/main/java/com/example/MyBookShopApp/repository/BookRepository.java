package com.example.MyBookShopApp.repository;


import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.genre.GenreEntity;
import com.example.MyBookShopApp.data.other.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, PagingAndSortingRepository<Book, Integer> {

    @Query("from Book")
    List<Book> customFindAllBooks();

    Optional<Book> findBookBySlug(String slug);

    Page<Book> findBookByTitleContaining(String bookTitle, Pageable nextPage);

    Page<Book> findBookByTagsContaining(Tag bookTitle, Pageable nextPage);

    Page<Book> findAllByGenreEntitiesContaining(GenreEntity genre, Pageable nextPage);

    Page<Book> findAllByPubDateIsBetween(Date pubDate, Date pubDate2, Pageable pageable);

    List<Book> findBooksByAuthorFirstNameContaining(String authorsFirstName);

    List<Book> findBooksByTitleContaining(String bookTitle);

    List<Book> findBooksByPriceOldBetween(Integer min, Integer max);

    List<Book> findBooksByPriceOldIs(Integer price);

    @Query("from Book where isBestseller=1")
    List<Book> getBestsellers();

    @Query(value = "SELECT * FROM books WHERE discount = (SELECT MAX(discount) FROM books", nativeQuery = true)
    List<Book> getBooksWithMaxDiscount();

    Page<Book> findAllByAuthor(Author author, Pageable nextPage);



    //  @Query(value = "SELECT * FROM books WHERE books.slug =:slug ", nativeQuery = true)
  //  Page<Book> findAllBySlugContaining(@Param("slug") String slug, Pageable nextPage);




}
