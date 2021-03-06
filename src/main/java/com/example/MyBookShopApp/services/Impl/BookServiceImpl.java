package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.dtoModel.book.BookDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.convector.BookСonvectorImpl;
import com.example.MyBookShopApp.dtoModel.page.BooksPageDtoModel;
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
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {


    private final BookRepository bookRepository;
    private final GenreEntityRepository genreEntityRepository;
    private final BookСonvectorImpl bookConvertor;

    @Autowired
    private BookServiceImpl(BookСonvectorImpl bookConvertor, BookRepository bookRepository, GenreEntityRepository genreEntityRepository) {
        this.bookRepository = bookRepository;
        this.bookConvertor = bookConvertor;
        this.genreEntityRepository = genreEntityRepository;
    }

    @Override
    public Optional<BookDto> getBookDtoBySlug(String slug) {
        return bookRepository.findBookDtoBySlug(slug);
    }

    @Override
    public BooksPageDtoModel getPageBookBySlug(String slug, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<Book> page = bookRepository.findAllByGenreEntitiesContaining(genreEntityRepository.getBySlug(slug), nextPage);
        BooksPageDtoModel booksPageDtoModel = new BooksPageDtoModel();
        booksPageDtoModel.setTotalElement(page.getTotalElements());
        booksPageDtoModel.setCount(page.getSize());
        booksPageDtoModel.setBooks(page.getContent().stream().map(bookConvertor::convertToDto).collect(Collectors.toList()));
        bookRepository.findAllByGenreEntitiesContaining(genreEntityRepository.getBySlug(slug), nextPage).stream().map(bookConvertor::convertToDto).collect(Collectors.toList());
        return booksPageDtoModel;
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
    public List<Book> findBooksBySlugIn(String[] slugs) {
        return bookRepository.findBooksBySlugIn(slugs);
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

    public BooksPageDtoModel getPageOfSearchResultBooks(String searchWord, Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        Page<Book> page = bookRepository.findBookByTitleContaining(searchWord, nextPage);
        BooksPageDtoModel booksPageDtoModel = new BooksPageDtoModel();
        booksPageDtoModel.setCount(page.getSize());
        booksPageDtoModel.setTotalElement(page.getTotalElements());
        booksPageDtoModel.setBooks(page.stream().map(bookConvertor::convertToDto).collect(Collectors.toList()));
        return booksPageDtoModel;
    }

    public List<Book> getBooksWithPrice(Integer price) {
        return bookRepository.findBooksByPriceOldIs(price);
    }

    public List<Book> getBooksWithMaxPrice() {
        return bookRepository.getBooksWithMaxDiscount();
    }

    public Page<Book> getPageOfRecommendedBooks(Integer offset, Integer limit) {
        Pageable nextPage = PageRequest.of(offset, limit);
        return bookRepository.findAll(nextPage);
    }

    public List<BookDtoModel> getPageOfDateBooks(Integer offset, Integer limit, String fromDate, String dateTo) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        return bookRepository.findAllByPubDateIsBetween(Date.valueOf(fromDate), Date.valueOf(dateTo), nextPage).stream().map(bookConvertor::convertToDto).collect(Collectors.toList());
    }

    public List<BookDtoModel> getPageOfNameSortBooks(Integer offset, Integer limit, String nameSort) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, nameSort));
        return bookRepository.findAll(nextPage).getContent().stream().map(bookConvertor::convertToDto).collect(Collectors.toList());
    }



}


