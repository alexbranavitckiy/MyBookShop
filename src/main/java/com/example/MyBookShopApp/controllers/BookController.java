package com.example.MyBookShopApp.controllers;

import com.example.MyBookShopApp.data.Book;
import com.example.MyBookShopApp.services.Impl.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookServiceImpl bookServiceImpl;

    @GetMapping("/books/by-author")
    @ApiOperation("operation to get book list of bookshop by passed author first name")
    public ResponseEntity<List<Book>> booksByAuthor(@RequestParam("author") String authorName) {
        return ResponseEntity.ok(bookServiceImpl.getBooksByAuthor(authorName));
    }

    @GetMapping("/books/by-title")
    @ApiOperation("get books by title")
    public ResponseEntity<List<Book>> booksByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(bookServiceImpl.getBooksByTitle(title));
    }

    @GetMapping("/books/by-price-range")
    @ApiOperation("get books by price range from min price to max price")
    public ResponseEntity<List<Book>> priceRangeBookss(@RequestParam("min") Integer min, @RequestParam("max") Integer max) {
        return ResponseEntity.ok(bookServiceImpl.getBooksWithPriceBetween(min, max));
    }

    @GetMapping("/books/with-max-discount")
    @ApiOperation("get list of book with max price")
    public ResponseEntity<List<Book>> maxPriceBooks() {
        return ResponseEntity.ok(bookServiceImpl.getBooksWithMaxPrice());
    }

    @GetMapping("/books/bestsellers")
    @ApiOperation("get bestseller book (which is_bestseller = 1)")
    public ResponseEntity<List<Book>> bestSellerBooks() {
        return ResponseEntity.ok(bookServiceImpl.getBestsellers());
    }


}
