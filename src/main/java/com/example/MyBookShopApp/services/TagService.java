package com.example.MyBookShopApp.services;

import com.example.MyBookShopApp.data.other.Tag;
import com.example.MyBookShopApp.dtoModel.book.BookDtoModel;
import com.example.MyBookShopApp.dtoModel.tag.TagDtoModel;
import com.example.MyBookShopApp.erss.EmptySearchExceprtion;

import java.util.List;
import java.util.Optional;


public interface TagService {

    Optional<List<Tag>> findAllTag();

    List<TagDtoModel> findAllTagsAndSortSizeDesc();

    List<BookDtoModel> getPageOfTagSortBooks(Integer offset, Integer limit, TagDtoModel tag, String nameSort);

    TagDtoModel  findTagById(int id) throws EmptySearchExceprtion;

    List<Tag> findAllTags();

}
