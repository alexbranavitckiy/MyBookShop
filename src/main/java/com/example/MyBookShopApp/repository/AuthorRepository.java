package com.example.MyBookShopApp.repository;


import com.example.MyBookShopApp.data.Dto.AuthorDto;
import com.example.MyBookShopApp.data.book.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author getBySlug(String slug);

    List<AuthorDto> getAllBy();



}
