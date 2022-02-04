package com.example.MyBookShopApp.controllers;


import com.example.MyBookShopApp.data.Dto.SearchWordDto;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import com.example.MyBookShopApp.myAnnotations.GlobalData;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.services.Impl.ToolCartAndPostponedServicesImpl;
import com.example.MyBookShopApp.services.ToolCartAndPostponedServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private final ToolCartAndPostponedServices toolCartAndPostponedServices;

    @Autowired
    public BookshopCartController(ToolCartAndPostponedServices toolCartAndPostponedServices, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.toolCartAndPostponedServices = toolCartAndPostponedServices;
    }

    @PostMapping("/changeBookStatus/cart/postponed/{slug}")
    public String handlePostponedRequestPost(@CookieValue(value = "cartPostponedSize", required = false) String cartPostponedSize,
                                             @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed,
                                             @CookieValue(name = "cartContents", required = false) String cartContents,
                                             @CookieValue(name = "contents", required = false) String contentsCartSize,
                                             @PathVariable(value = "slug", required = false) String slug,
                                             HttpServletResponse response,
                                             Model model) throws EmptySearchExceprtion {
        removeCarSlug(slug, contentsCartSize, cartContents, response, model);
        setCartPostmone(cartPostponedSize, contentsPostponed, slug, response);
        return "postponed";
    }


    @PostMapping("/changeBookStatus/postponed/by/{slug}")
    public String handleRemoveBookFromPostponedByRequest(@PathVariable("slug") String slug,
                                                         @CookieValue(name = "cartPostponedSize", required = false) String cartPostponedSize,
                                                         @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed, @CookieValue(name = "cartContents", required = false) String cartContents,
                                                         @CookieValue(name = "contents", required = false) String contents, HttpServletResponse response, Model model) throws EmptySearchExceprtion {

        removePostmoneCart(slug, cartPostponedSize, contentsPostponed, response, model);
        eddCartSlug(slug, cartContents, contents, response);
        return "redirect:/books/cart";
    }


    @PostMapping("/changeBookStatus/postponed/remove/{slug}")
    public String handleRemoveBookFromPostponedRequest(@PathVariable("slug") String slug,
                                                       @CookieValue(name = "cartPostponedSize", required = false) String cartPostponedSize,
                                                       @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        removePostmoneCart(slug, cartPostponedSize, contentsPostponed, response, model);
        return "redirect:/books/cart";
    }

    @GetMapping({"/postponed/{slug}", "/postponed"})
    public String postponedPage(@CookieValue(value = "contentsPostponed", required = false) String contentsPostponed,
                                @PathVariable(value = "slug", required = false) String slug,
                                Model model) {
        if (contentsPostponed == null || contentsPostponed.equals("")) {
            model.addAttribute("isPostponedEmpty", true);
        } else {
            model.addAttribute("isPostponedEmpty", false);
            model.addAttribute("bookPostponed", bookRepository.findBooksBySlugIn(this.toolCartAndPostponedServices.generateCookieSlugsToSlugs(contentsPostponed)));
        }
        return "postponed";
    }


    @PostMapping("/changeBookStatus/postponed/{slug}")//Ok
    public String handleChangeBookStatusPostponed(@PathVariable("slug") String slug,
                                                  @CookieValue(name = "cartPostponedSize", required = false) String cartPostponedSize,
                                                  @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        setCartPostmone(cartPostponedSize, contentsPostponed, slug, response);
        return "redirect:/books/" + slug;
    }


    @GetMapping("/cart")
    public String handleCartRequest(@CookieValue(value = "cartContents", required = false) String cartContents,
                                    Model model) {
        if (cartContents == null || cartContents.equals("")) {
            model.addAttribute("isCartEmpty", true);
        } else {
            model.addAttribute("isCartEmpty", false);
            model.addAttribute("bookCart", bookRepository.findBooksBySlugIn(this.toolCartAndPostponedServices.generateCookieSlugsToSlugs(cartContents)));
        }
        return "cart";
    }


    @PostMapping("/changeBookStatus/cart/remove/{slug}")
    public String handleRemoveBookFromCartRequest(@PathVariable("slug") String slug,
                                                  @CookieValue(name = "contents", required = false) String contents,
                                                  @CookieValue(name = "cartContents", required = false) String cartContents, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        removeCarSlug(slug, contents, cartContents, response, model);
        return "redirect:/books/cart";
    }



    @PostMapping("/changeBookStatus/{slug}")
    public String handleChangeBookStatus(@PathVariable("slug") String slug,
                                         @CookieValue(name = "cartContents", required = false) String cartContents,
                                         @CookieValue(name = "contents", required = false) String contents, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        eddCartSlug(slug, cartContents, contents, response);
        return "redirect:/books/" + slug;
    }

    @ModelAttribute(name = "bookCart")
    public List<Book> bookCart() {
        return new ArrayList<>();
    }

    @ModelAttribute(name = "bookPostponed")
    public List<Book> bookPostponed() {
        return new ArrayList<>();
    }

    @ModelAttribute("searchWordDto")
    public SearchWordDto searchWordDto() {
        return new SearchWordDto();
    }

    private void cookieValue(@CookieValue(value = "cartContents", required = false) String cartContents, String contents, String slug, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        if (cartContents != null && !cartContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            if (cookieBooks.remove(slug)) {
                if (contents != null) {
                    response.addCookie(this.toolCartAndPostponedServices.minesSizeCart(contents, "contents"));
                }
            }
            response.addCookie(this.toolCartAndPostponedServices.addSlugCart("cookieBooks", slug, "cartContents", "/books"));
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


    private void setCartPostmone(@CookieValue(value = "cartPostponedSize", required = false) String cartPostponedSize, @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed, @PathVariable(value = "slug", required = false) String slug, HttpServletResponse response) throws EmptySearchExceprtion {
        if (contentsPostponed == null || contentsPostponed.equals("")) {
            response.addCookie(this.toolCartAndPostponedServices.defaultCartSize(cartPostponedSize, "cartPostponedSize"));
            response.addCookie(this.toolCartAndPostponedServices.SetCookie("/books", slug, "contentsPostponed"));
        } else if (!contentsPostponed.contains(slug)) {
            response.addCookie(this.toolCartAndPostponedServices.addSlugCart(contentsPostponed, slug, "contentsPostponed", "/books"));
            response.addCookie(this.toolCartAndPostponedServices.plusSizeCart(cartPostponedSize, "cartPostponedSize"));
        }
    }

    private void eddCartSlug(@PathVariable("slug") String slug, @CookieValue(name = "cartContents", required = false) String cartContents, @CookieValue(name = "contents", required = false) String contents, HttpServletResponse response) throws EmptySearchExceprtion {
        if (cartContents == null || cartContents.equals("")) {
            response.addCookie(this.toolCartAndPostponedServices.defaultCartSize(contents, "contents"));
            Cookie cookie = new Cookie("cartContents", slug);
            cookie.setPath("/books");
            response.addCookie(cookie);
        } else if (!cartContents.contains(slug)) {
            StringJoiner stringJoiner = new StringJoiner("/");// string concatenation
            stringJoiner.add(cartContents).add(slug);
            Cookie cookie = new Cookie("cartContents", stringJoiner.toString());
            cookie.setPath("/books");
            response.addCookie(cookie);
            response.addCookie(this.toolCartAndPostponedServices.plusSizeCart(contents, "contents"));
        }
    }

    private void removePostmoneCart(@PathVariable("slug") String slug, @CookieValue(name = "cartPostponedSize", required = false) String cartPostponedSize, @CookieValue(name = "contentsPostponed", required = false) String contentsPostponed, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        if (contentsPostponed != null && !contentsPostponed.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(contentsPostponed.split("/")));
            if (cookieBooks.remove(slug) && cartPostponedSize != null) {
                response.addCookie(this.toolCartAndPostponedServices.minesSizeCart(cartPostponedSize, "cartPostponedSize"));
            }
            Cookie cookie = new Cookie("contentsPostponed", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isPostponedEmpty", false);
        } else {
            model.addAttribute("isPostponedEmpty", true);
        }
    }

    private void removeCarSlug(@PathVariable("slug") String slug, @CookieValue(name = "contents", required = false) String contents, @CookieValue(name = "cartContents", required = false) String cartContents, HttpServletResponse response, Model model) throws EmptySearchExceprtion {
        if (cartContents != null && !cartContents.equals("")) {
            ArrayList<String> cookieBooks = new ArrayList<>(Arrays.asList(cartContents.split("/")));
            if (cookieBooks.remove(slug) && contents != null) {
                response.addCookie(this.toolCartAndPostponedServices.minesSizeCart(contents, "contents"));
            }
            Cookie cookie = new Cookie("cartContents", String.join("/", cookieBooks));
            cookie.setPath("/books");
            response.addCookie(cookie);
            model.addAttribute("isCartEmpty", false);
        } else {
            model.addAttribute("isCartEmpty", true);
        }
    }

}
