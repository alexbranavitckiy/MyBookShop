package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.BookStoreApiWrongException;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.repository.GenreEntityRepository;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreEntityRepository genreEntityRepository;

    @Override
    public Page<Book> getPageBookBySlug(String slug, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAllByGenreEntitiesContaining(genreEntityRepository.getBySlug(slug), nextPage);
    }

    public List<Book> getBooksData() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookRepository.findBooksByAuthorFirstNameContaining(authorName);
    }

    @Override
    public Optional<Book> getBookBySlug(String slug) {


        return bookRepository.findBookBySlug(slug);
    }

    @Override
    public boolean saveBook(Book book) {
        bookRepository.save(book);
        return false;
    }

    @Override
    public List<Book> getBooksByTitle(String title) throws BookStoreApiWrongException {
        if (title.equals(" ") || title.length() < 1) {
            throw new BookStoreApiWrongException("Wrong value passed to one or more parameters");
        }
        List<Book> date = bookRepository.findBooksByTitleContaining(title);
        if (date.size() > 0) {
            return date;
        } else {
            throw new BookStoreApiWrongException("No date found with specified parameters...");
        }
    }


    public List<Book> getBooksWithPriceBetween(Integer min, Integer max) {
        return bookRepository.findBooksByPriceOldBetween(min, max);
    }

    public Page<Book> getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findBookByTitleContaining(searchWord, nextPage);
    }

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public List<Book> getBestsellers() {
        return bookRepository.getBestsellers();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfDateBooks(Integer offset, Integer limit, String fromDate, String dateTo) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        return bookRepository.findAllByPubDateIsBetween(Date.valueOf(fromDate), Date.valueOf(dateTo), nextPage);
    }

    public Page<Book> getPageOfNameSortBooksTag(Integer offset, Integer limit, String nameSort, Integer id) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findAll(nextPage);
    }

    public Page<Book> getPageOfNameSortBooks(Integer offset, Integer limit, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findAll(nextPage);
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}


