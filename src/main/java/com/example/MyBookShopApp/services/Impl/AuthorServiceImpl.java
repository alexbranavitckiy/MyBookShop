package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.Dto.AuthorDto;
import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    @Autowired
    private AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public Page<Book> getPageBookByAuthorSlag(String slug, int offset, int limit) throws EmptySearchExceprtion {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        return bookRepository.findAllByAuthor(getAuthorBySlug(slug), nextPage);
    }

    @Override
    public Author getAuthorBySlug(String slug) throws EmptySearchExceprtion{
        Optional<Author> optionalAuthor = authorRepository.getBySlug(slug);
        if (optionalAuthor.isPresent())
            return optionalAuthor.get();
        else throw new EmptySearchExceprtion("Not found Author by slug");
    }

    @Override
    public Map<String, List<AuthorDto>> getAllAuthorsMap() {
        return authorRepository.getAllBy().stream().collect(Collectors.groupingBy((AuthorDto a) ->
                a.getFirstName().substring(0, 1)));
    }


}
