package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Dto.AuthorDto;
import com.example.MyBookShopApp.data.Dto.BooksPageDto;
import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@GlobalData
@Controller
public class AuthorsController {

    private final AuthorService authorService;

    @Autowired
    private AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @ModelAttribute("SLUG")
    public String slugGenre() {
        return "SLUG";
    }

    @ModelAttribute("authorsMap")
    public Map<String, List<AuthorDto>> authorsMap() {
        return authorService.getAllAuthorsMap();
    }

    @GetMapping("/authors/index")
    public String authorsPage() {
        return "/authors/index";
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @GetMapping(value = {"/authors/SLUG/"})
    public String getAuthors(@RequestParam(value = "SLUG", required = false) String SLUG, Model model) throws EmptySearchExceprtion {
        model.addAttribute("author", authorService.getAuthorBySlug(SLUG));
        model.addAttribute("SLUG", SLUG);
        model.addAttribute("authorsListBookPage", authorService.getPageBookByAuthorSlag(SLUG, 0, 5));
        return "authors/slug";
    }

    @GetMapping("/books/author/SLUG{SLUG}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "SLUG", required = false) String SLUG, Model model) throws EmptySearchExceprtion {
        if (offset < 0) return new BooksPageDto();
        model.addAttribute("author", authorService.getAuthorBySlug(SLUG));
        Page<Book> bookPage = authorService.getPageBookByAuthorSlag(SLUG, offset, limit);
        model.addAttribute("sizeSearch", bookPage.getTotalElements());
        return new BooksPageDto(bookPage.getContent());
    }


    @GetMapping(value = {"/books/authors/page/SLUG"})
    public String authorsListBook(@RequestParam(value = "SLUG", required = false) String SLUG, Model model) throws EmptySearchExceprtion {
        model.addAttribute("SLUG", SLUG);
        model.addAttribute("author", authorService.getAuthorBySlug(SLUG));
        model.addAttribute("authorsListBook", authorService.getPageBookByAuthorSlag(SLUG, 0, 20));
        return "/books/author";
    }
}
