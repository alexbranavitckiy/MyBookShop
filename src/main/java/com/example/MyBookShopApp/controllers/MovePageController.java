package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.data.Dto.BooksPageDto;
import com.example.MyBookShopApp.data.Dto.RecommendedBooksPageDto;
import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.services.BookService;
import com.example.MyBookShopApp.services.BooksRatingAndPopulatityService;
import com.example.MyBookShopApp.services.Impl.BookServiceImpl;
import com.example.MyBookShopApp.services.Impl.BooksRatingAndPopulatityServiceImpl;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MovePageController {


    private BookService bookService;

    @Autowired
    public MovePageController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @ModelAttribute("recommendedBooks")
    public List<Book> recommendedBooks()  {
        return bookService.getPageOfNameSortBooks(0, 6, "priceOld").getContent();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("popularBooks")
    public List<Book> popularBooks() {
        return bookService.getPageOfNameSortBooks(0, 10, "coefficient").getContent();
    }


    @ModelAttribute("resentBooks")
    public List<Book> bestsellersBooks() {
        return bookService.getPageOfNameSortBooks(0, 10, "pubDate").getContent();
    }





    @ModelAttribute("sizeSearch")
    public String sizeList() {
        return "";
    }

    @GetMapping("/books/recommended")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPageRecommended(@RequestParam("offset") Integer offset,
                                                           @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(bookService.getPageOfNameSortBooks(offset, limit, "priceOld").getContent());
    }

    @GetMapping("/books/popular")
    @ResponseBody
    public RecommendedBooksPageDto getBooksPagePopular(@RequestParam("offset") Integer offset,
                                                       @RequestParam("limit") Integer limit) {
        return new RecommendedBooksPageDto(bookService.getPageOfNameSortBooks(offset, limit, "coefficient").getContent());
    }


    @GetMapping("/books/recent")
    @ResponseBody
    public RecommendedBooksPageDto getBooksRecent(@RequestParam(required = false, value = "offset") Integer offset,
                                                  @RequestParam(required = false, value = "limit") Integer limit,
                                                  @RequestParam(required = false, value = "from") String from,
                                                  @RequestParam(required = false, value = "to") String to) {
        if (from != null && to != null && !from.isEmpty() && !to.isEmpty()) {
            return new RecommendedBooksPageDto(bookService.getPageOfDateBooks(offset, limit,
                    from.substring(6, 10) + "-" + from.substring(3, 5) + "-" + from.substring(0, 2),
                    to.substring(6, 10) + "-" + to.substring(3, 5) + "-" + to.substring(0, 2)).getContent());
         }
        return new RecommendedBooksPageDto(bookService.getPageOfNameSortBooks(offset, limit, "coefficient").getContent());
    }


    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) {
        Page<Book> bookPage = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5);
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookPage.getContent());
        model.addAttribute("sizeSearch", bookPage.getTotalElements());
        return "/search/index";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDto getNextSearchPage(@RequestParam("offset") Integer offset,
                                          @RequestParam("limit") Integer limit,
                                          @PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto, Model model) {

        if (offset < 0) offset = 0;
        Page<Book> bookPage = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit);
        model.addAttribute("searchWordDto", searchWordDto);
        model.addAttribute("searchResults",
                bookPage.getContent());
        model.addAttribute("sizeSearch", bookPage.getTotalElements());
        return new BooksPageDto(bookPage.getContent());
    }

    @GetMapping("/books/recentPage")
    public String recentPage() {
        return "books/recent";
    }

    @GetMapping("/books/popularPage")
    public String popularPage() {
        return "books/popular";
    }

    @GetMapping("/genres/index")
    public String recentBookPage() {
        return "genres/index";
    }

    @ModelAttribute("booksList")
    public List<Book> bookList() {
        return bookService.getBooksData();
    }

    @GetMapping("/documents/index")
    public String documentsPage() {
        return "documents/index";
    }

    @GetMapping("/tags/index")
    public String tagsPage() {
        return "tags/index";
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
