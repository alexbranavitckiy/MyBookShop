package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.dtoModel.BooksPageDtoError;
import com.example.MyBookShopApp.dtoModel.SearchWordDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@GlobalData
public class SearchController {

    private final BookService bookService;

    private SearchController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("search/")
    public String searchPage() {
        return "search/index";
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    @ModelAttribute("sizeSearch")
    public String sizeList() {
        return "";
    }

    @GetMapping("/search/page/{searchWord}")
    @ResponseBody
    public BooksPageDtoError getNextSearchPage(@RequestParam("offset") Integer offset,
                                               @RequestParam("limit") Integer limit,
                                               @PathVariable(value = "searchWord", required = false)
                                                  SearchWordDto searchWordDto, Model model) throws EmptySearchExceprtion {
        if (searchWordDto != null && !searchWordDto.getExample().equals("")) {
            if (offset < 0) return new BooksPageDtoError();
            Page<Book> bookPage = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), offset, limit);
            model.addAttribute("searchWordDto", searchWordDto);
            model.addAttribute("searchResults",
                    bookPage.getContent());
            model.addAttribute("sizeSearch", bookPage.getTotalElements());
            return new BooksPageDtoError(bookPage.getContent());
        } else throw new EmptySearchExceprtion("Search word error");
    }

    @GetMapping(value = {"/search", "/search/{searchWord}"})
    public String getSearchResults(@PathVariable(value = "searchWord", required = false) SearchWordDto searchWordDto,
                                   Model model) throws EmptySearchExceprtion {
        if (searchWordDto!=null&&!searchWordDto.getExample().equals("favicon.ico")) {
            Page<Book> bookPage = bookService.getPageOfSearchResultBooks(searchWordDto.getExample(), 0, 5);
            model.addAttribute("searchWordDto", searchWordDto);
            model.addAttribute("searchResults",
                    bookPage.getContent());
            model.addAttribute("sizeSearch", bookPage.getTotalElements());
            return "/search/index";
        } else {
               throw new EmptySearchExceprtion("Not null search");
        }
    }


}
