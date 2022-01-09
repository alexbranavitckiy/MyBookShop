package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class PopularController {

    private final BookServiceImpl bookServiceImpl;


    @Autowired
    public PopularController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookServiceImpl.getBooksData();
    }


    @GetMapping("/books/popular")
    public String recentBookPage() {
        return "books/popular";
    }


    @ModelAttribute("booksList")
    public List<Book> bookList(){
        return bookServiceImpl.getBooksData();
    }




}
