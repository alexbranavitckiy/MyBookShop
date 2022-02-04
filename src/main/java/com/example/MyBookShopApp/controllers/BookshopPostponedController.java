package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;


@GlobalData
@Controller
public class BookshopPostponedController {

    private final BookRepository bookRepository;

    @Autowired
    public BookshopPostponedController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }




}
