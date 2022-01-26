package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.BooksPageDto;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.Dto.TreeGenreDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.Impl.GenreServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
public class GenreController {


    @Autowired
    private GenreServicesImpl genreServices;

    @Autowired
    private BookService bookService;

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
        List<TreeGenreDto> list2 = this.genreServices.getAllGenreDtoList();
        TreeGenreDto node = new TreeGenreDto();
        if (list2.size() > 0) {
            for (TreeGenreDto test : list2) {
                TreeGenreDto tn = new TreeGenreDto(test.getId(), test.getParentId(), test.getSlug(), test.getName(), test.getSizeBook());
                node.add(tn);
            }
        }
        node.getChildren().sort(((Comparator<TreeGenreDto>) (o1, o2) -> (o1.getSizeBook() - o2.getSizeBook())).reversed());
        return node.getChildren();
    }

    @GetMapping(value = {"/genres/SLUG"})
    public String getSearchResults(@RequestParam(value = "SLUG", required = false) String SLUG, Model model) {
        model.addAttribute("SLUG", SLUG);
        Page<Book> page = bookService.getPageBookBySlug(SLUG, 0, 5);
        if (page.getTotalElements() > 5) model.addAttribute("flagButton", true);
        model.addAttribute("flagButton", page.getTotalElements() >= (5));
        model.addAttribute("genresBooks", page.getContent());
        model.addAttribute("nameGenre", genreServices.getGenreByName(SLUG));
        return "books/genres";
    }


    @GetMapping("/genres/page{SLUG}")
    @ResponseBody
    public BooksPageDto getSLUGBook(@RequestParam("offset") Integer offset,
                                    @RequestParam("limit") Integer limit,
                                    @PathVariable(value = "SLUG", required = false) String SLUG, Model model) {
        model.addAttribute("SLUG", SLUG);
        Page<Book> page = bookService.getPageBookBySlug(SLUG, offset, limit);
        model.addAttribute("flagButton", page.getTotalElements() >= (5 * offset));
        model.addAttribute("nameGenre", genreServices.getGenreByName(SLUG));
        return new BooksPageDto(page.getContent());
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
