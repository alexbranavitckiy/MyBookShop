package com.example.MyBookShopApp.services;


import com.example.MyBookShopApp.data.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Date;
import java.util.List;

public interface BookService {

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit);

    List<Book> getBooksData();

    List<Book> getBooksByAuthor(String authorName);

    List<Book> getBooksByTitle(String title);

    List<Book> getBooksWithPriceBetween(Integer min, Integer max);

    List<Book> getBooksWithPrice(Integer price);

    List<Book> getBooksWithMaxPrice();

    List<Book> getBestsellers();

    Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit);


    Page<Book> getPageOfDateBooks(Integer offset, Integer limit,String fromDate, String dateTo);

    Page<Book> getPageOfNameSortBooks(Integer offset, Integer limit, String nameSort);

}
