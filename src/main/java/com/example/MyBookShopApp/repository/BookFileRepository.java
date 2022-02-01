package com.example.MyBookShopApp.repository;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.book.file.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFileRepository extends JpaRepository<BookFile, Integer> {

    public BookFile findBookFileByHash(String hash);


}
