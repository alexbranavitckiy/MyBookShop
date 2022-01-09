package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class BookController {

    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public BookController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }

    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookServiceImpl.getBooksData();
    }

    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return bookServiceImpl.getBooksData();
    }

    @GetMapping("{/documents/index")
    public String documentsPage() {

        System.out.println("f");
        return "documents/index";
    }


    @GetMapping("/cart")
    public String cartPage() {
        return "cart";
    }

    @GetMapping("/my")
    public String myPage() {
        return "my";
    }

    @GetMapping("/signin")
    public String signupPage() {
        return "signin";
    }


    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @GetMapping("/faq")
    public String faqPage() {
        return "faq";
    }

    @GetMapping("/contacts")
    public String contactsPage() {
        return "contacts";
    }



    @GetMapping("search/")
    public String searchPage() {
        return "search/index";
    }


    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/postponed")
    public String postponedPage() {
        return "postponed";
    }


    @GetMapping("/")
    public String mainPage() {
        return "index";
    }

}
