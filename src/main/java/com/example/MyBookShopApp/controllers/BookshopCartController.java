package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


@GlobalData
@RequestMapping("/books")
@Controller
public class BookshopCartController {

    private final BookRepository bookRepository;

    @Autowired
    public BookshopCartController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    @GetMapping("/cart/postponed/")
    public String handlePostponedRequest(@CookieValue(value = "cartContents", required = false) String cartContents,
                                         @CookieValue(name = "contents", required = false) String contents,
                                         @RequestParam(name = "slug", required = false) String slug,
                                         HttpServletResponse response,
                                         Model model) {
        cookieValue(cartContents, contents, slug, response, model);
        return "redirect:/postponed";
    }

    @PostMapping("/changeBookStatus/cart/cart/postponed/")
    public String handlePostponedRequestPost(@CookieValue(value = "cartContents", required = false) String cartContents,
                                             @CookieValue(name = "contents", required = false) String contents,
                                             @RequestParam(name = "slug", required = false) String slug,
                                             HttpServletResponse response,
                                             Model model) {
        cookieValue(cartContents, contents, slug, response, model);
        return "redirect:/postponed";
    }

    private void cookieValue(@CookieValue(value = "cartContents", required = false) String cartContents, @CookieValue(name = "contents", required = false) String contents, @PathVariable(name = "slug", required = false) String slug, HttpServletResponse response, Model model) {
        if (cartContents != null && !cartContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            if (cookieBooks.remove(slug)) {
                if (contents != null) {
                    Cookie cookieCont;
                    cookieCont = new Cookie("contents", String.valueOf(Integer.parseInt(contents) - 1));
                    cookieCont.setPath("/");
                    response.addCookie(cookieCont);
                }
            }
            Cookie cookie = new Cookie("cartContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else {
            model.addAttribute("isCartEmpty", true);
        }
    }

    private void countingMoney(List<Book> booksFromCookieSlugs, Model model) {
        int price = booksFromCookieSlugs.stream().mapToInt(Book::getPriceOld).sum();// calculation of the amount without discount
        int priceOld = booksFromCookieSlugs.stream().mapToInt(Book::dicsountPrice).sum();// discount calculation/Old price
        model.addAttribute("priceCart", price);
        model.addAttribute("priceCartOld", priceOld);
    }

    @GetMapping("/cart")
    public String handleCartRequest(@CookieValue(value = "cartContents", required = false) String cartContents,
                                    Model model) {
        if (cartContents == null || cartContents.equals("")) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            cartContents = cartContents.startsWith("/") ? cartContents.substring(1) : cartContents;
            cartContents = cartContents.endsWith("/") ? cartContents.substring(0, cartContents.length() - 1) :
                    cartContents;
            String[] cookieSlugs = cartContents.split("/");
            List<Book> booksFromCookieSlugs = bookRepository.findBooksBySlugIn(cookieSlugs);
            countingMoney(booksFromCookieSlugs, model);
            model.addAttribute("bookCart", booksFromCookieSlugs);
        }
        return "cart";
    }

    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug,
                                                  @CookieValue(name = "contents", required = false) String contents,
                                                  @CookieValue(name = "cartContents", required = false) String cartContents, HttpServletResponse response, Model model) {
        cookieValue(cartContents, contents, slug, response, model);
        return "redirect:/books/cart";
    }

    @PostMapping("/changeBookStatus/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug,
                                         @CookieValue(name = "cartContents", required = false) String cartContents,
                                         @CookieValue(name = "contents", required = false) String contents, HttpServletResponse response, Model model) {
        Cookie cookieCont;
        if (cartContents == null || cartContents.equals("")) {
            cookieCont = new Cookie("contents", "1");
            cookieCont.setPath("/");
            response.addCookie(cookieCont);
            Cookie cookie = new Cookie("cartContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else if (!cartContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");// string concatenation
            stringJoiner.add(cartContents).add(slug);
            Cookie cookie = new Cookie("cartContents", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
            cookieCont = new Cookie("contents", String.valueOf(Integer.parseInt(contents) + 1));
            cookieCont.setPath("/");
            response.addCookie(cookieCont);
        }
        return "redirect:/books/" + slug;
    }

    @ModelAttribute(name = "bookCart")
    public List<Book> bookCart() {
        return new ArrayList<>();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }
}
