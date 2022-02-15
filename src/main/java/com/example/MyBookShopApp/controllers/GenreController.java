package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.dtoModel.BooksPageDtoError;
import com.example.MyBookShopApp.dtoModel.SearchWordDto;
import com.example.MyBookShopApp.dtoModel.TreeGenreDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.page.BooksPageDtoModel;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@GlobalData
public class GenreController {

    private final GenreServices genreServices;
    private final BookService bookService;

    @Autowired
    private GenreController(GenreServices genreServices, BookService bookService) {
        this.genreServices = genreServices;
        this.bookService = bookService;
    }

    @ModelAttribute("nameGenre")
    public String nameGenre() {
        return "Genre name";
    }

    @ModelAttribute("SLUG")
    public String slugGenre() {
        return "SLUG";
    }

    @ModelAttribute("flagButton")
    public boolean flagButton() {
        return false;
    }

    @ModelAttribute("tagList")
    public List<TreeGenreDto> getGenreList() {
        return genreServices.generationTreeGenre();
    }

    @GetMapping(value = {"/genres/SLUG"})// rewrite!!!
    public String getSearchResults(@RequestParam(value = "SLUG", required = false) String SLUG, Model model) {
        model.addAttribute("SLUG", SLUG);
        BooksPageDtoModel booksPageDtoModel = bookService.getPageBookBySlug(SLUG, 0, 5);
        if (booksPageDtoModel.getTotalElement() > 5) model.addAttribute("flagButton", true);
        model.addAttribute("flagButton", booksPageDtoModel.getTotalElement() >= (5));
        model.addAttribute("genresBooks", booksPageDtoModel.getBooks());
        model.addAttribute("nameGenre", genreServices.getGenreByName(SLUG));
        return "books/genres";
    }


    @GetMapping("/genres/page{SLUG}")
    @ResponseBody
    public BooksPageDtoModel getSLUGBook(@RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit,
                                    @PathVariable(value = "SLUG", required = false) String SLUG, Model model) {
        model.addAttribute("SLUG", SLUG);
        BooksPageDtoModel booksPageDtoModel = bookService.getPageBookBySlug(SLUG, offset, limit);
        model.addAttribute("flagButton", booksPageDtoModel.getTotalElement() >= (5 * offset));
        model.addAttribute("nameGenre", genreServices.getGenreByName(SLUG));
        return booksPageDtoModel;
    }


    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("sizeSearch")
    public String sizeList() {
        return "";
    }

    @GetMapping("/genres/index")
    public String recentBookPage() {
        return "genres/index";
    }


}
