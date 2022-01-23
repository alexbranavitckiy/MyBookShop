package com.example.MyBookShopApp.services.Impl;

import com.example.MyBookShopApp.data.book.Author;
import com.example.MyBookShopApp.repository.AuthorRepository;
import com.example.MyBookShopApp.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {


    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public Map<String, List<Author>> getAllAuthorsMap() {
        return authorRepository.findAll().stream().collect(Collectors.groupingBy((Author a) ->
                a.getLastName().substring(0, 1)));
    }


}
