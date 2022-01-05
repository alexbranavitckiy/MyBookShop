package com.example.MyBookShopApp.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GenresController {


    @GetMapping("/genres/index")
    public String recentBookPage() {
        return "genres/index";
    }



}
