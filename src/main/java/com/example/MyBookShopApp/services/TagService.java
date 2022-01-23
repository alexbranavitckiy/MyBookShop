package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.book.Book;
import com.example.MyBookShopApp.data.other.Tag;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;


public interface TagService {

    Optional<List<Tag>> findAllTag();

    List<Tag> findAllTagsAndSortSizeDesc();

    Page<Book> getPageOfTagSortBooks(Integer offset, Integer limit, Tag tag, String nameSort);

    Tag  findTagById(int id);

    List<Tag> findAllTags();

    Optional<List<Tag>> findTagByBook(Book book);

    boolean saveTag(Tag tag);

    boolean saveTagAndSubscribeBook(Tag tag,Integer bookId);

    boolean removeTagByName(String name);

    boolean removeTagById(Integer id);


}
