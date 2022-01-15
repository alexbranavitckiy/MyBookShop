package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Author;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.services.Impl.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Map;

@Controller
public class AuthorsController {

    @Autowired
    private  AuthorServiceImpl authorServiceImpl;



    @ModelAttribute("authorsMap")
    public Map<String,List<Author>> authorsMap(){
        return authorServiceImpl.getAllAuthorsMap();
    }

    @GetMapping("/authors/index")
    public String authorsPage(){
        return "/authors/index";
    }


    @GetMapping("/books/authors")
    public String authorsBookPage() {
        return "books/author";
    }


    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }


}
