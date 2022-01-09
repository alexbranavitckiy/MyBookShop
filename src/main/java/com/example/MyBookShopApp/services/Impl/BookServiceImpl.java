package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {




    @Autowired
    private BookRepository bookRepository;


    public List<Book> getBooksData() {
        return (List<Book>) bookRepository.findAll();
    }


}
