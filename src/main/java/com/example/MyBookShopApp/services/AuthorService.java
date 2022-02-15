package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.author.AuthorsModel;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface AuthorService {



     Page<Book> getPageBookByAuthorSlag(String slug, int offset, int limit)  throws EmptySearchExceprtion;

     Map<String, List<AuthorsModel>> getAllAuthorsMap();

     Author getAuthorBySlug(String slug) throws EmptySearchExceprtion;
}
