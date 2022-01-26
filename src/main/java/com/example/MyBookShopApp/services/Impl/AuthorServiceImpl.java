package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.repository.BookRepository;
import com.example.MyBookShopApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    @Override
    public Page<Book> getPageBookByAuthorSlag(String slug, int offset, int limit) {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        return bookRepository.findAllByAuthor(getAuthorBySlug(slug), nextPage);
    }

    @Override
    public Author getAuthorBySlug(String slug) {
        return authorRepository.getBySlug(slug);
    }

    @Override
    public Map<String, List<Author>> getAllAuthorsMap() {
        return authorRepository.findAll().stream().collect(Collectors.groupingBy((Author a) ->
                a.getLastName().substring(0, 1)));
    }


}
