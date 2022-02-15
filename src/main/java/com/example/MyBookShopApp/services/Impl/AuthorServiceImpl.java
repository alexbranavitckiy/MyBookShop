package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.dtoModel.author.AuthorsModel;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.convector.AuthorConvectorImpl;
import com.example.MyBookShopApp.dtoModel.convector.BookСonvectorImpl;
import com.example.MyBookShopApp.dtoModel.page.BooksPageDtoModel;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookСonvectorImpl bookConvertor;
    private final AuthorConvectorImpl authorConvector;

    @Autowired
    private AuthorServiceImpl(AuthorConvectorImpl authorConvector, BookСonvectorImpl bookConvertor, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.authorConvector = authorConvector;
        this.bookConvertor = bookConvertor;
        this.bookRepository = bookRepository;
    }


    @Override
    public BooksPageDtoModel getPageBookByAuthorSlag(String slug, int offset, int limit) throws EmptySearchExceprtion {
        Pageable nextPage = PageRequest.of(offset, limit, Sort.by(Sort.Direction.DESC, "pubDate"));
        Page<Book> page=bookRepository.findAllByAuthor(getAuthorBySlug(slug), nextPage);
        List<BookDtoModel> bookDtoModelList = page.stream().map(bookConvertor::convertToDto).collect(Collectors.toList());
        return new BooksPageDtoModel(bookDtoModelList.size(),bookDtoModelList,page.getTotalElements());
    }

    @Override
    public Author getAuthorBySlug(String slug) throws EmptySearchExceprtion {
        Optional<Author> optionalAuthor = authorRepository.getBySlug(slug);
        if (optionalAuthor.isPresent())
            return optionalAuthor.get();
        else throw new EmptySearchExceprtion("Not found Author by slug");
    }

    @Override
    public AuthorsModel getAuthorBySlugModelDto(String slug) throws EmptySearchExceprtion {
        Optional<Author> optionalAuthor = authorRepository.getBySlug(slug);
        if (optionalAuthor.isPresent())
            return authorConvector.toDto(optionalAuthor.get());
        else throw new EmptySearchExceprtion("Not found Author by slug");
    }

    @Override
    public Map<String, List<AuthorsModel>> getAllAuthorsMap() {
        return authorRepository.getAllBy().stream().map(authorConvector::toDto).collect(Collectors.groupingBy((AuthorsModel a) ->
                a.getFirstName().substring(0, 1)));
    }

}
