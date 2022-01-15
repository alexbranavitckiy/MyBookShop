package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.Dto.BooksPageDto;
import com.example.MyBookShopApp.data.Dto.RecommendedBooksPageDto;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MovePageController {


    private final BookServiceImpl bookServiceImpl;

    @Autowired
    public MovePageController(BookServiceImpl bookServiceImpl) {
        this.bookServiceImpl = bookServiceImpl;
    }


    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks() {
        return bookServiceImpl.getPageOfNameSortBooks(0, 6, "priceOld").getContent();
    }

    @ModelAttribute("resentBooks")
    public List<Book> bestsellersBooks() {
        return bookServiceImpl.getPageOfNameSortBooks(0, 6, "pubDate").getContent();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookServiceImpl.getPageOfNameSortBooks(0, 10, "isBestseller").getContent();
    }


    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPageRecommended(@RequestParam("offset") Integer offset,
                                                           @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(bookServiceImpl.getPageOfNameSortBooks(offset, limit, "priceOld").getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPagePopular(@RequestParam("offset") Integer offset,
                                                       @RequestParam("limit") Integer limit, Model model) {
        System.out.println("  @GetMapping(\"/books/popular\")");
    //  model.addAttribute("popularBooks", new RecommendedBooksPageDto(bookServiceImpl.getPageOfNameSortBooks(offset, limit, "isBestseller").getContent()));
        return new RecommendedBooksPageDto(bookServiceImpl.getPageOfNameSortBooks(offset, limit, "isBestseller").getContent());
    }


    @GetMapping(value = {"/books/recent"})
    @ResponseBody
    public RecommendedBooksPageDto getBooksRecent(@RequestParam("offset") Integer offset,
                                                  @RequestParam("limit") Integer limit,
                                                  @RequestParam(required = false, value = "from") String from,
                                                  @RequestParam(required = false, value = "to") String to) {
        if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
            return new RecommendedBooksPageDto(bookServiceImpl.getPageOfDateBooks(offset, limit,
                    from.substring(6, 10) + "-" + from.substring(3, 5) + "-" + from.substring(0, 2),
                    to.substring(6, 10) + "-" + to.substring(3, 5) + "-" + to.substring(0, 2)).getContent());
        }
        return new RecommendedBooksPageDto(bookServiceImpl.getPageOfNameSortBooks(offset, limit, "pubDate").getContent());
    }


    @GetMapping("/books/recentPage")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("/books/popularPage")
    public String popularPage() {
        return "books/popular";
    }


    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }


    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookServiceImpl.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5).getContent());


        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto) {
        if (offset < 0) {
            offset = 0;
        }
        return new BooksPageDto(bookServiceImpl.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit).getContent());
    }


    @GetMapping("/genres/index")
    public String recentBookPage() {
        return "genres/index";
    }

    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return bookServiceImpl.getBooksData();
    }

    @GetMapping("{/documents/index")
    public String documentsPage() {
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
