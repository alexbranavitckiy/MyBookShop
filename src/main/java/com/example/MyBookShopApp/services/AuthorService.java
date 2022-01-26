package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AuthorService {


     Map<String, List<Author>> getAllAuthorsMap();

     Page<Book> getPageBookByAuthorSlag(String slug, int offset, int limit);


     Author getAuthorBySlug(String slug);
}
