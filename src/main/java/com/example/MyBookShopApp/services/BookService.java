package com.example.MyBookShopApp.services;


import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.BookStoreApiWrongException;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Page<Book> getPageBookBySlug(String slug, int offset, int limit);

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) ;

    List<Book> getBooksData();

    Optional<Book> getBookBySlug(String slug);

    boolean saveBook(Book book);


    List<Book> findBooksBySlugIn(String[] slugs);

    List<Book> getBooksByAuthor(String authorName);

    List<Book> getBooksByTitle(String title) throws BookStoreApiWrongException;

    List<Book> getBooksWithPriceBetween(Integer min, Integer max);

    List<Book> getBooksWithPrice(Integer price);

    List<Book> getBooksWithMaxPrice();

    List<Book> getBestsellers();

    Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit);


    Page<Book> getPageOfDateBooks(Integer offset, Integer limit, String fromDate, String dateTo);

    Page<Book> getPageOfNameSortBooks(Integer offset, Integer limit, String nameSort);

}
