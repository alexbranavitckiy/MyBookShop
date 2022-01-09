package com.example.MyBookShopApp.repository;


import com.example.MyBookShopApp.data.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {





}
