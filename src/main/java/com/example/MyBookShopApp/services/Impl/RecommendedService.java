package com.example.MyBookShopApp.services.Impl;


import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class RecommendedService {

    private final BookRepository bookRepository;

    @Autowired
    private RecommendedService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void listener(String slug, String event) {
        Optional<Book> optionalBook = bookRepository.findBookBySlug(slug);
        if (optionalBook.isPresent()) {
            switch (event) {
                case "B": {// B is the number of users who bought the book,
                    optionalBook.get().getStatistics().setByNumberBAutomatically();
                    break;
                }
                case "C": {//is the number of users who have the book in their cart
                    optionalBook.get().getStatistics().setInCurtCAutomatically();
                    break;
                }
                case "K": {//is the number of users who have the book delayed.
                    optionalBook.get().getStatistics().setDelayedCountKAutomatically();
                    break;
                }
                default: {
                    Logger.getLogger(this.getClass().getSimpleName()).warning("Not found value on listener method" + this.getClass());
                    break;
                }
            }
            bookRepository.save(optionalBook.get());
        }
    }


}
